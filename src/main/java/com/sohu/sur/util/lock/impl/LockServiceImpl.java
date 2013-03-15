package com.sohu.sur.util.lock.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sohu.sur.util.lock.LockService;

/**
 * 实现锁
 * 
 * @author xuewuhao
 * 
 */
public class LockServiceImpl implements LockService {

	private static Log log = LogFactory.getLog(LockServiceImpl.class);
	private Map<String, Lock> lockMap;

	/**
	 * 初始化map锁
	 */
	public LockServiceImpl() {
		lockMap = Collections.synchronizedMap(new HashMap<String, Lock>());
	}

	public Lock getLockObj(String key) {
		if (!lockMap.containsKey(key)) {
			newLock(key);
		}
		return lockMap.get(key);
	}

	private synchronized void newLock(String key) {

		if (!lockMap.containsKey(key)) {
			log.info("will create a new lock key=" + key);
			Lock lock = new ReentrantLock(true);
			lockMap.put(key, lock);
		}
	}

}
