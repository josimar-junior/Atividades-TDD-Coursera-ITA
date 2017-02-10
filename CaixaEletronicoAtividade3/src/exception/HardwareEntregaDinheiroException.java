package exception;

public class HardwareEntregaDinheiroException extends RuntimeException {

	private static final long serialVersionUID = -4837634974143835929L;

	public HardwareEntregaDinheiroException() {
		super("Falha na entrega do dinheiro.");
	}
}
