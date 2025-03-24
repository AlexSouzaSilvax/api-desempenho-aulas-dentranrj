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
import com.desempenho.aulas.detranrj.api.service.ConclusaoAulasService;
import com.desempenho.aulas.detranrj.api.util.ResponseHandler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Conclusão das Aulas", description = "Quadro de Conclusão das Aulas")
@CrossOrigin
@RestController(value = "/api/conclusao-aulas")
@RequestMapping(name = "/api/conclusao-aulas", value = "/api/conclusao-aulas")
@Validated
public class ConclusaoAulasController {

	@Autowired
	ConclusaoAulasService conclusaoAulasService;

	@Operation(summary = "Aulas", description = "Retorna uma lista com situação da conclusão das suas aulas")
	@PostMapping()
	public CompletableFuture<ResponseEntity<? extends Object>> getConclusaoAulas(
			@Valid @RequestBody RenachDTO request) {
		return ResponseHandler
				.handleAsyncRequest(() -> conclusaoAulasService.getConclusaoAulas(request.getRenach()));
	}

}