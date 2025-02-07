package com.desempenho.aulas.detranrj.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResumoAulasTeoricas {

	private String disciplina;

	@JsonProperty("quantidade_aulas")
	private String quantidadeAulas;

}
