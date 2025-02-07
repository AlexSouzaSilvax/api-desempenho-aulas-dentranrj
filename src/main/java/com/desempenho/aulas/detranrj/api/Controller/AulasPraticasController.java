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
import com.desempenho.aulas.detranrj.api.service.AulasPraticasService;
import com.desempenho.aulas.detranrj.api.util.ResponseHandler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Aulas Práticas", description = "Suas aulas práticas")
@CrossOrigin
@RestController(value = "/api/aulaspraticas")
@RequestMapping(name = "/api/aulaspraticas", value = "/api/aulaspraticas")
@Validated
public class AulasPraticasController {

	@Autowired
	AulasPraticasService aulasPraticasService;

	@Operation(summary = "Detalhe das Aulas Práticas", description = "Retorna uma lista do detalhe das aulas práticas")
	@PostMapping("detalhe")
	public CompletableFuture<ResponseEntity<? extends Object>> getDetalheAulasPraticas(
			@Valid @RequestBody RenachDTO request) {

		return ResponseHandler
				.handleAsyncRequest(() -> aulasPraticasService.getDetalheAulasPraticas(request.getRenach()));
	}

}