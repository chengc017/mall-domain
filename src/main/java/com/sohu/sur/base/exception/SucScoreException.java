package com.sohu.sur.base.exception;

/**
 * 积分管理模版异常类
 * 
 * @author xiayanming
 *
 */
public class SucScoreException extends SucException {

	/**
     * Class constructor.
     */
    public SucScoreException() {
        super();
    }

    /**
     * Class constructor.
     *
     * @param message	java.lang.String
     */
    public SucScoreException(String message) {
        super(message);
    }

    /**
     * Class constructor.
     *
     * @param message
     * @param cause
     */
    public SucScoreException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Class constructor.
     *
     * @param cause
     */
    public SucScoreException(Throwable cause) {
        super(cause);
    }
}
