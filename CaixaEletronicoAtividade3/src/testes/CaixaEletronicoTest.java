package testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import caixa.CaixaEletronico;
import caixa.ContaCorrente;
import exception.HardwareCartaoException;
import exception.HardwareEntregaDinheiroException;
import exception.HardwareEnvelopeException;
import mock.HardwareMock;
import mock.ServicoRemotoMock;

public class CaixaEletronicoTest {

	private CaixaEletronico caixaEletronico;
	private ServicoRemotoMock servicoRemotoMock;
	private HardwareMock hardwareMock;
	
	@Before
	public void init() {
		caixaEletronico = new CaixaEletronico();
		servicoRemotoMock = new ServicoRemotoMock();
		hardwareMock = new HardwareMock();
		
		servicoRemotoMock.adicionarConta(new ContaCorrente("123", 100));
		servicoRemotoMock.adicionarConta(new ContaCorrente("321", 200));
	}
	
	@Test
	public void deveRealizarLogin() throws Exception {
		caixaEletronico.addHardwareMock(hardwareMock);
		caixaEletronico.addServicoRemotoMock(servicoRemotoMock);
		assertEquals("Usuário Autenticado", caixaEletronico.logar("123"));
	}
	
	@Test
	public void deveRetornaOSaldo() throws Exception {
		caixaEletronico.addServicoRemotoMock(servicoRemotoMock);
		assertEquals("O saldo é R$200,00", caixaEletronico.saldo("321"));
	}
	
	@Test
	public void deveSacarDinheiroComSucesso() throws Exception {
		caixaEletronico.addServicoRemotoMock(servicoRemotoMock);
		caixaEletronico.addHardwareMock(hardwareMock);
		assertEquals("Retire seu dinheiro", caixaEletronico.sacar(50, "321"));
	}
	
	@Test
	public void deveMostrarSaldoInsuficiente() throws Exception {
		caixaEletronico.addServicoRemotoMock(servicoRemotoMock);
		caixaEletronico.addHardwareMock(hardwareMock);
		assertEquals("Saldo insuficiente", caixaEletronico.sacar(500, "123"));
	}
	
	@Test
	public void depositar() throws Exception {
		caixaEletronico.addServicoRemotoMock(servicoRemotoMock);
		caixaEletronico.addHardwareMock(hardwareMock);
		assertEquals("Depósito recebido com sucesso", caixaEletronico.depositar(100, "123"));
		assertEquals(200, caixaEletronico.getServicoRemoto().recuperarConta("123").getSaldo(), 0.001);
	}
	
	@Test(expected=HardwareCartaoException.class)
	public void deveFalharAoPegarNumeroDaContaDoCartao() throws Exception {
		caixaEletronico.addHardwareMock(hardwareMock);
		caixaEletronico.logar(null);
	}
	
	@Test(expected=HardwareEntregaDinheiroException.class)
	public void deveFalharAoEntregarDinheiro() throws Exception {
		caixaEletronico.addHardwareMock(hardwareMock);
		caixaEletronico.getHardware().entregarDinheiro(null);
	}
	
	@Test(expected=HardwareEnvelopeException.class)
	public void deveFalharAoLerEnvelope() throws Exception {
		caixaEletronico.addHardwareMock(hardwareMock);
		caixaEletronico.getHardware().lerEnvelope(-1);;
	}
}
