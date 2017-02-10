package br.com.tdd.exception;

public class StringContemCaracterEspecialException extends RuntimeException {

	private static final long serialVersionUID = -2240279582445573601L;

	public StringContemCaracterEspecialException(String msg) {
		super(msg);
	}
}
