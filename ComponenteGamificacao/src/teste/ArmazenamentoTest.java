package teste;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import gamificacao.Armazenamento;
import gamificacao.Ponto;
import gamificacao.Repositorio;
import gamificacao.Usuario;

public class ArmazenamentoTest {

	private Repositorio repositorio;
	private Armazenamento armazenamento;
	
	@Before
	public void init() {
		repositorio = new Repositorio("usuarios");
		armazenamento = new Armazenamento(repositorio);
	}
	
	@Test
	public void deveArmazenarUmUsuario() throws Exception {
		armazenamento.armazenarPontuacao("Josimar", "Estrela", 10);
		
		assertEquals("O usuário Josimar recebeu 10 pontos do tipo Estrela", "O usuário " + armazenamento.getPontosUsuario("Josimar").getNome() + ""
				+ " recebeu " + armazenamento.getPontosUsuario("Josimar").getQuantidadePontosPorTipo("Estrela") + " pontos do tipo " + armazenamento.getTiposPontosPorUsuario("Josimar").get(0).getTipo());
	}
	
	@Test
	public void deveRetornarPontosPorTipoDeUmUsuario() throws Exception {
		armazenamento.armazenarPontuacao("Josimar", "Estrela", 10);
		armazenamento.armazenarPontuacao("Lucas", "Moeda", 20);
		
		
		assertEquals(20, armazenamento.getPontosUsuario("Lucas").getQuantidadePontosPorTipo("Moeda"));
	}
	
	@Test
	public void deveRetornarTodosOsUsuariosComPontos() throws Exception {
		armazenamento.armazenarPontuacao("Josimar", "Estrela", 10);
		armazenamento.armazenarPontuacao("Lucas", "Moeda", 20);
		armazenamento.armazenarPontuacao("Maria", "Estrela", 0);
		
		List<Usuario> usuarios = armazenamento.getUsuariosComPontos();
		assertEquals(2, usuarios.size());
	}
	
	@Test
	public void deveRetornarTodosOsTiposRegistradosAoMesmoUsuario() throws Exception {
		armazenamento.armazenarPontuacao("Josimar", "Estrela", 10);
		armazenamento.armazenarPontuacao("Josimar", "Moeda", 20);
		armazenamento.armazenarPontuacao("Maria", "Estrela", 0);
		
		List<Ponto> pontos = armazenamento.getTiposPontosPorUsuario("Josimar");
		
		assertEquals(2, pontos.size());
	}
	
	@After
	public void limparArquivo() {
		repositorio.limparArquivo();
	}

}
	