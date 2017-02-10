package br.com.tdd.testes;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import br.com.tdd.camelcase.ConverterStringCamelCase;
import br.com.tdd.exception.StringContemCaracterEspecialException;
import br.com.tdd.exception.StringIniciaComNumeroException;

public class ConverterStringCamelCaseTest {

	@Test
	public void deveRetornarAMesmaStringPassadaTodaMinuscula() throws Exception {
		List<String> valoresEsperados = Arrays.asList("nome");
		String valorPassado = "nome";
		
		assertEquals(valoresEsperados, ConverterStringCamelCase.converterCamelCase(valorPassado));
	}
	
	@Test
	public void deveRetornarUnicaStringTodaMinuscula() throws Exception {
		List<String> valoresEsperados = Arrays.asList("nome");
		String valorPassado = "Nome";
		
		assertEquals(valoresEsperados, ConverterStringCamelCase.converterCamelCase(valorPassado));
	}
	
	@Test
	public void deveRetornarStringSeparadaNomeCompostoInicioMinusculo() throws Exception {
		List<String> valoresEsperados = Arrays.asList("nome", "composto");
		String valorPassado = "nomeComposto";
		
		assertEquals(valoresEsperados, ConverterStringCamelCase.converterCamelCase(valorPassado));
	}
	
	@Test
	public void deveRetornarStringSeparadaNomeCompostoInicioMaiusculo() throws Exception {
		List<String> valoresEsperados = Arrays.asList("nome", "composto");
		String valorPassado = "NomeComposto";
		
		assertEquals(valoresEsperados, ConverterStringCamelCase.converterCamelCase(valorPassado));
	}
	
	@Test
	public void deveRetornarStringTodaMaiuscula() throws Exception {
		List<String> valoresEsperados = Arrays.asList("CPF");
		String valorPassado = "CPF";
		
		assertEquals(valoresEsperados, ConverterStringCamelCase.converterCamelCase(valorPassado));
	}
	
	@Test
	public void deveRetornarStringComMinusculasEAcronimo() throws Exception {
		List<String> valoresEsperados = Arrays.asList("numero", "CPF");
		String valorPassado = "numeroCPF";
		
		assertEquals(valoresEsperados, ConverterStringCamelCase.converterCamelCase(valorPassado));
	}
	
	@Test
	public void deveRetornarStringComMinusculasEAcronimoNoMeio() throws Exception {
		List<String> valoresEsperados = Arrays.asList("numero", "CPF", "contribuinte");
		String valorPassado = "numeroCPFContribuinte";
		
		assertEquals(valoresEsperados, ConverterStringCamelCase.converterCamelCase(valorPassado));
	}
	
	@Test
	public void deveRetornarStringComNumeroNoMeio() throws Exception {
		List<String> valoresEsperados = Arrays.asList("recupera", "10", "primeiros");
		String valorPassado = "recupera10Primeiros";
		
		assertEquals(valoresEsperados, ConverterStringCamelCase.converterCamelCase(valorPassado));
	}
	
	@Test(expected = StringIniciaComNumeroException.class)
	public void deveLancarExceptionSeStringIniciarComNumero() throws Exception {
		String valorPassado = "10Primeiros";
		ConverterStringCamelCase.converterCamelCase(valorPassado);
	}
	
	@Test(expected = StringContemCaracterEspecialException.class)
	public void deveLancarExceptionSeStringConterCaracteresEspeciais() throws Exception {
		String valorPassado = "nome#Composto";
		ConverterStringCamelCase.converterCamelCase(valorPassado);
	}
	
}
