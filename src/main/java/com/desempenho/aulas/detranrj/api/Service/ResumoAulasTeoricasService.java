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

import com.desempenho.aulas.detranrj.api.Entity.ResumoAulasTeoricasEntity;
import com.desempenho.aulas.detranrj.api.Util.Helper;

@Service
public class ResumoAulasTeoricasService {

	public String requestResumoAulas(String renach) {
		try {
			Process process = Runtime.getRuntime().exec("curl -k -d \"renach=RJ" + renach
					+ "&tipo=resumo\" -H \"Content-Type: application/x-www-form-urlencoded\" --header \"Accept-Charset: UTF-8\" -X POST https://www2.detran.rj.gov.br/portal/habilitacao/biometriaValid");

			StringBuilder output = new StringBuilder();
			try (BufferedReader reader = new BufferedReader(
					new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
				String line;
				while ((line = reader.readLine()) != null) {
					output.append(line).append("\n");
				}
			}

			if (process.waitFor() == 0) {
				return Normalizer.normalize(output.toString(), Normalizer.Form.NFC);
			} else {
				return "";
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return "";
		}
	}

	public List<ResumoAulasTeoricasEntity> convertRetornoResumo(String retorno) {
		List<ResumoAulasTeoricasEntity> resumoAulasTeoricasBeans = new ArrayList<>();

		try {
			if (retorno.contains("NENHUM REGISTRO ENCONTRADO DE AULAS")) {
				System.out.println("Nenhum registro encontrado de aulas.");
				return resumoAulasTeoricasBeans;
			}

			Document doc = Jsoup.parseBodyFragment(retorno);

			List<String> listaQuantidades = new ArrayList<>();
			List<String> listaDisciplinas = new ArrayList<>();

			for (Element trTd : doc.select("tr td")) {
				if (Helper.validNumber(trTd.ownText())) {
					listaQuantidades.add(trTd.ownText().trim());
				}
			}

			for (Element a : doc.select("a")) {
				listaDisciplinas.add(Helper.formatText(Normalizer.normalize(a.ownText(), Normalizer.Form.NFC)));
			}

			int minSize = Math.min(listaQuantidades.size(), listaDisciplinas.size());
			for (int i = 0; i < minSize; i++) {
				ResumoAulasTeoricasEntity resumoBean = new ResumoAulasTeoricasEntity();
				resumoBean.setNome(listaDisciplinas.get(i));
				resumoBean.setQuantidade(listaQuantidades.get(i));
				resumoAulasTeoricasBeans.add(resumoBean);
			}

		} catch (Exception e) {
			System.err.println("Erro ao processar o resumo: " + e.getMessage());
			e.printStackTrace();
		}

		return resumoAulasTeoricasBeans;
	}
}
