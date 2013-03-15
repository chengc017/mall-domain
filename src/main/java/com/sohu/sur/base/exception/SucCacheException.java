package com.sohu.sur.base.exception;


/**
 * 
 * @author xiayanming
 *
 */
public class SucCacheException extends SucRuntimeException {
	
	public SucCacheException (String message) {
		super(message);
	}
	
	public SucCacheException (String message, Throwable thr) {
		super(message,thr);
	}

}