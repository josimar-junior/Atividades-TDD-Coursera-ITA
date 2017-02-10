package gamificacao;

import java.util.List;
import java.util.stream.Collectors;

import interfaces.Armazenador;

public class Armazenamento implements Armazenador {

	private Repositorio repositorio;

	public Armazenamento(Repositorio repositorio) {
		this.repositorio = repositorio;
	}

	@Override
	public void armazenarPontuacao(String nome, String tipo, int quantidade) {
		Usuario usuario = repositorio.getUsuarioOuCria(nome);
		usuario.atribuirPontos(tipo, quantidade);
		repositorio.salvar(usuario);
	}

	@Override
	public Usuario getPontosUsuario(String nome) {
		return repositorio.listarUsuarios().get(nome);
	}

	@Override
	public List<Usuario> getUsuariosComPontos() {
		return repositorio.listarUsuarios().values().stream().filter(us -> us.contemPontos())
				.collect(Collectors.toList());
	}
	
	public List<Ponto> getTiposPontosPorUsuario(String nome) {
		return repositorio.getUsuarioOuCria(nome).getPontos();
	}

}
