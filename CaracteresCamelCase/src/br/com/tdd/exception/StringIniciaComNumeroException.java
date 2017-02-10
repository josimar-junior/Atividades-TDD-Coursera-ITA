package br.com.tdd.exception;

public class StringIniciaComNumeroException extends RuntimeException {

	private static final long serialVersionUID = -2240279582445573601L;

	public StringIniciaComNumeroException(String msg) {
		super(msg);
	}
}
