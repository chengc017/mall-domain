package com.sohu.sur.util.cache.impl;

import net.rubyeye.xmemcached.MemcachedClient;

import com.sohu.sur.base.exception.CacheException;
import com.sohu.sur.util.cache.PageCacheService;


public class PageCacheServiceImpl implements PageCacheService {


    private MemcachedClient pageMemcachedClient;

    public void setMemcachedClient(MemcachedClient pageMemcachedClient) {
        this.pageMemcachedClient = pageMemcachedClient;
    }

    public PageCacheServiceImpl() {
        super();
    }



    @Override
    public boolean add(String key, Object obj) throws CacheException {

        boolean ret = false;
        try {
            ret = pageMemcachedClient.add(key, 0, obj);
        } catch (Exception e) {
            throw new CacheException(e.getMessage(), e);
        }
        return ret;

    }

    @Override
    public boolean delete(String key) throws CacheException {

        boolean ret = false;
        try {
        	pageMemcachedClient.delete(key);
        } catch (Exception e) {
            throw new CacheException(e.getMessage(), e);
        }
        return ret;

    }


    @Override
    public Object get(String key) throws CacheException {

        Object result = null;

        try {
            result = pageMemcachedClient.get(key);
        } catch (Exception e) {
            throw new CacheException(e.getMessage(), e);
        }

        return result;
    }



    @Override
    public void setKey(String key, Object obj) throws CacheException {
        setKey(key, 0, obj);
    }

    @Override
    public void setKey(String key, int expire, Object obj) throws CacheException {

        try {
        	pageMemcachedClient.set(key, expire, obj);
        } catch (Exception e) {
            throw new CacheException(e.getMessage(), e);
        }

    }



    @Override
    public void init() {

        try {
        	pageMemcachedClient.flushAll();
        } catch (Exception e) {
            throw new CacheException(e.getMessage(), e);
        }
    }

}
