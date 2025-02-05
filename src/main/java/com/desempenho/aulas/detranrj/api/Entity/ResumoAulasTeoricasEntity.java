package com.desempenho.aulas.detranrj.api.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResumoAulasTeoricasEntity {

	private String disciplina;

	@JsonProperty("quantidade_aulas")
	private String quantidadeAulas;

}
