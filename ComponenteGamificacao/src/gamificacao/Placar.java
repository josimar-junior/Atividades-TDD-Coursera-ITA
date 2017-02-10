package gamificacao;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import interfaces.Armazenador;

public class Placar {

	private Armazenador mock;
	
	public void setArmazenamento(Armazenador mock) {
		this.mock = mock;
	}

	public void armazenarPontuacao(String nome, String tipo, int quantidade) {
		mock.armazenarPontuacao(nome, tipo, quantidade);
	}

	public Usuario getPontosPorUsuario(String nome) {
		return mock.getPontosUsuario(nome);
	}

	public List<Usuario> getRankingPorTipo(String tipo) {
		return mock.getUsuariosComPontos().stream().filter(us -> us.isQuantidadeMaiorQueZeroPorTipo(tipo))
		.sorted(comparatorMaiorPontuacao(tipo))
		.collect(Collectors.toList());
	}
	
	public List<Ponto> getTiposPontosPorUsuario(String nome) {
		return mock.getPontosUsuario(nome).getPontos();
	}
	
	private Comparator<Usuario> comparatorMaiorPontuacao(String tipo) {
		return (u1, u2) -> Integer.compare(u2.getQuantidadePontosPorTipo(tipo), u1.getQuantidadePontosPorTipo(tipo));
	}

}
