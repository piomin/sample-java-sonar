package pl.piomin.sonar.exception;

/**
 * Throws when user is not authenticated
 * @author minkowp
 *
 */
public class AuthenticationException extends Exception {

	private static final long serialVersionUID = -756686878433120266L;

	public AuthenticationException() {
		super();
	}

	public AuthenticationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuthenticationException(String message) {
		super(message);
	}

	public AuthenticationException(Throwable cause) {
		super(cause);
	}

}
