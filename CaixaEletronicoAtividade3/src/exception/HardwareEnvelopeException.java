package exception;

public class HardwareEnvelopeException extends RuntimeException {

	private static final long serialVersionUID = 9212198548630525901L;

	public HardwareEnvelopeException() {
		super("Falha na leitura do envelope");
	}
}
