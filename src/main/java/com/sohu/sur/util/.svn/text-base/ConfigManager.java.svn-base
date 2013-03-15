package com.sohu.sur.util;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConfigManager {

	protected final Log log = LogFactory.getLog(getClass());
	private static ConfigManager configManger = new ConfigManager();

	private volatile static Map<String, Properties> proMap = new ConcurrentHashMap<String, Properties>();

	private ConfigManager() {

	}

	public static ConfigManager instance() {
		return configManger;
	}

	/**
	 * 
	 * @param profileName
	 *            配置文件名（不包括后缀）
	 * @param key
	 *            属性key值
	 * @return
	 */
	public String getProperty(String profileName, String key) {
		if (key == null || profileName == null) {
			throw new IllegalArgumentException("key is null");
		}
		Properties properties = proMap.get(profileName);

		if (properties == null) {
			synchronized (this) {

				if (properties == null) {

					properties = this.loadProps(profileName);

					if (properties != null) {
						proMap.put(profileName, properties);
					} else {
						return null;
					}
				}
			}
		}
		
		String value = properties.getProperty(key);
		return value;
	}
	
	public static String getKeyByValue(String profileName, String value){
		String url = "";
		
		Properties p = proMap.get(profileName);

		if (p == null) {
			p = ConfigManager.instance().loadProps(profileName);
			proMap.put(profileName, p);
		}
		if(p!=null && p.keySet()!=null && p.keySet().size()>0){
			Iterator it = p.keySet().iterator();
			while(it.hasNext()){
				String key = (String)it.next();
				
				if(p.get(key).equals(value)){
					
					url = "http://"+key;
					
				}
			}
		}
		
		return url;
	}

	/**
	 * 
	 * @param profileName
	 *            配置文件名（不包括后缀）
	 * @param key
	 *            属性key值
	 * @return
	 */
	public int getIntProperty(String profileName, String key) {
		if (key == null || profileName == null) {
			throw new IllegalArgumentException("key is null");
		}

		String intValue = this.getProperty(profileName, key);

		try {
			return Integer.parseInt(intValue);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public Properties loadProps(String proFileName) {

		log.debug("Getting Config");

		Properties properties = null;

		InputStream in = null;

		try {
			properties = new Properties();

			String fileName = "/" + proFileName + ".properties";

			in = getClass().getResourceAsStream(fileName);
			properties.load(in);

			log.info("Successfully  proFileName=" + proFileName + " load Properties:" + properties);

		} catch (Exception e) {
			log.error("Error reading " + proFileName + " in loadProps() " + e.getMessage(),e);
			log.error("Ensure the " + proFileName + " file is readable and in your classpath.",e);
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return properties;
	}
	
	/**
	 * 
	 * @param profileName
	 *            配置文件名（不包括后缀）
	 * @param key
	 *            属性key值
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public Map getPropertyList(String profileName, String key) throws UnsupportedEncodingException {
		if (key == null || profileName == null) {
			throw new IllegalArgumentException("key is null");
		}
		Properties properties = proMap.get(profileName);

		if (properties == null) {
			synchronized (this) {

				if (properties == null) {

					properties = this.loadProps(profileName);

					if (properties != null) {
						proMap.put(profileName, properties);
					} else {
						return null;
					}
				}
			}
		}
		//LinkedHashSet set = new LinkedHashSet();   
		Map<String, String> mmap = new HashMap<String, String>();
		Iterator iter = properties.stringPropertyNames().iterator();
		while(iter.hasNext()){
			String str = (String) iter.next();
			if(str.indexOf(key)!=-1){
				String str2 = new String(getProperty(profileName,str).getBytes("ISO-8859-1"),"GBK");
				if(str2.indexOf(",")!=-1){
					String[] str3 = str2.split("[,]");
					mmap.put(str3[0], str3[1]);
				}
			}
		}
		//return value;
		return mmap;
	}
	
}
