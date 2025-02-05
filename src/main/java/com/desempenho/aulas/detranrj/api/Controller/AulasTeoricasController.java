package com.desempenho.aulas.detranrj.api.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desempenho.aulas.detranrj.api.Service.DetalheAulasTeoricasService;
import com.desempenho.aulas.detranrj.api.Service.ResumoAulasTeoricasService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Aulas Teóricas", description = "Suas aulas teóricas")
@CrossOrigin
@RestController(value = "/api/aulasteoricas")
@RequestMapping(name = "/api/aulasteoricas", value = "aulasteoricas")
public class AulasTeoricasController {

	@Autowired
	ResumoAulasTeoricasService resumoAulasTeoricasService;

	@Autowired
	DetalheAulasTeoricasService detalheAulasTeoricasService;

	@Operation(summary = "Quantidade das Aulas Teóricas Concluídas", description = "Lista de quantidade de aulas teóricas por disciplina")
	@GetMapping("resumo")
	public ResponseEntity<Object> getQtdAulasDisciplina(@RequestParam String renach) throws IOException {
		try {
			return ResponseEntity.ok().body(resumoAulasTeoricasService
					.convertRetornoResumo(resumoAulasTeoricasService.requestResumoAulas(renach)));
		} catch (Exception e) {
			return ResponseEntity.ok().body("Nenhuma informação encontrada");
		}
	}

	@Operation(summary = "Detalhe das Aulas Teóricas", description = "Retorna uma lista do detalhe das aulas teóricas")
	@GetMapping("detalhe")
	public ResponseEntity<Object> getDetalheAulas(@RequestParam String renach) throws IOException {
		try {
			return ResponseEntity.ok().body(detalheAulasTeoricasService.getDetalheAulasTeoricas(renach));
		} catch (Exception e) {
			return ResponseEntity.ok().body("Nenhuma informação encontrada");
		}
	}

}