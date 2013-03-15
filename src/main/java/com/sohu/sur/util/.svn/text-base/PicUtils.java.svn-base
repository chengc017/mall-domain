package com.sohu.sur.util;

public class PicUtils {

    /** 获取文件的扩展名字 **/
    public static String getFileExt(String fileName) {
        if (fileName == null) return "";

        int i = fileName.lastIndexOf(".");

        if (i < 0) return "";

        return fileName.substring(i + 1);
    }

    /***
     * 获得图片在图片库的存放路径 服务器地址为：http://m[1-4].biz.itc.cn/pic/women/
     * 
     * @param id
     * @return
     */
    public static String getPicServerPathS(Long id) {
        if (id != null) {
            Long remain = id % 4 + 1;
            return "http://m" + remain + ".biz.itc.cn/suc";
        } else {
            return "http://m1.biz.itc.cn/suc";
        }

    }
}
