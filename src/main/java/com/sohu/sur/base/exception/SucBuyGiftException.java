package com.sohu.sur.base.exception;

/**
 * 礼品兑换过程的异常对象
 * 
 * @author xiayanming
 *
 */
public class SucBuyGiftException extends SucException {

	/**
     * Class constructor.
     */
    public SucBuyGiftException() {
        super();
    }

    /**
     * Class constructor.
     *
     * @param message	java.lang.String
     */
    public SucBuyGiftException(String message) {
        super(message);
    }

    /**
     * Class constructor.
     *
     * @param message
     * @param cause
     */
    public SucBuyGiftException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Class constructor.
     *
     * @param cause
     */
    public SucBuyGiftException(Throwable cause) {
        super(cause);
    }
}
