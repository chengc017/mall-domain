package com.sohu.sur.util;

import java.net.URLEncoder;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.sohu.sur.base.exception.SucScoreException;

public class PaybackUtil {
    
    private static Logger logger= LoggerFactory.getLogger(PaybackUtil.class);

    private static String sucurl = "http://api.ums.sohu.com/change-bonus/";  //"http://10.11.157.37/change-bonus/" ;  
    private static String validationCode = "A0vMoE";
    private static Gson gson = new Gson();

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
	    		logger.error(e.getMessage(),e);
	    		throw new SucScoreException("扣分操作异常。");
	    	}
    	return cr;
    }
    
  
}
