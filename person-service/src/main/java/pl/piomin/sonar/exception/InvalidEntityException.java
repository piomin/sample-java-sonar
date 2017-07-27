package pl.piomin.sonar.exception;

/**
 * Throws wen entity is invalid
 * @author minkowp
 *
 */
public class InvalidEntityException extends Exception {

	private static final long serialVersionUID = 325184415613820299L;

	public InvalidEntityException() {
		super();
	}

	public InvalidEntityException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public InvalidEntityException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public InvalidEntityException(String arg0) {
		super(arg0);
	}

	public InvalidEntityException(Throwable arg0) {
		super(arg0);
	}
	
}
