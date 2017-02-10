package gamificacao;

import java.io.Serializable;

public class Ponto implements Serializable {

	private static final long serialVersionUID = 3053244455127776269L;

	private String tipo;
	private int quantidade;

	public Ponto(String tipo, int quantidade) {
		this.tipo = tipo;
		this.quantidade = quantidade;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public boolean isQuantidadeMaiorQueZero() {
		return getQuantidade() > 0;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public void adicionarPonto(int quantidade) {
		this.quantidade += quantidade;
	}

}
