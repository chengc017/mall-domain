package com.sohu.sur.util.gift;

import java.net.*;
import javax.net.SocketFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * 下发短信
 * 
 * @author Administrator
 * 
 */
public class SendMessage {

    private static Logger logger = LoggerFactory.getLogger(SendMessage.class);

    public static final int FIELD_LENGTH_2 = 2;

    public static final int FIELD_LENGTH_4 = 4;

    public static final int FIELD_LENGTH_6 = 6;

    public static final int FIELD_LENGTH_11 = 11;

    public static final int FIELD_LENGTH_20 = 20;

    public static final int FIELD_LENGTH_21 = 21;

    public static final int FIELD_LENGTH_320 = 320;

    public static final int MIDDLE_COMMAND = 10;

    public static String networkaddress_cache_ttl = "1000";

    public static final String ip = "middle.sms.sohu.com.cn";

    public static final int port = 10000;

    private static final int socket_timeout = 2000;

    private static final StringBuilder strBuilder;

    private static final String LANG_ISO = "ISO-8859-1";

    static {
        java.security.Security.setProperty("networkaddress.cache.ttl", networkaddress_cache_ttl);
        strBuilder = new StringBuilder();
        for (int i = 0; i < FIELD_LENGTH_320; i++) {
            strBuilder.append(" ");
        }

    }

    public static void main(String[] args) {

        //	System.out.println(args[1] + " " + args[2]+ " " + args[3]+ " " +args[4]);
        String msg = "  test  八九";
        String msgiso = "";
        try {
            msgiso = new String(msg.getBytes("gbk"), "ISO-8859-1");
        } catch (Exception e) {}
        int i = sendMsg("18600407993", "106575000125", "13910923573", 1, msgiso, "", "", "100000", 15, 0, -1, "", "");
        System.out.println("return ret = " + i);
    }

    //comefrom=appid, firstmomt=0,srcnumber=10666666XXX, chargenumber,destnumber=手机号 columnid=1
    //msg=短消息内容 binmsg=wappush内容, msgfmt 短信为15,push为4
    public static int sendMsg(String chargenumber, String srcnumber, String destnumber, int columnid, String msg,
            String binmsg, String linkid, String comefrom, int msgfmt, int firstMoMt, int gwid, String appid, String key) {

        byte[] bmsg = null;
        try {
            bmsg = msg.getBytes(LANG_ISO);
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
        }
        int packLen = 546 + bmsg.length;
        int msgtype = 4;
        int givefee = 0;
        int chargewho = 3;
        int chargenumbertype = 0;
        int destnumbercount = 1;
        int Priority = 0;
        int iRet = -1;
        Socket socket = null;
        BufferedWriter wr = null;
        BufferedReader rd = null;
        StrMD5 strmd5 = new StrMD5();
        String md5 = strmd5.getMD5Str(appid + columnid + chargenumber + srcnumber + destnumber + msg + comefrom + key,
                LANG_ISO);

        String pack = MIDDLE_COMMAND + appid + packLen + getSpaceString("" + packLen, FIELD_LENGTH_4);
        pack += gwid + getSpaceString("" + gwid, FIELD_LENGTH_4) + columnid
                + getSpaceString("" + columnid, FIELD_LENGTH_4);
        pack += msgtype + getSpaceString("" + msgtype, FIELD_LENGTH_4) + givefee
                + getSpaceString("" + givefee, FIELD_LENGTH_4);
        pack += firstMoMt + getSpaceString(firstMoMt, FIELD_LENGTH_4) + chargewho
                + getSpaceString(chargewho, FIELD_LENGTH_4);
        pack += chargenumber + getSpaceString(chargenumber, FIELD_LENGTH_21) + chargenumbertype
                + getSpaceString("" + chargenumbertype, FIELD_LENGTH_4);
        pack += srcnumber + getSpaceString(srcnumber, FIELD_LENGTH_21) + destnumbercount
                + getSpaceString(destnumbercount, FIELD_LENGTH_4);
        pack += destnumber + getSpaceString(destnumber, FIELD_LENGTH_21) + chargenumbertype
                + getSpaceString(chargenumbertype, FIELD_LENGTH_4);
        pack += getSpaceString("", FIELD_LENGTH_20) + getSpaceString("", FIELD_LENGTH_20);
        pack += msgfmt + getSpaceString(msgfmt, FIELD_LENGTH_4) + bmsg.length
                + getSpaceString(bmsg.length, FIELD_LENGTH_4);
        pack += msg + binmsg + getSpaceString(binmsg, FIELD_LENGTH_320);
        pack += linkid + getSpaceString(linkid, FIELD_LENGTH_20) + comefrom + getSpaceString(comefrom, FIELD_LENGTH_11);
        pack += Priority + getSpaceString(Priority, FIELD_LENGTH_4) + md5;
        try {
            socket = SocketFactory.getDefault().createSocket(ip, port);
            socket.setSoTimeout(socket_timeout);
            wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), LANG_ISO));
            rd = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            wr.write(pack);
            wr.flush();
            iRet = Integer.parseInt(rd.readLine().trim());
            wr.close();
            rd.close();
            logger.info("send sms result:" + iRet);
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (Exception e) {
                logger.info(e.getMessage(), e);
            }
        }

        String str = "chargenumber=" + chargenumber + " srcnumber=" + srcnumber + " destnumber=" + destnumber
                + " columnid=" + columnid + "msg=" + msg + " binmsg=" + binmsg + " linkid=" + linkid + " comefrom="
                + comefrom + " msgfmt=" + msgfmt + " gwid=" + gwid + " ret=" + iRet;
        System.out.println(str);
        logger.info(str);
        ///log.writeLog(str);
        return iRet;
    }

    private static String getSpaceString(String content, int length) {
        if (length <= content.length()) {
            return "";
        }

        return strBuilder.substring(0, length - content.length());
    }

    private static String getSpaceString(int i, int length) {
        return getSpaceString("" + i, length);
    }

}
