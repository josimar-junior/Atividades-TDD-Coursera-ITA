package mock;

import caixa.CaixaEletronico;
import exception.HardwareCartaoException;
import exception.HardwareEntregaDinheiroException;
import exception.HardwareEnvelopeException;
import interfaces.Hardware;

public class HardwareMock implements Hardware {

	@Override
	public String pegarNumeroDaContaCartao(String numero, CaixaEletronico caixa) {
		if(numero == null) {
			throw new HardwareCartaoException();
		}
		return caixa.getServicoRemoto().recuperarConta(numero).getNumero();
	}

	@Override
	public void entregarDinheiro(String numero) {
		if(numero == null) {
			throw new HardwareEntregaDinheiroException();
		}
		System.out.println("Dinheiro entregue.");
	}

	@Override
	public void lerEnvelope(double valor) {
		if(valor < 0) {
			throw new HardwareEnvelopeException();
		}
		System.out.println("Valor: " + valor);
	}


}
