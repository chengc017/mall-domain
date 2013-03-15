package com.sohu.sur.util;

import java.util.LinkedHashMap;
import java.util.Map;

import com.sohu.sur.util.gift.GiftEnumUtils.GiftType;

public class OrderEnumUtils {

	public static Map giftPhysicsTypeMap = null;

	public static synchronized Map getGiftPhysicsTypeMap() {
		if (giftPhysicsTypeMap == null) {
			giftPhysicsTypeMap = new LinkedHashMap();

			giftPhysicsTypeMap.put(3, "全部");
			giftPhysicsTypeMap.put(0, "实物");
			giftPhysicsTypeMap.put(1, "优惠券");
			giftPhysicsTypeMap.put(2, "虚拟");
		}

		return giftPhysicsTypeMap;
	}

	private static Map<String, String> giftTypeMap = null;

	public static synchronized Map getGiftTypeMap() {
		if (giftTypeMap == null) {
			giftTypeMap = new LinkedHashMap<String, String>();

			giftTypeMap.put("3", "全部");
			giftTypeMap.put("1", "兑换");
			giftTypeMap.put("2", "抽奖");
		}

		return giftTypeMap;
	}

	public static Map orderstatus = null;

	public static synchronized Map getorderstatus() {
		if (orderstatus == null) {
			orderstatus = new LinkedHashMap();
			orderstatus.put(6, "全部");
			orderstatus.put(0, "待处理");
			orderstatus.put(1, "已确认正在备货");
			orderstatus.put(2, "已经发货");
			orderstatus.put(3, "已完成");
			orderstatus.put(4, "已取消");
			orderstatus.put(5, "已作废");

		}

		return orderstatus;
	}
	public enum StatusEm {
		STATUS_WAIT(0, "待处理"), STATUS_ALREADY(1, "已确认正在备货"), STATUS_SEND(2, "已经发货"), STATUS_FINISHED(3, "已完成"), STATUS_CANCEL(4,
				"已取消"), STATUS_QUIT(5, "已作废");
		private String name;
		private Integer code;

		private StatusEm(Integer c, String name) {
			this.code = c;
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public Integer getCode() {
			return code;
		}

		
	}
	enum TestEm {
		T1(0, "待处理"), T2(1, "已确认正在备货"), T3(2, "已经发货"), T4(3, "已完成"), T5(4,
				"已取消"), T6(5, "已作废");
		private String name;
		private Integer code;

		private TestEm(Integer c, String name) {
			this.code = c;
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public Integer getCode() {
			return code;
		}

		public static String value(Integer code) {
			System.out.println(code);
			for (TestEm t : TestEm.values()) {
				if (t.code.equals(code)) {
					return t.getName();
				}

			}
			return null;
		}
	}
}
