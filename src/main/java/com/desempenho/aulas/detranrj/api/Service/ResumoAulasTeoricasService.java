package com.desempenho.aulas.detranrj.api.Service;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.desempenho.aulas.detranrj.api.Entity.ResumoAulasTeoricasEntity;
import com.desempenho.aulas.detranrj.api.Util.Helper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ResumoAulasTeoricasService {

	private final AulasService aulasService;

	public String requestResumoAulas(String renach) {
		return aulasService.requestAulas(renach, "", "resumo");
	}

	public List<ResumoAulasTeoricasEntity> convertRetornoResumo(String retorno) {
		List<ResumoAulasTeoricasEntity> resumoAulasTeoricasBeans = new ArrayList<>();

		try {
			if (retorno.contains("NENHUM REGISTRO ENCONTRADO DE AULAS")) {
				System.out.println("Nenhum registro encontrado de aulas.");
				return resumoAulasTeoricasBeans;
			}

			Document doc = Jsoup.parseBodyFragment(retorno);

			List<String> listaQuantidadeAulas = new ArrayList<>();
			List<String> listaDisciplinas = new ArrayList<>();

			for (Element trTd : doc.select("tr td")) {
				if (Helper.validNumber(trTd.ownText())) {
					listaQuantidadeAulas.add(trTd.ownText().trim());
				}
			}

			for (Element a : doc.select("a")) {
				listaDisciplinas.add(Helper.formatText(Normalizer.normalize(a.ownText(), Normalizer.Form.NFC)));
			}

			int minSize = Math.min(listaQuantidadeAulas.size(), listaDisciplinas.size());
			for (int i = 0; i < minSize; i++) {
				resumoAulasTeoricasBeans
						.add(new ResumoAulasTeoricasEntity(listaDisciplinas.get(i), listaQuantidadeAulas.get(i)));
			}

		} catch (Exception e) {
			System.err.println("Erro ao processar o resumo: " + e.getMessage());
			e.printStackTrace();
		}

		return resumoAulasTeoricasBeans;
	}
}
