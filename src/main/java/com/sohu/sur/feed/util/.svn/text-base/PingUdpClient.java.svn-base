package com.sohu.sur.feed.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author guojunyang
 * 
 */
public class PingUdpClient {
	private static final Log log = LogFactory.getLog(PingUdpClient.class);

	private static final byte[] magic = { 'F', 'E', 'E', 'D' };

	private static InetAddress serverAddress;

	private static int serverPort;
 
	private static int localPort;
	
	public static final String TITLE_SPLIT = ":";
	public static final String DESC_SPLIT = "||";

    
	static {
	    InputStream propis = PingUdpClient.class.getResourceAsStream("/feed_client.properties");

		
		Properties properties = new Properties();
		try {
			properties.load(propis);
		} catch (IOException e) {
			log.error("Newsfeed initialization error, can't load feed_client.properties properties file!");
		}
		serverPort = Integer.parseInt(properties.getProperty("feed.server.port", "2009"));
		localPort = Integer.parseInt(properties.getProperty("feed.local.port", "2007"));
		try {
		serverAddress = InetAddress.getByName(properties.getProperty("feed.server.ip","10.11.15.131"));
		} catch (UnknownHostException e) {
			log.error("#### ping client init UnknownHostException, ip="+ serverAddress.getHostAddress());
		}
	}

	/**
	 * 对各个应用text，desc的格式不同，type的值也不同
	 * @param passport
	 * @param text
	 * @param desc
	 * @param type
	 */
	public static void sendMessage(String passport, String text, String desc,
			short type,short version) {
		long start = System.currentTimeMillis();
		byte[] data = composeData(passport, text, desc, type,version);
		if (data == null)
			return;
		DatagramSocket serverSocket = null;
		try {
			/**
			 * 此处可以进一步优化，把DatagramSocket做成一个对象池
			 */
			InetSocketAddress d ;
			serverSocket = new DatagramSocket(localPort);
			log.info("Sending Message to  :"+serverAddress+" : "+serverPort+"\r\n");
			log.info("Sending data's length is :"+data.length);
			serverSocket.send(new DatagramPacket(data, 0, data.length,serverAddress, serverPort));
		} catch (UnknownHostException e) {
			log
					.warn("#### ping client sendMessage UnknownHostException, serverAddress="
							+ serverAddress.getHostAddress()
							+ ", serverPort="
							+ serverPort);
		} catch (SocketException e) {
			log
					.warn("#### ping client sendMessage SocketException, serverAddress="
							+ serverAddress.getHostAddress()
							+ ", serverPort="
							+ serverPort + ", e=" + e.getMessage());
		} catch (IOException e) {
			log.warn("#### ping client sendMessage IOException, serverAddress="
					+ serverAddress.getHostAddress() + ", serverPort="
					+ serverPort);
		} finally{
			if(serverSocket!=null)
				serverSocket.close();
		}
		long interval = System.currentTimeMillis() - start;
		log.warn("#### ping client: send message time, interval=" + interval);
	}
	
	public static void sendMessage(String passport, String text, String desc,
			short type,short version,String appid,String itemid) {
		long start = System.currentTimeMillis();
		byte[] data = composeData(passport, text, desc, type,version,appid,itemid);
		if (data == null)
			return;
		DatagramSocket serverSocket = null;
		try {
			/**
			 * 此处可以进一步优化，把DatagramSocket做成一个对象池
			 */
			serverSocket = new DatagramSocket(localPort);
			log.info("Sending Message to  :"+serverAddress+" : "+serverPort+"\r\n");
			log.info("Sending data's length is :"+data.length);
			serverSocket.send(new DatagramPacket(data, 0, data.length,serverAddress, serverPort));
		} catch (UnknownHostException e) {
			log
					.warn("#### ping client sendMessage UnknownHostException, serverAddress="
							+ serverAddress.getHostAddress()
							+ ", serverPort="
							+ serverPort);
		} catch (SocketException e) {
			log
					.warn("#### ping client sendMessage SocketException, serverAddress="
							+ serverAddress.getHostAddress()
							+ ", serverPort="
							+ serverPort + ", e=" + e.getMessage());
		} catch (IOException e) {
			log.warn("#### ping client sendMessage IOException, serverAddress="
					+ serverAddress.getHostAddress() + ", serverPort="
					+ serverPort);
		} finally{
			if(serverSocket!=null)
				serverSocket.close();
		}
		long interval = System.currentTimeMillis() - start;
		log.warn("#### ping client: send message time, interval=" + interval);
	}
	
	public static void sendMessageToCR(String content){
		long start = System.currentTimeMillis();
		if(content==null || content.length()<=0)
			return;
		byte[] data =null;
		try {
			data = content.getBytes("GBK");
		} catch (UnsupportedEncodingException e) {
			data= content.getBytes();
		}
		if(data==null)
			return;
		DatagramSocket serverSocket = null;
		try {
			/**
			 * 此处可以进一步优化，把DatagramSocket做成一个对象池
			 */
			serverSocket = new DatagramSocket(localPort);
			serverSocket.send(new DatagramPacket(data, 0, data.length,serverAddress, serverPort));
			log.warn("#### ping client: send message to CR,serverAddress="+serverAddress.getHostAddress()+", serverPort="+serverPort);
		} catch (UnknownHostException e) {
			log.warn("#### ping client sendMessage UnknownHostException, serverAddress="
							+ serverAddress.getHostAddress()
							+ ", serverPort="
							+ serverPort);
		} catch (SocketException e) {
			log
					.warn("#### ping client sendMessage SocketException, serverAddress="
							+ serverAddress.getHostAddress()
							+ ", serverPort="
							+ serverPort + ", e=" + e.getMessage());
		} catch (IOException e) {
			log.warn("#### ping client sendMessage IOException, serverAddress="
					+ serverAddress.getHostAddress() + ", serverPort="
					+ serverPort);
		}catch(Exception e){
			log.warn("### error in sent to cr:"+e);
		}finally{
			if(serverSocket!=null)
				serverSocket.close();
		}
		long interval = System.currentTimeMillis() - start;
		log.warn("#### ping client: send message time, interval=" + interval);
	}
	/*
	 * 将各种类型的对象转换成字节数组，以便在网络传输
	 * +------+------+----------+------+-----+------+------+------+------+---------+----------+---------------+-------------+----------+ |
	 * FEED | pLen | passport | MD5L | MD5 | tLen | text | dLen | desc | Timestamp |type(short)|version(short)|SentVia(byte)| CRC(int) |
	 * +------+------+----------+------+-----+------+------+------+------+---------+----------+---------------+-------------+----------+
	 * 
	 */
	
	private static byte[] composeData(String passport, String text,String desc,short type,short version) {

		int length = magic.length;
		byte[] passportData;
		if (passport == null)
			return null;

		try {
			passportData = passport.getBytes("GBK");
		} catch (UnsupportedEncodingException e) {
			passportData = passport.getBytes();
		}
		length += 4;   // --->FEED
		length += passportData.length;

		String md5 = md5Encode(passport);
		byte[] md5Data;
		try {
			md5Data = md5.getBytes("GBK");
		} catch (UnsupportedEncodingException e) {
			md5Data = md5.getBytes();
		}
		length += 4;
		length += md5Data.length;
        //Handle Text
		byte[] tData;
		if (text == null)
			return null;
		if (log.isDebugEnabled()) {
			log.debug("run() - send text  : |" + text);
		}

		try {
			tData = text.getBytes("GBK");
		} catch (UnsupportedEncodingException e) {
			tData = text.getBytes();
		}
		length += 4;
		length += tData.length;
        //Handle Description
		byte[] dData = null;
		length += 4;
		if (desc != null) {
			try {
				dData = desc.getBytes("GBK");
			} catch (UnsupportedEncodingException e) {
				dData = desc.getBytes();
			}
			length += dData.length;
		}

		length += 8 + 2 + 2 + 1 + 4; // 8 --time |2 -- type | 2 -- version |1 -- sendby |4 -- CRC

		byte[] data = new byte[length + 4];
		int offset = 0;
		ByteArrayUtil.putInt(length, data, offset);
		offset += 4;
		ByteArrayUtil.put(magic, data, offset);
		offset += magic.length;
		ByteArrayUtil.putInt(passportData.length, data, offset);
		offset += 4;
		ByteArrayUtil.put(passportData, data, offset);
		offset += passportData.length;
		ByteArrayUtil.putInt(md5Data.length, data, offset);
		offset += 4;
		ByteArrayUtil.put(md5Data, data, offset);
		offset += md5Data.length;
		ByteArrayUtil.putInt(tData.length, data, offset);
		offset += 4;
		ByteArrayUtil.put(tData, data, offset);
		offset += tData.length;
		//description 允许为空
		if (dData == null) {
			ByteArrayUtil.putInt(0, data, offset);
			offset += 4;
		} else {
			ByteArrayUtil.putInt(dData.length, data, offset);
			offset += 4;
			ByteArrayUtil.put(dData, data, offset);
			offset += dData.length;
		}
        //8
		ByteArrayUtil.putLong(new Date().getTime(), data, offset);
		offset += 8;
		if(type < 0 || version < 0){
			return null;
		}
        //2
		ByteArrayUtil.putShort(type, data, offset);
		offset += 2;
		//2
		ByteArrayUtil.putShort(version, data, offset);
		//1
		offset += 2;
		byte sendBy = 0;
		byte[] byteArr = new byte[1];
		byteArr[0] = sendBy;
		ByteArrayUtil.put(byteArr, data, offset);
		//4
		offset += byteArr.length;
		ByteArrayUtil.putInt(0, data, offset);

		return data;
	}

	public static String md5Encode(String s) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(s.getBytes());
			return toHexString(md.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return s;
		}
	}
	
	private static byte[] composeData(String passport, String text,String desc,short type,short version,String appid,String itemid) {

		int length = magic.length;
		byte[] passportData;
		if (passport == null)
			return null;

		try {
			passportData = passport.getBytes("GBK");
		} catch (UnsupportedEncodingException e) {
			passportData = passport.getBytes();
		}
		length += 4;   // --->FEED
		length += passportData.length;

		String md5 = md5Encode(passport);
		byte[] md5Data;
		try {
			md5Data = md5.getBytes("GBK");
		} catch (UnsupportedEncodingException e) {
			md5Data = md5.getBytes();
		}
		length += 4;
		length += md5Data.length;
        //Handle Text
		byte[] tData;
		if (text == null)
			return null;
		if (log.isDebugEnabled()) {
			log.debug("run() - send text  : |" + text);
		}

		try {
			tData = text.getBytes("GBK");
		} catch (UnsupportedEncodingException e) {
			tData = text.getBytes();
		}
		length += 4;
		length += tData.length;
        //Handle Description
		byte[] dData = null;
		length += 4;
		if (desc != null) {
			try {
				dData = desc.getBytes("GBK");
			} catch (UnsupportedEncodingException e) {
				dData = desc.getBytes();
			}
			length += dData.length;
		}
		byte[] aData = null;
		length += 4;
		
		if (appid != null) {
			try {
				if (log.isDebugEnabled()) {
					log.debug("**appid : "+appid.toString());
				}
				aData = appid.getBytes("GBK");
			} catch (UnsupportedEncodingException e) {
				aData = appid.getBytes();
			}
			length += aData.length;
		}
		
		byte[] iData = null;
		length += 4;
		
		if (itemid != null) {
			try {
				if (log.isDebugEnabled()) {
					log.info("**itemid : "+itemid.toString());
				}
				iData = itemid.getBytes("GBK");
			} catch (UnsupportedEncodingException e) {
				iData = itemid.getBytes();
			}
			length += iData.length;
		}
		length += 8 + 2 + 2 + 1 + 4; // 8 --time |2 -- type | 2 -- version |1 -- sendby |4 -- CRC

		byte[] data = new byte[length + 4];
		int offset = 0;
		ByteArrayUtil.putInt(length, data, offset);
		offset += 4;
		ByteArrayUtil.put(magic, data, offset);
		offset += magic.length;
		ByteArrayUtil.putInt(passportData.length, data, offset);
		offset += 4;
		ByteArrayUtil.put(passportData, data, offset);
		offset += passportData.length;
		ByteArrayUtil.putInt(md5Data.length, data, offset);
		offset += 4;
		ByteArrayUtil.put(md5Data, data, offset);
		offset += md5Data.length;
		ByteArrayUtil.putInt(tData.length, data, offset);
		offset += 4;
		ByteArrayUtil.put(tData, data, offset);
		offset += tData.length;
		//description ����Ϊ��
		if (dData == null) {
			ByteArrayUtil.putInt(0, data, offset);
			offset += 4;
		} else {
			ByteArrayUtil.putInt(dData.length, data, offset);
			offset += 4;
			ByteArrayUtil.put(dData, data, offset);
			offset += dData.length;
		}
		
        //8
		ByteArrayUtil.putLong(new Date().getTime(), data, offset);
		offset += 8;
		if(type < 0 || version < 0){
			return null;
		}
        //2
		ByteArrayUtil.putShort(type, data, offset);
		offset += 2;
		//appid����Ϊ��
		if (aData == null) {
			ByteArrayUtil.putInt(0, data, offset);
			offset += 4;
		} else {
			if (log.isDebugEnabled()) {
				log.debug("**aData : "+aData.length);
			}
			ByteArrayUtil.putInt(aData.length, data, offset);
			offset += 4;
			ByteArrayUtil.put(aData, data, offset);
			offset += aData.length;
		}
		
		//itemid����Ϊ��
		if (iData == null) {
			ByteArrayUtil.putInt(0, data, offset);
			offset += 4;
		} else {
			ByteArrayUtil.putInt(iData.length, data, offset);
			offset += 4;
			ByteArrayUtil.put(iData, data, offset);
			offset += iData.length;
		}
		
		//2
		ByteArrayUtil.putShort(version, data, offset);
		//1
		offset += 2;
		byte sendBy = 0;
		byte[] byteArr = new byte[1];
		byteArr[0] = sendBy;
		ByteArrayUtil.put(byteArr, data, offset);
		//4
		offset += byteArr.length;
		ByteArrayUtil.putInt(0, data, offset);
		if (log.isDebugEnabled()) {
			log.debug("ping content: "+data.toString());
		}
		
		return data;
	}

	

	public static String toHexString(byte[] b) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < b.length; ++i) {
			buf.append(Integer.toHexString((b[i] >> 4) & 0x0f));
			buf.append(Integer.toHexString(b[i] & 0x0f));
		}
		return buf.toString();
	}
	
	/**
	 * 提供一个在线设置server ip的接口
	 * 
	 * @param ip
	 */
	public void setServerAddress(String ip) {
		if (ip == null)
			return;
		try {
			serverAddress = InetAddress.getByName(ip);
		} catch (UnknownHostException e) {
			log.error("#### ping client setServerAddress UnknownHostException, ip="
							+ ip);
		}
	}

	/**
	 * 提供一个在线设置server port的接口
	 * 
	 * @param remotePort
	 */
	public static void setUdpPort(int remotePort) {
		serverPort = remotePort;
	}
	

	
	public static void main(String[] args){
		//UUID uuid = UUID.randomUUID();
		String passport="haoxw@sohu.com";
		String text="";
		/**
		* 金币商城（39）
		{
		  type : 1 ,       // 1,抽奖； 2，兑换; 3,新商品上架
		  num : 3 ,        //几个金币（1、3时有该项）
		  name:'',         //商品名称
		  link:'',         //商品展示页
		  pic: ''          //图片地址
		}
		 */
		
		JSONObject  json =  new JSONObject();
		json.put("type", 39);
		json.put("num", 100);
		json.put("name", "郝学武测试商品1");
		json.put("link", "http://1822.img.pp.sohu.com.cn/images/2011/10/31/16/14/u212763979_13416ef3b2dg215.jpg");
		short type=39;
		short version = 1;
		PingUdpClient.sendMessage(passport, text, json.toString(), type, version);

	}
}
