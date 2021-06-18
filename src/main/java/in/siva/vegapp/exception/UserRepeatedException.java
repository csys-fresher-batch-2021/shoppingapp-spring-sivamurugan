package in.siva.vegapp.exception;

public class UserRepeatedException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserRepeatedException(String message) {
		super(message);
	}
}
