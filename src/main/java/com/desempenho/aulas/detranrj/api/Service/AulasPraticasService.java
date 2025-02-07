package com.desempenho.aulas.detranrj.api.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.desempenho.aulas.detranrj.api.entity.Aula;
import com.desempenho.aulas.detranrj.api.util.ResponseHandler;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AulasPraticasService {

	private final AulasService aulasService;

	public CompletableFuture<List<Aula>> getDetalheAulasPraticas(String renach) {
		return ResponseHandler.handleAsyncRequestService(
				() -> aulasService.requestAsync(renach, "", "pratica"),
				response -> aulasService.convertRetorno(response));
	}

}