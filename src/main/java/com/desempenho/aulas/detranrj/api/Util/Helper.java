package com.desempenho.aulas.detranrj.api.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class Helper {

	public static boolean validNumber(String valor) {
		return !valor.isEmpty() && !valor.equalsIgnoreCase(null) && valor.matches("[0-9]*");
	}

	public static String formatText(String valor) {
		return capitalizeFully(valor);
	}

	public static String correcaoString(String entrada) {

		Map<String, String> strings = new HashMap<>();
		strings.put("Tecnico Teorico", "Técnico Teórico");
		strings.put("Pratico De Direcao Veicular - Moto", "Prático De Direção Veícular - Moto");
		strings.put("Pratico De Direcao Veicular - Auto", "Prático De Direção Veícular - Auto");
		strings.put("Dire", "Direção Defensiva");
		strings.put("No", "Noções de Mecânica");
		strings.put("Legisla", "Legislação de Trânsito");

		for (Map.Entry<String, String> entry : strings.entrySet()) {
			String pattern = entry.getKey();
			String saida = entry.getValue();

			if (entrada.contains(pattern)) {
				return saida;
			}
		}
		return entrada;
	}

	public static String formatData(String data) {
		try {
			SimpleDateFormat inputFormat = new SimpleDateFormat("M/dd/yyyy hh:mm:ss a");
			SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");

			Date date = inputFormat.parse(data);
			return outputFormat.format(date);
		} catch (ParseException e) {
			// e.printStackTrace();
			return data;
		}
	}

	public static String capitalizeFully(String str) {
		if (str == null) {
			return null;
		}
		String[] words = str.toLowerCase().split(" ");
		for (int i = 0; i < words.length; i++) {
			words[i] = StringUtils.capitalize(words[i]);
		}
		return String.join(" ", words);
	}

}