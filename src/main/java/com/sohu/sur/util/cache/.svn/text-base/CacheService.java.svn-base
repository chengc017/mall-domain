package com.sohu.sur.util.cache;

import com.sohu.sur.base.exception.SucCacheException;


/**
 * 对象,变量存取memcache
 * @author xuewuhao
 *
 */
public interface CacheService {

    /**
     * 查找
     * @param key
     * @return
     * @throws SucCacheException
     */
    public Object get(String key)
            throws SucCacheException;


    /**
     * set key value
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, Object value);


    public void flushEntry(String key);

    /**
     * Thread safe way to initialize and increment a counter.
     *
     * @param key key where the data is stored
     * @return value of incrementer
     */
    public long incr(String key);

    /**
     * Increment a counter.
     *
     * @param key       key where the data is stored
     * @param initValue init value
     * @return value of incrementer
     */
	public long addOrIncr(String key, long initValue);

    /**
     * check key 是否存在
     * @param key
     * @return
     */
    public boolean keyExists(String key);
	

}