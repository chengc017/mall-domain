package com.sohu.sur.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class PagerFacade {
	private static Log log = LogFactory.getLog(PagerFacade.class);

	/**
	 * Offset
	 * 
	 * @return offset
	 */
	public static int getOffset(String pagerOffset) {

		int offset = 0;
		try {
			offset = Integer.parseInt(pagerOffset);
		} catch (NumberFormatException e) {
			if (log.isDebugEnabled()) {
				log.debug("Error during get pager.offset", e);
			}
		}
		return offset;
	}

	public static int getOffset() {
		return 0;
	}

	/**
	 * maxPageItems
	 * 
	 * @return maxPageItems
	 */
	public static int getMaxPageItems() {
		int interval = PaginationSupport.DEFAULT_MAX_PAGE_ITEMS;

		return interval;
	}

	/**
	 * DEFAULT_MAX_PAGE_ITEMS
	 * 
	 * @return DEFAULT_MAX_INDEX_PAGES
	 */
	public static int getMaxIndexPages() {
		int maxIndexPages = PaginationSupport.DEFAULT_MAX_INDEX_PAGES;

		return maxIndexPages;
	}

	public static String getIndex() {
		return PaginationSupport.DEFALUT_INDEX;
	}

}
