package com.sohu.sur.base.exception;

/**
 * Sis基础异常类
 * 
 * @author xiayanming
 *
 */
public class SucException extends Exception {
	
	/**
     * Class constructor.
     */
    public SucException() {
        super();
    }

    /**
     * Class constructor.
     *
     * @param message	java.lang.String
     */
    public SucException(String message) {
        super(message);
    }

    /**
     * Class constructor.
     *
     * @param message
     * @param cause
     */
    public SucException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Class constructor.
     *
     * @param cause
     */
    public SucException(Throwable cause) {
        super(cause);
    }
}
