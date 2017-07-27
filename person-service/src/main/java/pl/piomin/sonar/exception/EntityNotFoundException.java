package pl.piomin.sonar.exception;

/**
 * Throws when entity is not found in repository
 * @author minkowp
 *
 */
public class EntityNotFoundException extends Exception {

	private static final long serialVersionUID = 325184415613820299L;

	public EntityNotFoundException() {
		super();
	}

	public EntityNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public EntityNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public EntityNotFoundException(String arg0) {
		super(arg0);
	}

	public EntityNotFoundException(Throwable arg0) {
		super(arg0);
	}
	
}
