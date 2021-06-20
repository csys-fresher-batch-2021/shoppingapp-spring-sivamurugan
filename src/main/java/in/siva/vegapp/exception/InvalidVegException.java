package in.siva.vegapp.exception;

public class InvalidVegException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public InvalidVegException(String message) {
		super(message);
	}
}
