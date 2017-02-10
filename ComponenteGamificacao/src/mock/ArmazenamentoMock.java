package mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gamificacao.Usuario;
import interfaces.Armazenador;

public class ArmazenamentoMock implements Armazenador {

	private Map<String, Usuario> map = new HashMap<>();
	
	@Override
	public void armazenarPontuacao(String nome, String tipo, int quantidade) {
		Usuario usuario = getPontosUsuario(nome);
		if(usuario == null) {
			usuario = new Usuario(nome);
		}
		usuario.atribuirPontos(tipo, quantidade);
		map.put(nome, usuario);
	}

	@Override
	public Usuario getPontosUsuario(String nome) {
		return map.get(nome);
	}
	
	@Override
	public List<Usuario> getUsuariosComPontos() {
		return new ArrayList<>(map.values());
	}
	
}
