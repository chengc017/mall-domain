package com.sohu.sur.base.exception;

/**
 * 缓存异常
 * @author leiyangbj6779
 *
 */
public class CacheException extends RuntimeException{
	private static final long serialVersionUID = 1517100105306654733L;

	/**
     * Class constructor.
     */
    public CacheException() {
        super();
    }

    /**
     * Class constructor.
     *
     * @param message	java.lang.String
     */
    public CacheException(String message) {
        super(message);
    }

    /**
     * Class constructor.
     *
     * @param message
     * @param cause
     */
    public CacheException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Class constructor.
     *
     * @param cause
     */
    public CacheException(Throwable cause) {
        super(cause);
    }
    

    

}
