package caixa;

import interfaces.Hardware;
import interfaces.ServicoRemoto;

public class CaixaEletronico {

	private Hardware hardware;
	private ServicoRemoto servicoRemoto;

	public void addHardwareMock(Hardware hardware) {
		this.hardware = hardware;
	}

	public Hardware getHardware() {
		return hardware;
	}

	public void addServicoRemotoMock(ServicoRemoto servicoRemoto) {
		this.servicoRemoto = servicoRemoto;
	}
	
	public ServicoRemoto getServicoRemoto() {
		return servicoRemoto;
	}

	public String logar(String numeroConta) {
		try {
			if (hardware.pegarNumeroDaContaCartao(numeroConta, this) != null) {
				return "Usuário Autenticado";
			}
		} catch (NullPointerException e) {
			return "Não foi possível autenticar o usuário";
		}
		return "";
	}

	public String saldo(String numero) {
		return "O saldo é R$" + String.valueOf(servicoRemoto.recuperarConta(numero).getSaldo()).replace(".0", "") + ",00";
	}

	public String sacar(int valor, String numero) {
		ContaCorrente conta = servicoRemoto.recuperarConta(numero);
		if(conta.getSaldo() >= valor) {
			conta.setSaldo(conta.getSaldo() - valor);
			servicoRemoto.persistirConta(conta);
			hardware.entregarDinheiro(numero);
			return "Retire seu dinheiro";
		}
		return "Saldo insuficiente";
	}

	public String depositar(double valor, String numero) {
		ContaCorrente conta = servicoRemoto.recuperarConta(numero);
		hardware.lerEnvelope(valor);
		conta.setSaldo(conta.getSaldo() + valor);
		servicoRemoto.persistirConta(conta);
		
		return "Depósito recebido com sucesso";
	}

}
