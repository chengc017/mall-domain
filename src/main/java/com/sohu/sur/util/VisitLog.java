package com.sohu.sur.util;

import java.util.Date;

/**
 * 访问记录帮助类
 * 
 * @author xuewuhao
 * 
 */

public class VisitLog{
	//会话id
	private String sessinId;
	//请求来源
	private String refferUrl;
	//请求来源类型:baidu/google/sogou/soso/blog.sohu/i.sohu/pinglun.sohu/self
	private String refferUrlType;
	//当前页
	private String curUrl;
	//当前页标题
	private String curUrlTitle;
	//访问时间
	private Date   cDate;
	//访问ip
	private String ip;
	//用户浏览器
	private String browser;
	//用户操作系统
	private String op;
	//默认为游客未登录 反之为用户passport
	private String uid="visit";
	
	public String getSessinId() {
		return sessinId;
	}
	public void setSessinId(String sessinId) {
		this.sessinId = sessinId;
	}
	public String getRefferUrl() {
		return refferUrl;
	}
	public void setRefferUrl(String refferUrl) {
		this.refferUrl = refferUrl;
	}
	public String getRefferUrlType() {
		return refferUrlType;
	}
	public void setRefferUrlType(String refferUrlType) {
		this.refferUrlType = refferUrlType;
	}
	public String getCurUrl() {
		return curUrl;
	}
	public void setCurUrl(String curUrl) {
		this.curUrl = curUrl;
	}
	public String getCurUrlTitle() {
		return curUrlTitle;
	}
	public void setCurUrlTitle(String curUrlTitle) {
		this.curUrlTitle = curUrlTitle;
	}
	public Date getcDate() {
		return cDate;
	}
	public void setcDate(Date cDate) {
		this.cDate = cDate;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	@Override
	public String toString() {
		return "sessinId=" + sessinId + ", refferUrl=" + refferUrl + ", refferUrlType=" + refferUrlType
				+ ", curUrl=" + curUrl + ", curUrlTitle=" + curUrlTitle + ", cDate=" + DateUtil.date2Str(cDate) + ", ip=" + ip
				
				+ ", browser=" + browser + ", op=" + op + ", uid=" + uid;
	}
	
}