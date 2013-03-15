package com.sohu.sur.util.lottery;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.sohu.sur.base.exception.SucScoreException;
import com.sohu.sur.util.ChangeBonusResult;
import com.sohu.sur.util.HttpCilentUtils;

public class UserScoreUtil {

    private static Logger logger= LoggerFactory.getLogger(UserScoreUtil.class);    
	
    //TODO change to get from propertie file
    private static String hostStr = "http://api.ums.sohu.com/"; //"http://10.11.157.37/"; 
    private static String userBonusUrl = "bonus/cur/";
    private static String useMedalUrl = "bonus/check/"; 
    private static String ChangeUserBonusUrl = "change-bonus/";
    
    private static String sucurl = "http://api.ums.sohu.com/change-bonus/";  //"http://10.11.157.37/change-bonus/" ;  
    private static String validationCode = "SLN1Q0";
    private static Gson gson = new Gson();

    /**
     * 获得用户总积分	
     * @param String userId        用户ID
     * @return Long 用户总积分
     * **/
    
    public static Integer getUserBonus(String userId){    			
    	Integer userTotalScore = new Integer(-1); 
    		try{
    			String url = new StringBuffer(hostStr).append(userBonusUrl).append(userId).append("/").toString();
    			logger.info(url);
    			userTotalScore = gson.fromJson(HttpCilentUtils.getResponseText(url), Integer.class);
    	     } catch(JsonSyntaxException e){
    	    	 logger.error(e.toString(),e);
    	     }catch(IOException e){
    	    	 logger.error(e.toString(),e);
    	 	 }
    		if(logger.isDebugEnabled()){
    			logger.debug(userId+" score:"+userTotalScore);
    		}
    	return userTotalScore;
    }
    
    /**
     * 获得用户兑换可使用的勋章
     * @param String requireMedals 所需勋章 
     * @param String userId        用户ID
     * @return Medal 可使用的勋章ID
     * **/
    
    public static UseMedal useMedal(String requireMedals, String userId, int score){
    	UseMedal useMedal = new UseMedal("", 1);
    	if (requireMedals == null) requireMedals = "";
    		try{
    			String url = new StringBuffer(hostStr).append(useMedalUrl).append(URLEncoder.encode(userId, "utf-8")).append("/").append("?score=").append(score).append("&medals=").append(requireMedals).toString();
    			logger.info(url);    			
    			useMedal = gson.fromJson(HttpCilentUtils.getResponseText(url,"utf-8"), UseMedal.class);
    	     } catch(JsonSyntaxException e){
    	    	 logger.error(e.toString(),e);
    	     }catch(IOException e){
    	    	 logger.error(e.toString(),e);
    	 	 }
    	return useMedal;
    }
    
    /**
     * 打开商品详情页获取用户勋章专用 
     * @param String requireMedals 所需勋章 
     * @param String userId        用户ID
     * @return Medal 可使用的勋章ID
     * **/
    
    public static UseMedal useMedal2(String requireMedals, String userId, int score){
    	UseMedal useMedal = new UseMedal("", 1);
    	if (requireMedals == null) requireMedals = "";
    		try{
    			String url = new StringBuffer(hostStr).append(useMedalUrl).append(URLEncoder.encode(userId, "utf-8")).append("/").append("?score=").append(score).append("&checkType=").append("userMedal").append("&medals=").append(requireMedals).toString();
    			logger.info(url);    			
    			useMedal = gson.fromJson(HttpCilentUtils.getResponseText(url,"utf-8"), UseMedal.class);
    	     } catch(JsonSyntaxException e){
    	    	 logger.error(e.toString(),e);
    	     }catch(IOException e){
    	    	 logger.error(e.toString(),e);
    	 	 }
    	return useMedal;
    }

    /**
     * 增减用户总积分	
     * @param String userId        用户ID
     * @return ChangeBonusResult 修改结果
     * **/
    
    public static ChangeBonusResult adjustUserBonus(String userId, Integer score)  throws SucScoreException{    	
    	//兑换扣分操作
    	ChangeBonusResult cr = null;
	    	try{
	    	    String actionCode = "cost";	    		
	    		String signstr = DigestUtils.md5Hex(new StringBuffer(userId).append(actionCode).append(score).append(validationCode).toString()); 
	    		String url = new StringBuffer(sucurl).append(userId).append("/").append(actionCode).append("/?desc=").append("exchange&value=").append(score).append("&sign=").append(signstr).toString();
	    		logger.info(url);
	    		cr = gson.fromJson(HttpCilentUtils.getResponseText(url), ChangeBonusResult.class);
	    	}catch(Exception e){
	    		cr = ChangeBonusResult.ERROR;
	    		e.printStackTrace();
	    		throw new SucScoreException("扣分操作异常。");
	    	}
    	return cr;
    }    
    
    /**
     * 增减用户总积分	
     * @param String userId        用户ID
     * @return ChangeBonusResult 修改结果
     * **/
    
    public static ChangeBonusResult adjustUserBonus(String userId, Integer score, String desc, String actionCode)  throws SucScoreException{    	
    	//兑换扣分操作
    	ChangeBonusResult cr = null;
	    	try{
	    		String signstr = DigestUtils.md5Hex(new StringBuffer(userId).append(actionCode).append(score).append(validationCode).toString());
	    		desc = java.net.URLEncoder.encode(desc, "utf-8");
	    		String url = new StringBuffer(sucurl).append(URLEncoder.encode(userId, "utf-8")).append("/").append(actionCode).append("/").append(signstr).append("/?desc=").append(desc).append("&value=").append(score).toString();
	    		logger.info(url);
	    		cr = gson.fromJson(HttpCilentUtils.getResponseText(url,"UTF-8"), ChangeBonusResult.class);
	    	}catch(Exception e){
	    		cr = ChangeBonusResult.ERROR;
	    		e.printStackTrace();
	    		throw new SucScoreException("扣分操作异常。");
	    	}
    	return cr;
    }
    
    /**
     * 同步商城交易记录	
     * @param id:商城的记录id，可空
     * @param type：兑换、抽奖        
     * @param count：数量
     * @param saleValue:消耗金币        
     * @param giftId：商品id
     * @param giftName：商品名称
     * @param logo：商品logo
     * @param dealId：交易id，可空
     * @param giftType：商品类型 0实物 1优惠劵 2虚拟
     * @return boolean 同步操作结果
     * **/
    public static boolean syncStoreDeals(String userId, String id, String type, String count, String saleValue, String giftId, String giftName, String logo, String dealId, int giftType){
    	boolean ret = false;
    	try{
    		String url = new StringBuffer(hostStr).append("mall-log/").append(userId).toString();
    		String sparam = new StringBuffer("/?id=").append(id).append("&type=").append(type).append("&giftType=").append(giftType).append("&count=").append(count).append("&logo=").append(logo).append("&saleValue=").append(saleValue).append("&giftId=").append(giftId).append("&dealId=").append(dealId).append("&giftName=").append(java.net.URLEncoder.encode(giftName, "utf-8")).toString();
    		url = new StringBuffer(url).append(sparam).toString();
    		logger.info(url);
    		HttpCilentUtils.getResponseText(url,"UTF-8");
    	}catch(Exception e){
    		logger.info(e.getMessage(),e);
    	}
    	return ret;
    }
    /**
     * 同步商城交易记录+增加用户虚拟商品	
     * @param id:商城的记录id，可空
     * @param type：兑换、抽奖        
     * @param count：数量
     * @param saleValue:消耗金币        
     * @param giftId：商品id
     * @param giftName：商品名称
     * @param logo：商品logo
     * @param dealId：交易id，可空
     * @param oldValue：原价
     * @param giftType：商品类型 0实物 1优惠劵 2虚拟
     * @return boolean 同步操作结果
     * **/
    public static boolean syncStoreVirtualDeals(String userId, String id, String type, String count, String saleValue, String giftId, String giftName, String logo, String dealId,int oldValue, int giftType){
    	boolean ret = false;
    	try{
    		String url = new StringBuffer(hostStr).append("mall-log/virtual/").append(java.net.URLEncoder.encode(userId, "utf-8")).toString();
    		String sparam = new StringBuffer("/?id=").append(id).append("&type=").append(type).append("&giftType=").append(giftType).append("&count=").append(count).append("&logo=").append(logo).append("&oldValue=").append(oldValue).append("&saleValue=").append(saleValue).append("&giftId=").append(giftId).append("&dealId=").append(dealId).append("&giftName=").append(java.net.URLEncoder.encode(giftName, "utf-8")).toString();
    		url = new StringBuffer(url).append(sparam).toString();
    		logger.info(url);
    		HttpCilentUtils.getResponseText(url,"UTF-8");
    		ret = true;
    	}catch(Exception e){
    		logger.info(e.getMessage(),e);
    	}
    	return ret;
    }
    public static void main(String[] args) {
		try {
			UserScoreUtil.adjustUserBonus("wocsok@sohu.com", -5, "sdfsfd", "newBlog");
		} catch (SucScoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
