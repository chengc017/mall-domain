package com.sohu.sur.base.exception;

/**
 * 品牌模块 异常类
 * 
 * @author xiayanming
 *
 */
public class SucBrandException extends SucException {

	/**
     * Class constructor.
     */
    public SucBrandException() {
        super();
    }

    /**
     * Class constructor.
     *
     * @param message	java.lang.String
     */
    public SucBrandException(String message) {
        super(message);
    }

    /**
     * Class constructor.
     *
     * @param message
     * @param cause
     */
    public SucBrandException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Class constructor.
     *
     * @param cause
     */
    public SucBrandException(Throwable cause) {
        super(cause);
    }
}
