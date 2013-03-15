package com.sohu.sur.util.cache;

/**
 * 页面存取 编码格式GBK
 * @author xuewuhao
 *
 */
public interface PageCacheService {

    /**
     * put Ojbject
     *
     * @param key
     * @param obj
     */
    boolean add(String key, Object obj);

    /**
     * remove Ojbject
     *
     * @param key
     */
    boolean delete(String key);

    /**
     * get Ojbject
     *
     * @param key
     * @return Ojbject
     */
    Object get(String key);


    /**
     * set Object to Key
     */

    void setKey(String key, Object obj);


    void setKey(String key, int expire, Object obj);
	
	/**
     * init cacheContent
     */
	void init();
	
}
