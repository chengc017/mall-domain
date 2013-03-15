package com.sohu.sur.util.gift;

/*
 * 创建日期 2004-5-9
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */


import java.security.*;

public class StrMD5
{
    private String m_result;
    private String m_key;
    private MessageDigest currentAlgorithm;

    public StrMD5()
    {    	
    }
    
    public StrMD5(String key)
    {
        this.m_key=key;
        try
        {           
           currentAlgorithm = MessageDigest.getInstance("MD5");
           this.m_result = computeDigest(loadBytes(this.m_key)) ;//+ "\r\n";
        } catch(NoSuchAlgorithmException e)
        {
            System.out.println("MD5 algorithm not available.");
        }
    }

    public String getResult()
	{
		return this.m_result;
	}
	
	public  String getMD5Str(String key) {
			try{
			   currentAlgorithm = MessageDigest.getInstance("MD5");
			   return computeDigest(loadBytes(key));
			} catch(NoSuchAlgorithmException e) {
				System.out.println("MD5 algorithm not available.");
			}
			return null;

	}
	
	public  String getMD5Str(String key,String charset) {
		try{
		   currentAlgorithm = MessageDigest.getInstance("MD5");
		   return computeDigest(loadBytes(key,charset));
		} catch(NoSuchAlgorithmException e) {
			System.out.println("MD5 algorithm not available.");
		}
		return null;

	}
	
	
    static private byte[] loadBytes(String name)
    {
       byte [] buffer = name.getBytes();
         //System.out.println(name);
        //System.out.println("Begin...");
        return(buffer);
    }
    
    static private byte[] loadBytes(String name,String charset)
    {
    	byte [] buffer = null;
    	try{
    		buffer = name.getBytes(charset);
    	}catch(Exception e){}

    	return(buffer);
    }

    
    private  String computeDigest(byte[] b){
      currentAlgorithm.reset();
      currentAlgorithm.update(b);
      byte[] hash = currentAlgorithm.digest();
      String d = "";
      int usbyte = 0;  // unsigned byte
      for (int i = 0; i < hash.length; i+=2){   // format with 2-byte words with spaces.
       usbyte = hash[i] & 0xFF ;   // byte-wise AND converts signed byte to unsigned.
       if(usbyte<16)
        d += "0" + Integer.toHexString(usbyte);   // pad on left if single hex digit.
         else
        d +=       Integer.toHexString(usbyte);
       usbyte = hash[i+1] & 0xFF ;   // byte-wise AND converts signed byte to unsigned.
       if(usbyte<16)
        d += "0" + Integer.toHexString(usbyte) ;//+ "  ";   // pad on left if single hex digit.
         else
        d +=       Integer.toHexString(usbyte);// + "  ";
      }
      //return d.toUpperCase();
	  return d.trim().toLowerCase();
    }
};