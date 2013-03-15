package com.sohu.sur.util.gift;

/**
 * Created by IntelliJ IDEA.
 * User: gangma
 * Date: 2011-3-31
 * Time: 16:39:17
 * To change this template use File | Settings | File Templates.
 */
public class MedalsUtil {


    /**
     * 处理页面格式
     *
     * @param medals
     * @return
     */
    public static String encodeMedals(String medals) {
        if ((medals != null) && (!medals.trim().equalsIgnoreCase(""))) {

            String newMedal = "";

            String[] medalAarry = medals.split(" ");
            int j = 0;
            for (int i = 0; i < medalAarry.length; i++) {
                String s = medalAarry[i];
                if (!s.trim().equalsIgnoreCase("")) {

                    if (j != 0) {
                        newMedal = newMedal + "," + s;
                    } else {
                        newMedal = newMedal + s;
                        j = 1;
                    }

                }
            }
            return newMedal;
        }

        return medals;
    }

    /**
     * 转换成页面格式
     *
     * @param medals
     * @return
     */
    public static String decodeMedals(String medals) {
        if ((medals != null) && (!medals.trim().equalsIgnoreCase(""))) {

            medals = medals.trim().replaceAll(",", " ");

        }

        return medals;
    }


    public static void main(String[] strings) {

        System.out.println("decode= " + decodeMedals("123,153,42345,sdf "));

        System.out.println("encode=" + encodeMedals("     123    153    42345    sdf    "));
    }


}
