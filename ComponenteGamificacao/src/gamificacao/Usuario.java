package gamificacao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Usuario implements Serializable {

	private static final long serialVersionUID = -5693356613287690012L;
	
	private String nome;
	private HashMap<String, Ponto> pontos;

	public Usuario(String nome) {
		this.nome = nome;
		pontos = new HashMap<>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Ponto> getPontos() {
		return pontos.values().stream().filter(p -> p.isQuantidadeMaiorQueZero()).collect(Collectors.toList());
	}

	public void adicionarPontos(Ponto ponto) {
		pontos.put(ponto.getTipo(), ponto);
	}

	public int getQuantidadePontosPorTipo(String tipo) {
		return buscarPontos(tipo).getQuantidade();
	}
	
	public boolean isQuantidadeMaiorQueZeroPorTipo(String tipo) {
		return getQuantidadePontosPorTipo(tipo) > 0;
	}
	
	public boolean contemPontos() {
		return getTotalPontos() > 0;
	}

	public int getTotalPontos() {
		return pontos.values().stream().mapToInt(ponto -> ponto.getQuantidade()).sum();
	}
	
	public void atribuirPontos(String tipo, int quantidade) {
		buscarPontos(tipo).adicionarPonto(quantidade);
	}

	public Ponto buscarPontos(String tipo) {
		Ponto ponto = pontos.getOrDefault(tipo, new Ponto(tipo, 0));
		pontos.put(tipo, ponto);
		return ponto;
	}
	
}
