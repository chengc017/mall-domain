package com.sohu.sur.util.gift;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: gangma
 * Date: 2011-4-1
 * Time: 16:54:49
 * 从json序列化用滴
 */
public class MedalsJsonParse {

    private volatile Map<String, Medal> medals;

    private volatile Map medalsdesc;

    private volatile List<Channel> channelList;

    private static MedalsJsonParse medalsJsonParse = new MedalsJsonParse();

    private MedalsJsonParse() {
        init();
    }

    public void reload() {
        init();
    }

    public Map<String, Medal> getMedals() {
        return medals;
    }


    /**
	 * @return the medalsdesc
	 */
	public Map getMedalsdesc() {
		return medalsdesc;
	}


	public List<Channel> getChannelList() {
        return channelList;
    }

    public static MedalsJsonParse instance() {
        return medalsJsonParse;
    }


    public void init() {

        Map<String, Medal> tempMedals = new HashMap();
        Map<String, String> tempMedalstr = new HashMap();
        
        List<Channel> tempChannelList = new ArrayList();

        String medalsJson = getMedalsJsonByHttp();

        medalsJson = medalsJson.replaceAll("0.7}", "\"0.7\"}");
        medalsJson = medalsJson.replaceAll("0.8}", "\"0.8\"}");
        medalsJson = medalsJson.replaceAll("0.85}", "\"0.85\"}");
        medalsJson = medalsJson.replaceAll("0.9}", "\"0.9\"}");
        medalsJson = medalsJson.replaceAll("0.95}", "\"0.95\"}");
        
        List<Map> listChannelMedals = JSON.parseArray(medalsJson, Map.class);

        for (int i = 0; i < listChannelMedals.size(); i++) {
            try {
                Map map = listChannelMedals.get(i);

                tempChannelList.add(parseChannel(map));
                
                
                String channelMedals = ((JSONArray) map.get("medals")).toString();
                List<Medal> medalList = JSON.parseArray(channelMedals, Medal.class);
                for (int j = 0; j < medalList.size(); j++) {
                    Medal medal = medalList.get(j);
                    tempMedals.put(medal.getCode(), medal);
                    tempMedalstr.put(medal.getCode(), medal.getName());

                    System.out.println("medal = " + medal);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.channelList = tempChannelList;        
        this.medals = tempMedals;
        this.medalsdesc = tempMedalstr;
    }

    private Channel parseChannel(Map map) {
        String name = (String) map.get("name");
        String id = (String) map.get("id");
        String description = (String) map.get("description");
        Channel channel = new Channel(id, name, description);
        return channel;
    }


    public String getMedalsJson() {
        return "[{\"name\":\"新闻\",\"id\":\"news\",\"description\":\"新闻频道\",\"medals\":[{\"name\":\"新闻钻石勋章\",\"description\":\"新闻钻石勋章\",\"code\":\"news_04\",\"productCode\":\"news\",\"productName\":\"新闻\",\"discount\":0.7},{\"name\":\"新闻金质勋章\",\"description\":\"新闻金质勋章\",\"code\":\"news_03\",\"productCode\":\"news\",\"productName\":\"新闻\",\"discount\":0.8},{\"name\":\"新闻银质勋章\",\"description\":\"新闻银质勋章\",\"code\":\"news_02\",\"productCode\":\"news\",\"productName\":\"新闻\",\"discount\":0.9},{\"name\":\"新闻铜质勋章\",\"description\":\"新闻铜质勋章\",\"code\":\"news_01\",\"productCode\":\"news\",\"productName\":\"新闻\",\"discount\":0.95}]},{\"name\":\"女人\",\"id\":\"women\",\"description\":\"女人频道\",\"medals\":[{\"name\":\"女人钻石勋章\",\"description\":\"女人钻石勋章\",\"code\":\"women_04\",\"productCode\":\"women\",\"productName\":\"女人\",\"discount\":0.7},{\"name\":\"女人金质勋章\",\"description\":\"女人金质勋章\",\"code\":\"women_03\",\"productCode\":\"women\",\"productName\":\"女人\",\"discount\":0.8},{\"name\":\"女人银质勋章\",\"description\":\"女人银质勋章\",\"code\":\"women_02\",\"productCode\":\"women\",\"productName\":\"女人\",\"discount\":0.9},{\"name\":\"女人铜质勋章\",\"description\":\"女人铜质勋章\",\"code\":\"women_01\",\"productCode\":\"women\",\"productName\":\"女人\",\"discount\":0.95}]},{\"name\":\"娱乐\",\"id\":\"yule\",\"description\":\"娱乐频道\",\"medals\":[{\"name\":\"娱乐钻石勋章\",\"description\":\"娱乐钻石勋章\",\"code\":\"yule_04\",\"productCode\":\"yule\",\"productName\":\"娱乐\",\"discount\":0.7},{\"name\":\"娱乐金质勋章\",\"description\":\"娱乐金质勋章\",\"code\":\"yule_03\",\"productCode\":\"yule\",\"productName\":\"娱乐\",\"discount\":0.8},{\"name\":\"娱乐银质勋章\",\"description\":\"娱乐银质勋章\",\"code\":\"yule_02\",\"productCode\":\"yule\",\"productName\":\"娱乐\",\"discount\":0.9},{\"name\":\"娱乐铜质勋章\",\"description\":\"娱乐铜质勋章\",\"code\":\"yule_01\",\"productCode\":\"yule\",\"productName\":\"娱乐\",\"discount\":0.95}]},{\"name\":\"IT\",\"id\":\"it\",\"description\":\"IT频道\",\"medals\":[{\"name\":\"IT钻石勋章\",\"description\":\"IT钻石勋章\",\"code\":\"it_04\",\"productCode\":\"it\",\"productName\":\"IT\",\"discount\":0.7},{\"name\":\"IT金质勋章\",\"description\":\"IT金质勋章\",\"code\":\"it_03\",\"productCode\":\"it\",\"productName\":\"IT\",\"discount\":0.8},{\"name\":\"IT银质勋章\",\"description\":\"IT银质勋章\",\"code\":\"it_02\",\"productCode\":\"it\",\"productName\":\"IT\",\"discount\":0.9},{\"name\":\"IT铜质勋章\",\"description\":\"IT铜质勋章\",\"code\":\"it_01\",\"productCode\":\"it\",\"productName\":\"IT\",\"discount\":0.95}]},{\"name\":\"旅游\",\"id\":\"travel\",\"description\":\"旅游频道\",\"medals\":[{\"name\":\"旅游钻石勋章\",\"description\":\"旅游钻石勋章\",\"code\":\"travel_04\",\"productCode\":\"travel\",\"productName\":\"旅游\",\"discount\":0.7},{\"name\":\"旅游金质勋章\",\"description\":\"旅游金质勋章\",\"code\":\"travel_03\",\"productCode\":\"travel\",\"productName\":\"旅游\",\"discount\":0.8},{\"name\":\"旅游银质勋章\",\"description\":\"旅游银质勋章\",\"code\":\"travel_02\",\"productCode\":\"travel\",\"productName\":\"旅游\",\"discount\":0.9},{\"name\":\"旅游铜质勋章\",\"description\":\"旅游铜质勋章\",\"code\":\"travel_01\",\"productCode\":\"travel\",\"productName\":\"旅游\",\"discount\":0.95}]},{\"name\":\"视频\",\"id\":\"tv\",\"description\":\"视频频道\",\"medals\":[{\"name\":\"视频钻石勋章\",\"description\":\"视频钻石勋章\",\"code\":\"tv_04\",\"productCode\":\"tv\",\"productName\":\"视频\",\"discount\":0.7},{\"name\":\"视频金质勋章\",\"description\":\"视频金质勋章\",\"code\":\"tv_03\",\"productCode\":\"tv\",\"productName\":\"视频\",\"discount\":0.8},{\"name\":\"视频银质勋章\",\"description\":\"视频银质勋章\",\"code\":\"tv_02\",\"productCode\":\"tv\",\"productName\":\"视频\",\"discount\":0.9},{\"name\":\"视频铜质勋章\",\"description\":\"视频铜质勋章\",\"code\":\"tv_01\",\"productCode\":\"tv\",\"productName\":\"视频\",\"discount\":0.95}]},{\"name\":\"教育\",\"id\":\"learning\",\"description\":\"教育频道\",\"medals\":[{\"name\":\"教育钻石勋章\",\"description\":\"教育钻石勋章\",\"code\":\"learning_04\",\"productCode\":\"learning\",\"productName\":\"教育\",\"discount\":0.7},{\"name\":\"教育金质勋章\",\"description\":\"教育金质勋章\",\"code\":\"learning_03\",\"productCode\":\"learning\",\"productName\":\"教育\",\"discount\":0.8},{\"name\":\"教育银质勋章\",\"description\":\"教育银质勋章\",\"code\":\"learning_02\",\"productCode\":\"learning\",\"productName\":\"教育\",\"discount\":0.9},{\"name\":\"教育铜质勋章\",\"description\":\"教育铜质勋章\",\"code\":\"learning_01\",\"productCode\":\"learning\",\"productName\":\"教育\",\"discount\":0.95}]},{\"name\":\"博客\",\"id\":\"blog\",\"description\":\"博客频道\",\"medals\":[{\"name\":\"博客钻石勋章\",\"description\":\"博客钻石勋章\",\"code\":\"blog_04\",\"productCode\":\"blog\",\"productName\":\"博客\",\"discount\":0.7},{\"name\":\"博客金质勋章\",\"description\":\"博客金质勋章\",\"code\":\"blog_03\",\"productCode\":\"blog\",\"productName\":\"博客\",\"discount\":0.8},{\"name\":\"博客银质勋章\",\"description\":\"博客银质勋章\",\"code\":\"blog_02\",\"productCode\":\"blog\",\"productName\":\"博客\",\"discount\":0.9},{\"name\":\"博客铜质勋章\",\"description\":\"博客铜质勋章\",\"code\":\"blog_01\",\"productCode\":\"blog\",\"productName\":\"博客\",\"discount\":0.95}]},{\"name\":\"读书\",\"id\":\"book\",\"description\":\"读书频道\",\"medals\":[{\"name\":\"读书钻石勋章\",\"description\":\"读书钻石勋章\",\"code\":\"book_04\",\"productCode\":\"book\",\"productName\":\"读书\",\"discount\":0.7},{\"name\":\"读书金质勋章\",\"description\":\"读书金质勋章\",\"code\":\"book_03\",\"productCode\":\"book\",\"productName\":\"读书\",\"discount\":0.8},{\"name\":\"读书银质勋章\",\"description\":\"读书银质勋章\",\"code\":\"book_02\",\"productCode\":\"book\",\"productName\":\"读书\",\"discount\":0.9},{\"name\":\"读书铜质勋章\",\"description\":\"读书铜质勋章\",\"code\":\"book_01\",\"productCode\":\"book\",\"productName\":\"读书\",\"discount\":0.95}]},{\"name\":\"体育\",\"id\":\"sports\",\"description\":\"体育频道\",\"medals\":[{\"name\":\"体育钻石勋章\",\"description\":\"体育钻石勋章\",\"code\":\"sports_04\",\"productCode\":\"sports\",\"productName\":\"体育\",\"discount\":0.7},{\"name\":\"体育金质勋章\",\"description\":\"体育金质勋章\",\"code\":\"sports_03\",\"productCode\":\"sports\",\"productName\":\"体育\",\"discount\":0.8},{\"name\":\"体育银质勋章\",\"description\":\"体育银质勋章\",\"code\":\"sports_02\",\"productCode\":\"sports\",\"productName\":\"体育\",\"discount\":0.9},{\"name\":\"体育铜质勋章\",\"description\":\"体育铜质勋章\",\"code\":\"sports_01\",\"productCode\":\"sports\",\"productName\":\"体育\",\"discount\":0.95}]}]";
    }

    public String getMedalsJsonByHttp() {
        String url = "http://api.ums.sohu.com/bonus/channels/";    //Todo 换成配置文件 

        HttpClient httpClient = new HttpClient();
//        httpClient.getParams().setParameter(
//              HttpMethodParams.HTTP_CONTENT_CHARSET, "GBK");

        GetMethod getMethod = new GetMethod(url);
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler());

        try {
            //执行getMethod
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: "
                        + getMethod.getStatusLine());
            } else {
                //读取内容
                byte[] responseBody = getMethod.getResponseBody();
                //处理内容
                String json = new String(responseBody, "utf8");
                System.out.println(json);

                return json;

            }

        } catch (HttpException e) {

            System.out.println("Please check your provided http address!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //释放连接
            getMethod.releaseConnection();
        }

        return this.getMedalsJson();

    }

    public static void main(String[] arg) {

        MedalsJsonParse.instance().getMedals();
    }


    public static class Channel {
        String description;
        String name;
        String id;

        public Channel() {

        }

        public Channel(String id, String name, String description) {
            this.description = description;
            this.id = id;
            this.name = name;

        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }


    public static class Medal {
        String name;
        String code;
        String productCode;
        String description;
        String productName;
        float discount;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Medal medal = (Medal) o;

            if (code != null ? !code.equals(medal.code) : medal.code != null) return false;
            if (description != null ? !description.equals(medal.description) : medal.description != null) return false;
            //if (discount != null ? !discount.equals(medal.discount) : medal.discount != null) return false;
            if (name != null ? !name.equals(medal.name) : medal.name != null) return false;
            if (productCode != null ? !productCode.equals(medal.productCode) : medal.productCode != null) return false;
            if (productName != null ? !productName.equals(medal.productName) : medal.productName != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (code != null ? code.hashCode() : 0);
            result = 31 * result + (productCode != null ? productCode.hashCode() : 0);
            result = 31 * result + (description != null ? description.hashCode() : 0);
            result = 31 * result + (productName != null ? productName.hashCode() : 0);
            //result = 31 * result + (discount != null ? discount.hashCode() : 0);
            return result;
        }

        public String getDescription() {

            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public float getDiscount() {
            return discount;
        }

        public void setDiscount(float discount) {
        	this.discount = discount;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getProductCode() {
            return productCode;
        }

        public void setProductCode(String productCode) {
            this.productCode = productCode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append("Medal");
            sb.append("{name='").append(name).append('\'');
            sb.append(", code='").append(code).append('\'');
            sb.append(", productCode='").append(productCode).append('\'');
            sb.append(", description='").append(description).append('\'');
            sb.append(", productName='").append(productName).append('\'');
            sb.append(", discount='").append(discount).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }


}
