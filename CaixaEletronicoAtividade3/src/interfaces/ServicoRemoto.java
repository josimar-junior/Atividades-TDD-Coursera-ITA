package interfaces;

import caixa.ContaCorrente;

public interface ServicoRemoto {

	ContaCorrente recuperarConta(String numero);
	void persistirConta(ContaCorrente conta);
}
