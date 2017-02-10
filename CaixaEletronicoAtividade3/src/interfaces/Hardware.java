package interfaces;

import caixa.CaixaEletronico;
import exception.HardwareCartaoException;
import exception.HardwareEntregaDinheiroException;
import exception.HardwareEnvelopeException;

public interface Hardware {

	String pegarNumeroDaContaCartao(String numero, CaixaEletronico caixa) throws HardwareCartaoException;
	void entregarDinheiro(String numero) throws HardwareEntregaDinheiroException;
	void lerEnvelope(double valor) throws HardwareEnvelopeException;
}
