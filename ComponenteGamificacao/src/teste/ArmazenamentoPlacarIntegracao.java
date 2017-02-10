package teste;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import gamificacao.Armazenamento;
import gamificacao.Placar;
import gamificacao.Ponto;
import gamificacao.Repositorio;
import gamificacao.Usuario;

public class ArmazenamentoPlacarIntegracao {

	private Repositorio repositorio;
	private Armazenamento armazenamento;
	private Placar placar;
	
	@Before
	public void init() {
		repositorio = new Repositorio("usuarios");
		armazenamento = new Armazenamento(repositorio);
		placar = new Placar();
		placar.setArmazenamento(armazenamento);
	}
	
	@Test
	public void deveRetornarPontoDeUmUsuario() throws Exception {
		placar.armazenarPontuacao("Josimar", "Estrela", 10);
		
		assertEquals("O usuário Josimar recebeu 10 pontos do tipo Estrela", "O usuário " + placar.getPontosPorUsuario("Josimar").getNome() + ""
				+ " recebeu " + placar.getPontosPorUsuario("Josimar").getQuantidadePontosPorTipo("Estrela") + " pontos do tipo " + placar.getTiposPontosPorUsuario("Josimar").get(0).getTipo());
	}
	
	@Test
	public void deveRetornarTodosOsPontosDeUmUsuario() throws Exception {
		placar.armazenarPontuacao("Lucas", "Estrela", 10);
		placar.armazenarPontuacao("Lucas", "Moeda", 20);
		placar.armazenarPontuacao("Maria", "Estrela", 0);
		
		List<Ponto> pontos = placar.getTiposPontosPorUsuario("Lucas");
		
		assertEquals(2, pontos.size());
	}
	
	@Test
	public void deveRetornarRankingPorTipo() throws Exception {
		
		placar.armazenarPontuacao("Lucas", "Estrela", 10);
		placar.armazenarPontuacao("Lucas", "Moeda", 20);
		placar.armazenarPontuacao("Maria", "Estrela", 30);
		
		List<Usuario> ranking = placar.getRankingPorTipo("Estrela");
		
		assertEquals("Maria , 30", ranking.get(0).getNome() + " , " + ranking.get(0).getQuantidadePontosPorTipo("Estrela"));
	}
	
	@After
	public void limparArquivo() {
		repositorio.limparArquivo();
	}
}
