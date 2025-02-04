package com.desempenho.aulas.detranrj.api.apidesempenhoaulasdetranrj;

import java.io.IOException;
import java.util.List;

import com.desempenho.aulas.detranrj.api.Entity.DetalheAulasTeoricasEntity;
import com.desempenho.aulas.detranrj.api.Service.DetalheAulasTeoricasService;

public class TesteDetalheAulasTeoricas {

	public static void main(String[] args) throws IOException {

		DetalheAulasTeoricasService disciplinaController = new DetalheAulasTeoricasService();

		//String renach = "332806847"; // meu
		String renach = "491165714"; // joao
		List<DetalheAulasTeoricasEntity> detalheAulasTeoricas = disciplinaController
				.convertRetornoDetalhe(disciplinaController.requestDetalheAulas(renach));

		for (int i = 0; i < detalheAulasTeoricas.size(); i++) {
			System.out.println("\n" + " Data: " + detalheAulasTeoricas.get(i).getData() + "\n Inicio: "
					+ detalheAulasTeoricas.get(i).getInicio() + "\n Fim: " + detalheAulasTeoricas.get(i).getFim()
					+ "\n Disc: " + detalheAulasTeoricas.get(i).getDisciplina() + "\n Envio: "
					+ detalheAulasTeoricas.get(i).getDataEnvio() + "\n Status: "
					+ detalheAulasTeoricas.get(i).getStatus());
		}

	}

}
