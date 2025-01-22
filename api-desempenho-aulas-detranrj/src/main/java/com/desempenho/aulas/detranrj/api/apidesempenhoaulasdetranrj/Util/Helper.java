package com.desempenho.aulas.detranrj.api.apidesempenhoaulasdetranrj.Util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.text.WordUtils;

@SuppressWarnings("deprecation")
public class Helper {
	public static boolean validNumber(String valor) {
		return !valor.isEmpty() && !valor.equalsIgnoreCase(null) && valor.matches("[0-9]*");
	}

	public static String formatText(String valor) {
		return WordUtils.capitalizeFully(valor);
	}

	public static String correcaoString(String entrada) {

		Map<String, String> strings = new HashMap<>();
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

}