package com.desempenho.aulas.detranrj.api.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.desempenho.aulas.detranrj.api.Entity.AulasEntity;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DetalheAulasTeoricasService {

	private final AulasService aulasService;

	public List<AulasEntity> getDetalheAulasTeoricas(String renach) {
		return aulasService
				.convertRetornoAulas(
						aulasService.requestAulas(renach, "TEORICAS", "detalhesTeorico"));
	}

}