package com.desempenho.aulas.detranrj.api.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AulasEntity {

	private String data;

	private String inicio;

	private String fim;

	private String disciplina;

	@JsonProperty("data_envio")
	private String dataEnvio;

	private String status;

}
