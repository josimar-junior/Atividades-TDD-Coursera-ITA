package interfaces;

import java.util.List;

import gamificacao.Usuario;

public interface Armazenador {

	void armazenarPontuacao(String nome, String tipo, int quantidade);
	Usuario getPontosUsuario(String nome);
	List<Usuario> getUsuariosComPontos();
}
