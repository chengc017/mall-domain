package com.sohu.sur.base.exception;

public class SucCatetoryException extends SucException {

	/**
     * Class constructor.
     */
    public SucCatetoryException() {
        super();
    }

    /**
     * Class constructor.
     *
     * @param message	java.lang.String
     */
    public SucCatetoryException(String message) {
        super(message);
    }

    /**
     * Class constructor.
     *
     * @param message
     * @param cause
     */
    public SucCatetoryException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Class constructor.
     *
     * @param cause
     */
    public SucCatetoryException(Throwable cause) {
        super(cause);
    }
}
