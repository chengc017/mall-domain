/**
 * 
 */
package com.sohu.sur.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author richardzhao
 *
 */
public class DateUtils {
	
	public static final String DEFAULT_DATE_FORMAT 	= "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT 			= "yyyy-MM-dd";
    public static final String DATEBILL_FORMAT 		= "yy-MM-dd";
    public static final String TIME_FORMAT 			= "yyyy年MM月dd日HH时mm分ss秒";
    public static final String DAY_FORMAT 			= "yyyy年MM月dd日";
    public static final String SECOND_FORMAT 		= "HH时mm分ss秒";
    public static final String YEAR_DATE_FORMAT 	= "yyyy";
    public static final String MONTH_DATE_FORMAT	= "yyyy-MM";
    public static final String HOUR_MINUTE_SECOND   = "HH:mm:ss";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			 System.out.print(getDateString(new Date(), 12));
		} catch (Exception e) {
			// TODO: handle exception
		}
       
	}
	
	
	  //获得字符串的日期   day:0-6 SUN-MON-TUES-SATER
    @SuppressWarnings("deprecation")
	public  static Date   getDateByString(String date,String format)throws Exception{ 
      return   new   SimpleDateFormat(format).parse(date);   
    }  
	
	  //获得上周日的日期   day:0-6 SUN-MON-TUES-SATER
    @SuppressWarnings("deprecation")
	public  static Date   getDateString(Date   date,int day)throws Exception{ 
      Calendar   c   =   Calendar.getInstance();   
      c.setTime(date); 
      c.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY); 
      c.set(Calendar.DAY_OF_MONTH,c.get(Calendar.DAY_OF_MONTH)-(7-day));//让日期加1  
      return   new   SimpleDateFormat("yyyy-MM-dd").parse(c.getTime().toLocaleString().toString());   
    }  
    
    //获得上周日的日期   day:0-6 SUN-MON-TUES-SATER
    @SuppressWarnings("deprecation")
	public  static String   getDate(Date   date,int day)throws Exception{ 
      Calendar   c   =   Calendar.getInstance();   
      c.setTime(date);   
      c.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY); 
      c.set(Calendar.DAY_OF_MONTH,c.get(Calendar.DAY_OF_MONTH)-(7-day));//让日期加1  
      return   new   SimpleDateFormat("yyyy-MM-dd").format(c.getTime());   
    }  
    
    //获得本周日的日期   day:0-6 SUN-MON-TUES-SATER
    @SuppressWarnings("deprecation")
	public  static String   getWeek(Date   date)throws Exception{ 
      Calendar   c   =   Calendar.getInstance();   
      c.setTime(date);   
      c.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY); 
      if(date.getDay()==0){
    	  //c.set(Calendar.DAY_OF_MONTH,c.get(Calendar.DAY_OF_MONTH)-7);//让日期加1 
      }else{
    	  c.set(Calendar.DAY_OF_MONTH,c.get(Calendar.DAY_OF_MONTH)+7);//让日期加1
      }
       
      return   new   SimpleDateFormat("yyyy-MM-dd").format(c.getTime());   
    }  
    
    //获得本周日的日期   day:0-6 SUN-MON-TUES-SATER
    @SuppressWarnings("deprecation")
	public  static String   getWeekDate(Date   date)throws Exception{ 
      Calendar   c   =   Calendar.getInstance();   
      c.setTime(date);   
      c.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY); 
      if(date.getDay()==0){
    	  //c.set(Calendar.DAY_OF_MONTH,c.get(Calendar.DAY_OF_MONTH)-7);//让日期加1 
      }else{
    	  c.set(Calendar.DAY_OF_MONTH,c.get(Calendar.DAY_OF_MONTH)+7);//让日期加1
      }
       
      return   new   SimpleDateFormat("yyyyMMdd").format(c.getTime());   
    }  
    
//  获得上周日的日期   day:0-6 SUN-MON-TUES-SATER
    @SuppressWarnings("deprecation")
	public  static String   getDate(Date   date,int day,String format)throws Exception{ 
      Calendar   c   =   Calendar.getInstance();   
      c.setTime(date);   
      c.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY); 
      c.set(Calendar.DAY_OF_MONTH,c.get(Calendar.DAY_OF_MONTH)-(7-day));//让日期加1  
      return   new   SimpleDateFormat(format).format(c.getTime());   
    } 
    
    //获取昨天的日期，格式自己定义
    public static String getYesterDate(Date date, String format){//"yyyy-MM-dd"
    	
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.add(Calendar.DATE,-1);   	
		
         return getStringDateAsFormat(calendar.getTime(), format);
    }
    
    /**
     * 得到currentDate之后的一天的日期
     * 
     * @param currentDate
     * @return
     */
    public static String getAfterDate(Date currentDate, String format)
	{		
		Calendar date = Calendar.getInstance();
		date.setTime(currentDate);		
		date.add(Calendar.DATE,1);
		String afterDate = getStringDateAsFormat(date.getTime(), format);
		return afterDate;
	}    
    
    //获取昨天的日期，格式自己定义
    public static String getTodayDate(Date date,String format){//"yyyy-MM-dd"
    	 SimpleDateFormat df=new SimpleDateFormat(format);   
         return df.format(new Date(date.getTime()));
    }
    
    //获得上周日的日期   day:0-6 SUN-MON-TUES-SATER
    @SuppressWarnings("deprecation")
	public  static Date   getDateFormat(Date   date,String  format)throws Exception{ 
      return   new   SimpleDateFormat(format).parse(date.toLocaleString().toString());   
    } 
    
    public static int getWeekNo(){
    	Calendar   cal   =   Calendar.getInstance();   
  	  //System.out.println(cal.get(Calendar.WEEK_OF_MONTH));   
  	  //System.out.println(cal.get(Calendar.WEEK_OF_YEAR));
    	return cal.get(Calendar.WEEK_OF_YEAR);
    }
    
    public static int getMonthNo(){
    	Calendar   cal   =   Calendar.getInstance();   
  	  //System.out.println(cal.get(Calendar.WEEK_OF_MONTH));   
  	  //System.out.println(cal.get(Calendar.WEEK_OF_YEAR));
    	return cal.get(Calendar.MONTH);
    }
    
    public static int getYearNo(){
    	Calendar   cal   =   Calendar.getInstance();   
  	  //System.out.println(cal.get(Calendar.WEEK_OF_MONTH));   
  	  //System.out.println(cal.get(Calendar.WEEK_OF_YEAR));
    	return cal.get(Calendar.YEAR);
    }
    /**
     * 获取上周日时间
     */
    public static String getLastWeek(){
    	Calendar c = Calendar.getInstance();   
	    c.setTime(new Date()); 
	    c.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY); 
	    c.set(Calendar.DAY_OF_MONTH,c.get(Calendar.DAY_OF_MONTH));
	    SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
	    return s.format(c.getTime());
    }
    /**
     * 按照自定义的格式得到日期字符串
     *
     * @param date
     *            日期转换时间
     * @param format
     *            日期转换格式
     * @return 格式化后的日期
     */
    public static String getStringDateAsFormat(Date date, String format) {

        if (date == null) {
            date = new Date();
        }
        return new SimpleDateFormat(format).format(date);
    }

    public static long interval(Date start, Date end)
    {    	
    	return end.getTime() - start.getTime();
    }
    
    /**
     * 返回两个日前之间的间隔天数
     * 
     * @param startDate			开始日期
     * @param endDate			结束日期
     * @return					间隔天数
     */
    public static long getDaysBetween(Date startDate, Date endDate) {  
        Calendar fromCalendar = Calendar.getInstance();  
        fromCalendar.setTime(startDate);  
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);  
        fromCalendar.set(Calendar.MINUTE, 0);  
        fromCalendar.set(Calendar.SECOND, 0);  
        fromCalendar.set(Calendar.MILLISECOND, 0);  
  
        Calendar toCalendar = Calendar.getInstance();  
        toCalendar.setTime(endDate);  
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);  
        toCalendar.set(Calendar.MINUTE, 0);  
        toCalendar.set(Calendar.SECOND, 0);  
        toCalendar.set(Calendar.MILLISECOND, 0);  
  
        return (toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24);  
    }  
    /**
     * 格式化商品的结束时间，23：59：59
     * @return
     */
    public static Date formatEndTime(Date date) {
    	if (date == null)
    		return date;
    	try {
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	String dateStr = sdf.format(date);
	    	sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			return date;
		}
    }
}
