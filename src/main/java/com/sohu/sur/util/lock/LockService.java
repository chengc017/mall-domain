package com.sohu.sur.util.lock;

import java.util.concurrent.locks.Lock;

/**
 * 声明锁接口
 * @author xuewuhao
 *
 */
public interface LockService {

	public Lock getLockObj(String id);
}
