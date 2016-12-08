package br.com.correios.cep.ex;

/**
 * The type Not found exception.
 */
public class NotFoundException extends RuntimeException {

    /**
     * Instantiates a new Not found exception.
     *
     * @param message the message
     */
    public NotFoundException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Not found exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
