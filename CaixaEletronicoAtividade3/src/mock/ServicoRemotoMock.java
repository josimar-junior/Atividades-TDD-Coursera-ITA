package mock;

import java.util.HashMap;
import java.util.Map;

import caixa.ContaCorrente;
import interfaces.ServicoRemoto;

public class ServicoRemotoMock implements ServicoRemoto {

	private Map<String, ContaCorrente> contas = new HashMap<>();
	
	@Override
	public ContaCorrente recuperarConta(String numero) {
		return contas.get(numero);
	}

	public void adicionarConta(ContaCorrente conta) {
		contas.put(conta.getNumero(), conta);
	}

	@Override
	public void persistirConta(ContaCorrente conta) {
		contas.put(conta.getNumero(), conta);
	}
}
