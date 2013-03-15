package com.sohu.sur.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.sohu.sur.base.exception.CacheException;
import com.sohu.sur.util.cache.PageCacheService;

/**
 * 页面过滤器 将返回结果存入memcache
 * 
 * @author xuewuhao
 * 
 */
@Component
public class CacheResponseFilter extends GenericFilterBean {

	private final static Logger logger = LoggerFactory.getLogger(CacheResponseFilter.class);

	@Autowired
	private PageCacheService pageCacheService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {

		// page content will be save in this stream
		ByteArrayOutputStream cacheContent = new ByteArrayOutputStream();

		HttpServletResponseWrapper cacheResponseWrapper = new CacheResponseWrapper((HttpServletResponse) response,
				cacheContent);

		chain.doFilter(request, cacheResponseWrapper);

		String uri = ((HttpServletRequest) request).getRequestURI();
		if (uri.startsWith("/store")) {
			uri = StringUtils.substringAfter(uri, "/store");
		}

		String cacheKey = DigestUtils.shaHex(uri);
		logger.info("uri={}, cacheKey={}", uri, cacheKey);

		String pageContent = cacheContent.toString(response.getCharacterEncoding());

		try {
			pageCacheService.setKey(cacheKey, pageContent); // 不设定过期时间
		} catch (CacheException e) {
			logger.warn("can't save page content into memcached", e);
		}

		// write to client
		ServletOutputStream outputStream = response.getOutputStream();
		outputStream.write(cacheContent.toByteArray());
	}

	@Override
	public void afterPropertiesSet() throws ServletException {
		super.afterPropertiesSet();
		if (pageCacheService == null) {
			throw new ServletException("no pageCacheService specified!");
		}
	}
}
