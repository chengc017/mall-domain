package com.sohu.sur.base.exception;

/**
 * 抽奖模块 异常类
 * 
 * @author xiayanming
 *
 */
public class SucLotteryException extends SucException {

	/**
     * Class constructor.
     */
    public SucLotteryException() {
        super();
    }

    /**
     * Class constructor.
     *
     * @param message	java.lang.String
     */
    public SucLotteryException(String message) {
        super(message);
    }

    /**
     * Class constructor.
     *
     * @param message
     * @param cause
     */
    public SucLotteryException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Class constructor.
     *
     * @param cause
     */
    public SucLotteryException(Throwable cause) {
        super(cause);
    }
}
