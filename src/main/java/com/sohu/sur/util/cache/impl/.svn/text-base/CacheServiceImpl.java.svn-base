package com.sohu.sur.util.cache.impl;

import java.util.concurrent.TimeoutException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import com.sohu.sur.base.exception.SucCacheException;
import com.sohu.sur.filter.CacheResponseFilter;
import com.sohu.sur.util.cache.CacheService;

public class CacheServiceImpl implements CacheService {

	private final static Logger logger = LoggerFactory.getLogger(CacheServiceImpl.class);

	private MemcachedClient memcachedClient;

	private int compressThreshold;

	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}

	public void setCompressThreshold(int compressThreshold) {
		this.compressThreshold = compressThreshold;

		this.memcachedClient.getTranscoder().setCompressionThreshold(compressThreshold);
	}

	public Object get(String key) throws SucCacheException {

		Object result = null;

		try {
			logger.info("get key " + key);
			result = memcachedClient.get(key);
		} catch (Exception e) {
			logger.error("get", e);
			throw new SucCacheException(e.getMessage(), e);
		}

		return result;
	}

	public void flushEntry(String key) {
		try {
			logger.info("flush key " + key);
			memcachedClient.delete(key);
		} catch (TimeoutException e) {
			logger.error("TimeoutException", e);
		} catch (InterruptedException e) {
			logger.error("InterruptedException", e);
		} catch (MemcachedException e) {
			logger.error("MemcachedException", e);
		}
	}

	public long incr(String key) {
		try {
			logger.info("incr key " + key);
			return memcachedClient.incr(key, 1);
		} catch (TimeoutException e) {
			logger.error("TimeoutException", e);
		} catch (InterruptedException e) {
			logger.error("InterruptedException", e);
		} catch (MemcachedException e) {
			logger.error("MemcachedException", e);
		}
		return 0;
	}

	
	public long addOrIncr(String key, long initValue) {

		try {
			logger.info("addOrIncr key " + key);
			return memcachedClient.incr(key, 1, initValue);
		} catch (TimeoutException e) {
			logger.error("TimeoutException", e);
		} catch (InterruptedException e) {
			logger.error("InterruptedException", e);
		} catch (MemcachedException e) {
			logger.error("MemcachedException", e);
		}
		return 0;
	}

	public boolean set(String key, Object value) {

		try {
			logger.info("set key " + key);
			return memcachedClient.set(key, 0, value);
		} catch (TimeoutException e) {
			logger.error("TimeoutException", e);
		} catch (InterruptedException e) {
			logger.error("InterruptedException", e);
		} catch (MemcachedException e) {
			logger.error("MemcachedException", e);
		}

		return false;
	}

	public boolean keyExists(String key) {

		boolean flag = false;
		try {
			String valueStr = memcachedClient.get(key);
			if(valueStr!=null&&valueStr.length()>0){
				logger.info("cache key="+key+"value="+valueStr);
				flag = true;
			}
		} catch (Exception e) {
			logger.error("MemcachedException", e);
		}
		return flag;
	}

}
