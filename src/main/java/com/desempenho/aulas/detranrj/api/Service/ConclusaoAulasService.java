package com.desempenho.aulas.detranrj.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.desempenho.aulas.detranrj.api.entity.Prova;
import com.desempenho.aulas.detranrj.api.util.Helper;
import com.desempenho.aulas.detranrj.api.util.ResponseHandler;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConclusaoAulasService {

	private final AulasService aulasServiceHttpClient;

	public CompletableFuture<List<Prova>> getConclusaoAulas(String renach) {
		return ResponseHandler.handleAsyncRequestService(
				() -> aulasServiceHttpClient.requestAsync(renach, "", "conclusao"),
				response -> convertRetorno(response));
	}

	public List<Prova> convertRetorno(String retorno) {

		if (retorno == null || retorno.isEmpty()) {
			System.out.println("Retorno vazio ou nulo.");
			return new ArrayList<>();
		}

		List<Prova> listaProvas = new ArrayList<>();
		List<String> lista1 = new ArrayList<>();

		try {

			Document doc = Jsoup.parseBodyFragment(retorno);
			for (Element trTd : doc.select("tr td")) {
				Element span = trTd.selectFirst("span");
				String text = (span != null) ? span.text() : trTd.ownText();
				lista1.add(Helper.formatText(text));
			}

			for (int i = 0; i < lista1.size(); i += 4) {
				if (i + 3 >= lista1.size())
					break;
				listaProvas.add(new Prova(Helper.correcaoString(lista1.get(i)), lista1.get(i + 2)));
			}
		} catch (Exception e) {
			System.err.println("Erro ao processar o retorno: " + e.getMessage());
			// e.printStackTrace();
		}

		return listaProvas;
	}

}