package com.sohu.sur.base.exception;

public class SucTradeException extends SucException {

	/**
     * Class constructor.
     */
    public SucTradeException() {
        super();
    }

    /**
     * Class constructor.
     *
     * @param message	java.lang.String
     */
    public SucTradeException(String message) {
        super(message);
    }

    /**
     * Class constructor.
     *
     * @param message
     * @param cause
     */
    public SucTradeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Class constructor.
     *
     * @param cause
     */
    public SucTradeException(Throwable cause) {
        super(cause);
    }
}
