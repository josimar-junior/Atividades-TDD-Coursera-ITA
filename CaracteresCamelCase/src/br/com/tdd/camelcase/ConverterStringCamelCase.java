package br.com.tdd.camelcase;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.tdd.exception.StringContemCaracterEspecialException;
import br.com.tdd.exception.StringIniciaComNumeroException;

public class ConverterStringCamelCase {

	public static List<String> converterCamelCase(String valorPassado) {

		verificarIniciaComNumero(valorPassado);
		verificarContemCaracteresEspeciais(valorPassado);
		if (isAcronimo(valorPassado)) {
			List<String> strings = new ArrayList<>();
			strings.add(valorPassado);
			return strings;
		}
		return getValoresLista(valorPassado);
	}

	private static List<String> getValoresLista(String valorPassado) {

		if (isContemNumero(valorPassado)) {
			return getStringsComNumeros(valorPassado);
		}

		if (isContemLetraMaiuscula(valorPassado)) {
			return getSringsSeparadas(valorPassado);
		}

		return getStringUnica(valorPassado);

	}

	private static List<String> getStringUnica(String valorPassado) {
		List<String> strings = new ArrayList<>();
		strings.add(valorPassado.toLowerCase());

		return strings;
	}

	private static void verificarIniciaComNumero(String valorPassado) {
		if (Character.isDigit(valorPassado.charAt(0))) {
			throw new StringIniciaComNumeroException("Inválido. Não deve começar com números");
		}
	}

	private static boolean isAcronimo(String valorPassado) {
		return valorPassado.matches("([A-Z])*");
	}

	private static void verificarContemCaracteresEspeciais(String valorPassado) {
		if (!valorPassado.matches("([A-Za-z0-9])*")) {
			throw new StringContemCaracterEspecialException(
					"Inválido. Caracteres especiais não são permitidos, somente letras e números");
		}
	}

	private static boolean isContemLetraMaiuscula(String valorPassado) {
		return valorPassado.split("(?=[A-Z])").length > 1;
	}

	private static List<String> getSringsSeparadas(String valorPassado) {
		String[] split = valorPassado.split("(?=[A-Z])");
		List<String> strings = new ArrayList<>();
		List<String> listaAux = new ArrayList<>();
		StringBuilder aux = new StringBuilder();
		boolean adicionou = false;
		adicionarStringsNaLista(split, listaAux, aux, adicionou, strings);
		return strings;
	}

	private static void adicionarStringsNaLista(String[] split, List<String> listaAux, StringBuilder aux, boolean adicionou,
			List<String> strings) {
		for (String s : split) {
			if (s.length() == 1) {
				listaAux = adicionarAcronimo(aux.append(s));
				adicionou = false;
				continue;
			}
			adicionarAcronimoLista(listaAux, strings, aux, adicionou);
			strings.add(s.toLowerCase());
		}
		verificarSeAdicionouAcronimo(adicionou, strings, listaAux);
	}

	private static void verificarSeAdicionouAcronimo(boolean adicionou, List<String> strings, List<String> listaAux) {
		if (!adicionou) {
			if (!listaAux.isEmpty()) {
				strings.addAll(listaAux);
			}
		}
	}

	public static void adicionarAcronimoLista(List<String> listaAux, List<String> strings, StringBuilder aux,
			Boolean adicionou) {
		if (!listaAux.isEmpty()) {
			strings.addAll(listaAux);
			listaAux.clear();
			aux.delete(0, aux.length());
			adicionou = true;
		}
	}

	public static List<String> adicionarAcronimo(StringBuilder string) {
		List<String> lista = new ArrayList<>();
		lista.clear();
		lista.add(string.toString());
		return lista;
	}

	private static boolean isContemNumero(String valorPassado) {
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(valorPassado);
		return m.find();
	}

	private static List<String> getStringsComNumeros(String valorPassado) {
		String[] split = valorPassado.split("(?=\\p{Upper})|(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
		List<String> strings = new ArrayList<>();
		for (String s : split) {
			strings.add(s.toLowerCase());
		}
		return strings;
	}
}
