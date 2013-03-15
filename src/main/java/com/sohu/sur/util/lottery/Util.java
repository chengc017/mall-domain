package com.sohu.sur.util.lottery;

import javax.servlet.http.HttpServletRequest;

import com.sohu.sur.util.ConfigManager;


public class Util {
	
	
	/**
	 * 获取域ID
	 * @param httpRequest
	 * @return
	 */
	public static Integer getRegionId(HttpServletRequest httpRequest){
		
		try{
			String result = null;
			
			String host = httpRequest.getHeader("Host");
			
			if(host!=null && !host.equals("")){
				if(host.indexOf(":")!=-1){
					host = host.substring(0, host.indexOf(":"));
				}
				result = ConfigManager.instance().getProperty("suc", host);
			}
			
			if(result == null || result.equals("")){
				result = "21";
			}
			
			return Integer.parseInt(result);
		}catch(Exception e){
			e.printStackTrace();
			
			return 21;//默认女人
		}
		
	}


}
