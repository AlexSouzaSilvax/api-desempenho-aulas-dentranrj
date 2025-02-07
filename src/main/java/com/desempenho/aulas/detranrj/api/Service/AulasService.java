package com.desempenho.aulas.detranrj.api.service;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.desempenho.aulas.detranrj.api.entity.Aula;
import com.desempenho.aulas.detranrj.api.util.Helper;

@Service
public class AulasService {

    private static final HttpClient client = createInsecureHttpClient();

    private static HttpClient createInsecureHttpClient() {
        try {
            // TrustManager ignora certificados inválidos
            TrustManager[] trustAllCertificates = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }

                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                        }
                    }
            };

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCertificates, new java.security.SecureRandom());

            return HttpClient.newBuilder()
                    .sslContext(sslContext)
                    .version(HttpClient.Version.HTTP_2)
                    .followRedirects(HttpClient.Redirect.NORMAL)
                    .connectTimeout(Duration.ofSeconds(10))
                    .build();

        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException("Erro ao configurar SSL inseguro", e);
        }
    }

    public CompletableFuture<String> requestAsync(String renach, String disciplina, String tipo) {
        String requestBody = "renach=" + URLEncoder.encode("RJ" + renach, StandardCharsets.UTF_8)
                + "&disciplina=" + URLEncoder.encode(disciplina, StandardCharsets.UTF_8)
                + "&tipo=" + URLEncoder.encode(tipo, StandardCharsets.UTF_8);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www2.detran.rj.gov.br/portal/habilitacao/biometriaValid"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Accept-Charset", "UTF-8")
                .timeout(Duration.ofSeconds(30))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        // Envia a requisição de forma assíncrona e retorna um CompletableFuture
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8))
                .thenApply(response -> {
                    if (response.statusCode() == 200) {
                        return response.body();
                    } else {
                        System.err.println("Erro na requisição: " + response.statusCode() + " - " + response.body());
                        return "";
                    }
                })
                .exceptionally(e -> {
                    System.err.println("Erro ao enviar requisição: " + e.getMessage());
                    return "";
                });
    }

    // Chamada síncrona, sem bloquear a thread principal
    public String request(String renach, String disciplina, String tipo) {
        try {
            return requestAsync(renach, disciplina, tipo).get(); // Bloqueia para esperar a resposta (se necessário)
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Erro ao enviar requisição: " + e.getMessage());
        }
        return "";
    }

    public List<Aula> convertRetorno(String retorno) {
        if (retorno == null || retorno.isEmpty()) {
            System.out.println("Retorno vazio ou nulo.");
            return new ArrayList<>();
        }

        List<Aula> listaAulas = new ArrayList<>();
        List<String> lista1 = new ArrayList<>();

        try {

            Document doc = Jsoup.parseBodyFragment(retorno);
            for (Element trTd : doc.select("tr td")) {
                Element span = trTd.selectFirst("span");
                String text = (span != null) ? span.text() : trTd.ownText();
                lista1.add(Helper.formatText(text));
            }

            for (int i = 0; i < lista1.size(); i += 6) {
                if (i + 5 >= lista1.size())
                    break;
                listaAulas
                        .add(new Aula(Helper.formatData(lista1.get(i)), lista1.get(i + 1),
                                lista1.get(i + 2), Helper.correcaoString(lista1.get(i + 3)),
                                Helper.formatData(lista1.get(i + 4)), lista1.get(i + 5)));
            }

            listaAulas.sort(Comparator.comparing(Aula::getDataAsDate).reversed());

        } catch (Exception e) {
            System.err.println("Erro ao processar o retorno: " + e.getMessage());
            // e.printStackTrace();
        }

        return listaAulas;
    }

}
