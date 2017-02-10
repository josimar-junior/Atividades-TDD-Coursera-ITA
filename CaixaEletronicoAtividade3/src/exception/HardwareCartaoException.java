package exception;

public class HardwareCartaoException extends RuntimeException {

	private static final long serialVersionUID = -4422345102895782058L;

	public HardwareCartaoException() {
		super("Falha na leitura do cartão.");
	}
}
