package com.desempenho.aulas.detranrj.api.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Aula {

	private String data;

	private String inicio;

	private String fim;

	private String disciplina;

	@JsonProperty("data_envio")
	private String dataEnvio;

	private String status;

	@JsonIgnore
	public Date getDataAsDate() {
		try {
			return new SimpleDateFormat("dd/MM/yyyy").parse(this.data);
		} catch (ParseException e) {
			throw new RuntimeException("Erro ao converter data: " + this.data, e);
		}
	}
}
