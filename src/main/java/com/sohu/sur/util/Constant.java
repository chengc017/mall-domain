package com.sohu.sur.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sohu.sur.util.cache.CacheService;

public class Constant {
	private static Logger logger = LoggerFactory.getLogger(Constant.class);
	// 初始化页面
	public static int page = 1;

	// 初始化每页数量
	public static int pageSize = 10;

	// 增加c 删除d 修改u 查看r
	public static String OPTTYPENULL = "N";
	public static String OPTTYPEINSERT = "C";
	public static String OPTTYPEDELETE = "D";
	public static String OPTTYPEUPDATE = "U";
	public static String OPTTYPESELECT = "R";

	public static final Map<String, String> STATESUSER = new HashMap<String, String>();
	static {
		STATESUSER.put("1", "有效");
		STATESUSER.put("0", "无效");
	}

	// 中奖方案1 中奖用户条件
	public static final Map<String, String> REWARDSTATION = new HashMap<String, String>();
	static {
		REWARDSTATION.put("0", "无");
		REWARDSTATION.put("1", "缝数即中");
		REWARDSTATION.put("2", "抽即中");
	}

	/**
	 * 增加浏览数
	 * 
	 * @param cacheService
	 * @param giftId
	 */
	public synchronized static void addGiftViewNum(CacheService cacheService, long giftId, long viewNum) {
		try {
			cacheService.set("gift_view_num" + giftId,viewNum+1);
		} catch (Exception e) {
			logger.error("addGiftViewNum", e);
		}
	}

	/**
	 * 查找浏览数
	 * @param cacheService
	 * @param giftId
	 * @param viewNum 数据库存的浏览数
	 * @return
	 */
	public static long fetchGiftViewNum(CacheService cacheService, long giftId, long viewNum) {
		long viewNumTmp = 0;
		String key = "gift_view_num" + giftId;
		try {
			Object obj = cacheService.get(key);
			if (obj == null) {
				cacheService.set(key, viewNum);
				viewNumTmp = viewNum;
			} else
				viewNumTmp = Long.valueOf(obj.toString());
		} catch (Exception e) {
			logger.error("fetchGiftViewNum", e);
		}
		return viewNumTmp;
	}
}
