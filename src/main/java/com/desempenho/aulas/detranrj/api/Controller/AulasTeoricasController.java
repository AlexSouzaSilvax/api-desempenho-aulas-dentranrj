package com.desempenho.aulas.detranrj.api.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desempenho.aulas.detranrj.api.dto.RenachDTO;
import com.desempenho.aulas.detranrj.api.service.AulasTeoricasService;
import com.desempenho.aulas.detranrj.api.util.ResponseHandler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Aulas Teóricas", description = "Suas aulas teóricas")
@CrossOrigin
@RestController(value = "/api/aulasteoricas")
@RequestMapping(name = "/api/aulasteoricas", value = "/api/aulasteoricas")
@Validated
public class AulasTeoricasController {

	@Autowired
	AulasTeoricasService aulasTeoricasService;

	@Operation(summary = "Quantidade das Aulas Teóricas Concluídas", description = "Lista de quantidade de aulas teóricas por disciplina")
	@PostMapping("resumo")
	public CompletableFuture<ResponseEntity<? extends Object>> getQtdAulasDisciplina(
			@Valid @RequestBody RenachDTO request) {
		return ResponseHandler
				.handleAsyncRequest(() -> aulasTeoricasService.getResumoAulasTeoricas(request.getRenach()));
	}

	@Operation(summary = "Detalhe das Aulas Teóricas", description = "Retorna uma lista do detalhe das aulas teóricas")
	@PostMapping("detalhe")
	public CompletableFuture<ResponseEntity<? extends Object>> getDetalheAulas(@Valid @RequestBody RenachDTO request) {
		return ResponseHandler
				.handleAsyncRequest(() -> aulasTeoricasService.getDetalheAulasTeoricas(request.getRenach()));
	}

}