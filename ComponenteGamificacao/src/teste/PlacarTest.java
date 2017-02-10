package teste;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import gamificacao.Placar;
import gamificacao.Ponto;
import gamificacao.Usuario;
import mock.ArmazenamentoMock;

public class PlacarTest {
	
	private ArmazenamentoMock mock;
	private Placar placar;
	
	@Before
	public void init() {
		mock = new ArmazenamentoMock();
		placar = new Placar();
		placar.setArmazenamento(mock);
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
}
