package com.desempenho.aulas.detranrj.api.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.desempenho.aulas.detranrj.api.Entity.AulasEntity;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DetalheAulasPraticasService {

	private final AulasService detalheAulasService;

	public List<AulasEntity> getDetalheAulasPraticas(String renach) {
		return detalheAulasService
				.convertRetornoAulas(
						detalheAulasService.requestAulas(renach, "", "pratica"));
	}
}