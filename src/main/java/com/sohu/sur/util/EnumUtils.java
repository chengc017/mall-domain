package com.sohu.sur.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 枚举类型工具类
 * 
 * @author xiayanming
 *
 */
public class EnumUtils {

	
	/**************************************************************
	 * 
	 * 状态常量及相关方法
	 * 
	 **************************************************************/
	
	public static final Integer STATUS_DELETE = status.删除.ordinal();
	public static final Integer STATUS_NORMAL = status.正常.ordinal();
	public static final Integer STATUS_UNCERTAIN = status.不确定.ordinal();
	
	//数据状态
	public enum status {
		//1:正常,0:删除,2:不确定
		删除, 正常, 不确定
	}
	
	public static Map<Integer, String> statusMap = null;
	
	public static synchronized Map<Integer, String> getStatusMap()
	{
		if(statusMap == null)
		{
			statusMap = new LinkedHashMap<Integer, String>();
			
			statusMap.put(STATUS_NORMAL, status.正常.name());
			statusMap.put(STATUS_DELETE, status.删除.name());
			statusMap.put(STATUS_UNCERTAIN, status.不确定.name());
		}
		
		return statusMap;
	}
	
	/**************************************************************
	 * 
	 * 是和否的常量及相关方法
	 * 
	 **************************************************************/
		
	public static final Integer YES = yesOrNo.是.ordinal();
	public static final Integer NO = yesOrNo.否.ordinal();
	
	public enum yesOrNo {
		否, 是
	}
	
	public static Map yesOrNoMap = null;
	
	public static synchronized Map getYesOrNoMap()
	{
		if(yesOrNoMap == null)
		{
			yesOrNoMap = new LinkedHashMap();
			
			yesOrNoMap.put(YES, yesOrNo.是.name());
			yesOrNoMap.put(NO, yesOrNo.否.name());
		}
		
		return yesOrNoMap;
	}
	
	
	/**************************************************************
	 * 
	 * 用户相关常量
	 * 
	 * 专有属性相关常量
	 * 
	 **************************************************************/
	
	//数据类型,名称
	public enum DataTypeName {
		整型, 实数, 字符, 日期, 时间, TEXT, 长整数型
	}
	
	//控件类型
	public enum ItemType {
		//1：文本框 2：文本域 3：下拉选择框 4：复选框 5:单选钮 6:复选下拉选择框
		文本框, 文本域, 下拉选择框, 复选框, 单选钮, 复选下拉选择框, //文件上传, LOGO图片
	}
	
//	是否允许为空,是否为必填项
	public enum Required {
		//0则允许为空，为1不允许为空
		非必填, 必填
	}
	
	/**
	 * 数据类型常量
	 */
	public static final int DATA_TYPE_INT = 1;
	public static final int DATA_TYPE_FLOAT = 2;
	public static final int DATA_TYPE_VARCHAR = 3;
	public static final int DATA_TYPE_DATE = 4;
	public static final int DATA_TYPE_TIME = 5;
	public static final int DATA_TYPE_TEXT = 6;
	public static final int DATA_TYPE_BIGINT = 7;	
	
	/**
	 * 控件类型， 类别常量
	 */
	
	public static final Integer ITEM_TYPE_TEXT = ItemType.文本框.ordinal() +1;//1
	public static final Integer ITEM_TYPE_TEXTAREA = ItemType.文本域.ordinal() +1;//2
	public static final Integer ITEM_TYPE_CHECKBOX = ItemType.复选框.ordinal() +1;//4
	public static final Integer ITEM_TYPE_CHECKBOX2 = ItemType.复选下拉选择框.ordinal() +1;//6
	//public static final Integer ITEM_TYPE_UPLOADFILE = ItemType.文件上传.ordinal()+1;//7
	//public static final Integer ITEM_TYPE_LOGO = ItemType.LOGO图片.ordinal() +1;//8
	
//	数据类型
	public enum DataType {
		//1：整型 2:实数，3：字符 4:日期 5：时间 6：TEXT 7:长整数型（主要用在id类型上）
		INT, FLOAT, VARCHAR, DATE, TIME, TEXT, BIGINT		
	}	
	
	
	private static Map itemMap = null;//控件类型
	/**
	 * 获得控件列表
	 * 文本框, 文本域, 下拉选择框, 复选框, 单选钮
	 * @return
	 */
	public static synchronized Map<Integer, String> getItemMap()
	{
		if(itemMap == null)
		{
			itemMap = new LinkedHashMap<Integer, String>();
			
			itemMap.put(0, "所有");
			itemMap.put(ItemType.文本框.ordinal()+1, ItemType.文本框.name());
			itemMap.put(ItemType.文本域.ordinal()+1, ItemType.文本域.name());
			itemMap.put(ItemType.下拉选择框.ordinal()+1, ItemType.下拉选择框.name());
			itemMap.put(ItemType.复选框.ordinal()+1, ItemType.复选框.name());
			itemMap.put(ItemType.单选钮.ordinal()+1, ItemType.单选钮.name());
		}
		
		return itemMap;
	}
	
	
	private static Map pubMap = null;//是否发布map
	/**
	 * 获得状态列表
	 * 
	 * @return
	 */
	public static synchronized Map<Integer, String> getPubMap()
	{
		if(pubMap == null)
		{
			pubMap = new LinkedHashMap<Integer, String>();
		
			pubMap.put(PublishStatus.Published.ordinal(), PublishStatus.Published.getName());
			pubMap.put(PublishStatus.NoPublish.ordinal(), PublishStatus.NoPublish.getName());
		}
		
		return pubMap;
	}
	
	/**
	 * 发布状态常量
	 */
	public static final Integer PUBLISH_NOPUBLISHED = 0;//未发布
	public static final Integer PUBLISH_PUBLISHED = 1;//已发布
	
//	状态： 0:未发布,1：已发布
	public enum PublishStatus {
		
		NoPublish{
			public String getName(){
				return "否";
			}
			
			public int getValue(){
				return PUBLISH_NOPUBLISHED;
			}			
		},
		Published{			
			public String getName(){
				return "是";
			}
			
			public int getValue(){
				return PUBLISH_PUBLISHED;
			}
			
		};
		
		
		//状态名称
		public abstract String getName();
		
		//状态值
		public abstract int getValue(); 	
		
	}
	
	/**
	 * 根据发布状态获取发布状态信息
	 * 
	 * @return
	 */
	public static PublishStatus getPublishStatus(int publishStatusValue) {
		PublishStatus[] publishStatusValues = PublishStatus.values();
		
		if(publishStatusValue > publishStatusValues.length)
			throw new ArrayIndexOutOfBoundsException();		
		
		
		for(PublishStatus publishStatus : publishStatusValues)
		{
			if(publishStatus.getValue() == publishStatusValue)
			{
				return publishStatus;
			}			
		}
		
		return null;
	}
	
	
//	是否为必填项map
	private static Map requiredMap = null;
	
	/**
	 * 是否为必填项的列表
	 * @return
	 */
	public static synchronized Map getRequiredMap()
	{
		if(requiredMap == null)
		{
			requiredMap = new LinkedHashMap();
			
			requiredMap.put(Required.必填.ordinal(), Required.必填.name());
			requiredMap.put(Required.非必填.ordinal(), Required.非必填.name());
		}
		
		return requiredMap;
	}
	
	private static Map sexMapShow = null;
	
	/**
	 * 用户显示，默认全部
	 * @return
	 */
	public static synchronized Map<Integer, String> getSexMapShow()
	{
		if(sexMapShow == null)
		{
			sexMapShow = new LinkedHashMap<Integer, String>();
			
			sexMapShow.put(-1, "所有");//所有
			sexMapShow.put(YES, "男");//1男
			sexMapShow.put(NO, "女");//0女
		}
		
		return sexMapShow;
	}
	
	private static Map sexMap = null;
	/**
	 * 用户添加，
	 * @return
	 */
	public static synchronized Map<Integer, String> getSexMap()
	{
		if(sexMap == null)
		{
			sexMap = new LinkedHashMap<Integer, String>();
			
			sexMap.put(YES, "男");//1男
			sexMap.put(NO, "女");//0女
		}
		
		return sexMap;
	}
	
	private static Map freezeMap = null;
	/**
	 * 用户添加，
	 * @return
	 */
	public static synchronized Map<Integer, String> getFreezeMap()
	{
		if(freezeMap == null)
		{
			freezeMap = new LinkedHashMap<Integer, String>();
			
			freezeMap.put(NO, "否");
			freezeMap.put(YES, "是");
		}
		
		return freezeMap;
	}
	
	/**
	 * 数据类型,根据数据类型type得到DataType
	 * 
	 * @param type
	 * @return
	 */
	public static DataType getDataType(int type) {
		 
		DataType[] dataTypes =  DataType.values();
		
		if(type-1 > dataTypes.length )
			throw new ArrayIndexOutOfBoundsException();
		
		DataType dataType = dataTypes[type-1];
		
		return dataType;
	}
	
	public static DataTypeName getDataTypeName(int type) {
		
		DataTypeName[] dataTypeNames =  DataTypeName.values();
		
		if(type-1 > dataTypeNames.length )
			throw new ArrayIndexOutOfBoundsException();
		
		DataTypeName dataTypeName = dataTypeNames[type-1];
		
		return dataTypeName;
	}
	
	/**
	 * 根据控件类型获取控件的title
	 * 
	 * @param type		控件id值
	 * @return
	 */
	public static ItemType getItemTypeName(int type) {
		
		ItemType[] itemTypeNames =  ItemType.values();
		
		if(type-1 > itemTypeNames.length )
			throw new ArrayIndexOutOfBoundsException();
		
		ItemType itemTypeName = itemTypeNames[type-1];
		
		return itemTypeName;
	}
	
	/**
	 * 域ID和目录url设置
	 * 
	 */
//	public static Map<String, String> urlMap = null;
//	
//	public static Map<String, String> getUrlMap()
//	{
//		if(urlMap == null)
//		{
//			urlMap = new LinkedHashMap<String,  String>();
//			
//			urlMap.put("21", "wm");//女人
//			urlMap.put("25", "sports");//体育
//		}
//		
//		return urlMap;
//	}

	
}
