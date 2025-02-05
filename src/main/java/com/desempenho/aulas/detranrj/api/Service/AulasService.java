package com.desempenho.aulas.detranrj.api.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.desempenho.aulas.detranrj.api.Entity.AulasEntity;
import com.desempenho.aulas.detranrj.api.Util.Helper;

@Service
public class AulasService {

	public String requestAulas(String renach, String disciplina, String tipo) {

		String command = "curl -k -d \"renach=RJ" + renach
				+ "&disciplina=" + disciplina + "&tipo=" + tipo
				+ "\" -H \"Content-Type: application/x-www-form-urlencoded\" --header \"Accept-Charset: UTF-8\" -X POST https://www2.detran.rj.gov.br/portal/habilitacao/biometriaValid";
		StringBuilder output = new StringBuilder();

		try {
			Process process = Runtime.getRuntime().exec(command);

			try (BufferedReader reader = new BufferedReader(
					new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
				String line;
				while ((line = reader.readLine()) != null) {
					output.append(line).append("\n");
				}
			}

			int exitVal = process.waitFor();
			if (exitVal == 0) {
				return Normalizer.normalize(output.toString(), Normalizer.Form.NFC);
			} else {
				try (BufferedReader errorReader = new BufferedReader(
						new InputStreamReader(process.getErrorStream(), StandardCharsets.UTF_8))) {
					StringBuilder errorOutput = new StringBuilder();
					String line;
					while ((line = errorReader.readLine()) != null) {
						errorOutput.append(line).append("\n");
					}
					System.err.println("Erro ao executar comando: " + errorOutput.toString());
				}
			}
		} catch (IOException | InterruptedException e) {
			System.err.println("Erro durante a execução do comando: " + e.getMessage());
			e.printStackTrace();
		}
		return "";
	}

	public List<AulasEntity> convertRetornoAulas(String retorno) {
		if (retorno == null || retorno.isEmpty()) {
			System.out.println("Retorno vazio ou nulo.");
			return new ArrayList<>();
		}

		List<AulasEntity> detalheAulas = new ArrayList<>();
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
				detalheAulas
						.add(new AulasEntity(Helper.formatData(lista1.get(i)), lista1.get(i + 1),
								lista1.get(i + 2), Helper.correcaoString(lista1.get(i + 3)),
								Helper.formatData(lista1.get(i + 4)), lista1.get(i + 5)));
			}
		} catch (Exception e) {
			System.err.println("Erro ao processar o retorno: " + e.getMessage());
			e.printStackTrace();
		}

		return detalheAulas;
	}
}