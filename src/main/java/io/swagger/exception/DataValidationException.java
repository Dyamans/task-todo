/**
 * 
 */
package io.swagger.exception;

/**
 * @author dgpats
 * This is for the validation error display. 400
 *
 */
public class DataValidationException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataValidationException() {
        super();
    }

    public DataValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataValidationException(String message) {
        super(message);
    }

    public DataValidationException(Throwable cause) {
        super(cause);
    }
}
