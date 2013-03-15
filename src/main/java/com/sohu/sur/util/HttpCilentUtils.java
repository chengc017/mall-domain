package com.sohu.sur.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HttpCilentUtils {
	 
    private Logger logger= LoggerFactory.getLogger(this.getClass().getName());    
	
	 private static int  connectionTimeoutMillis = 1000;
	 private static int  socketTimeoutMillis = 5000;

	 public static String getResponseText(String url) throws HttpException, IOException{
		 String responseBody = "";
		 HttpClient client = new HttpClient();
		 
		 List<Header> headers = new ArrayList<Header>();
	     headers.add(new Header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"));   
	     client.getHostConfiguration().getParams().setParameter("http.default-headers", headers);
	     
		    client.getParams().setParameter("http.useragent", "store Client");
		    client.getParams().setParameter("http.connection.timeout",new Integer(socketTimeoutMillis));

		    GetMethod method  = new GetMethod();
		    FileOutputStream fos = null;

		    try {

		      method.setURI(new URI(url, true));
		      int returnCode = client.executeMethod(method);

		      if(returnCode != HttpStatus.SC_OK) {
		        System.err.println(
		          "Unable to fetch default page, status code: " + returnCode);
		      }
		      responseBody = new String(method.getResponseBody(),"UTF-8");
		    } finally {
		      method.releaseConnection();
		      if(fos != null) try { fos.close(); } catch (Exception fe) {}
		    }
	     return responseBody;
	 }//responseText
	 
	 public static String getResponseText(String url, String charset) throws HttpException, IOException{
		 String responseBody = "";
		 HttpClient client = new HttpClient();
		 
		 List<Header> headers = new ArrayList<Header>();
	     headers.add(new Header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"));   
	     client.getHostConfiguration().getParams().setParameter("http.default-headers", headers);
	     
		    client.getParams().setParameter("http.useragent", "store Client");
		    client.getParams().setParameter("http.connection.timeout",new Integer(socketTimeoutMillis));

		    GetMethod method  = new GetMethod();
		    //method.getParams().setUriCharset("GB2312");
		    FileOutputStream fos = null;

		    try {

		      //method.setURI(new URI(url, false, "GB2312"));
		      method.setURI(new URI(url, true));
		      int returnCode = client.executeMethod(method);

		      if(returnCode != HttpStatus.SC_OK) {
		        System.err.println(
		          "Unable to fetch default page, status code: " + returnCode);
		      }
		      responseBody = new String(method.getResponseBody(),charset);
		    } finally {
		      method.releaseConnection();
		      if (fos != null)
				try {
					fos.close();
				} catch (Exception fe) {
				}
		    }
	     return responseBody;
	 }//responseText
	 	
	 public static void main(String args[]){

	 }//
	 
}
