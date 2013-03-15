package com.sohu.sur.base.exception;

/**
 * Sis运行时异常
 * 
 * @author xiayanming
 *
 */
public class SucRuntimeException extends RuntimeException {

	/**
     * Class constructor.
     */
    public SucRuntimeException() {
        super();
    }

    /**
     * Class constructor.
     *
     * @param message	java.lang.String
     */
    public SucRuntimeException(String message) {
        super(message);
    }

    /**
     * Class constructor.
     *
     * @param message
     * @param cause
     */
    public SucRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Class constructor.
     *
     * @param cause
     */
    public SucRuntimeException(Throwable cause) {
        super(cause);
    }
}
