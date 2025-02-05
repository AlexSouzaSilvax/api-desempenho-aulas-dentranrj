package com.desempenho.aulas.detranrj.api.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desempenho.aulas.detranrj.api.Service.DetalheAulasPraticasService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Aulas Práticas", description = "Suas aulas práticas")
@CrossOrigin
@RestController(value = "/api/aulaspraticas")
@RequestMapping(name = "/api/aulaspraticas", value = "aulaspraticas")
public class AulasPraticasController {

	@Autowired
	DetalheAulasPraticasService detalheAulasPraticasService;

	@Operation(summary = "Detalhe das Aulas Práticas", description = "Retorna uma lista do detalhe das aulas práticas")
	@GetMapping("detalhe")
	public ResponseEntity<Object> getDetalheAulasPraticas(@RequestParam String renach) throws IOException {
		try {
			return ResponseEntity.ok().body(detalheAulasPraticasService.getDetalheAulasPraticas(renach));
		} catch (Exception e) {
			return ResponseEntity.ok().body("Nenhuma informação encontrada");
		}
	}

}