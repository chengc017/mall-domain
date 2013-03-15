package com.sohu.sur.base.exception;

/**
 * 礼品模块 异常类
 * 
 * @author xiayanming
 *
 */
public class SucGiftException extends SucException {

	/**
     * Class constructor.
     */
    public SucGiftException() {
        super();
    }

    /**
     * Class constructor.
     *
     * @param message	java.lang.String
     */
    public SucGiftException(String message) {
        super(message);
    }

    /**
     * Class constructor.
     *
     * @param message
     * @param cause
     */
    public SucGiftException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Class constructor.
     *
     * @param cause
     */
    public SucGiftException(Throwable cause) {
        super(cause);
    }
}
