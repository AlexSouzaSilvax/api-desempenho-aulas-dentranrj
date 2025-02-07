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
import com.desempenho.aulas.detranrj.api.service.ProvasService;
import com.desempenho.aulas.detranrj.api.util.ResponseHandler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Provas", description = "Resultados das suas provas")
@CrossOrigin
@RestController(value = "/api/provas")
@RequestMapping(name = "/api/provas", value = "/api/provas")
@Validated
public class ProvasController {

	@Autowired
	ProvasService provasService;

	@Operation(summary = "Provas", description = "Retorna uma lista com resumo das suas provas")
	@PostMapping("resultado")
	public CompletableFuture<ResponseEntity<? extends Object>> getResultadoProvas(
			@Valid @RequestBody RenachDTO request) {
		return ResponseHandler
				.handleAsyncRequest(() -> provasService.getResultadoProvas(request.getRenach()));
	}

}