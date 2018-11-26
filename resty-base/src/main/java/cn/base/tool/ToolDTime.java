package cn.base.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import cn.dreampie.log.Logger;


/**
 * 时间工具类
 * @author haoyin
 *
 */
public class ToolDTime{
	private final static Logger logger = Logger.getLogger(ToolDTime.class);
	
    /** 
     * 缺省的日期显示格式： yyyy-MM-dd 
     */  
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";  
    /** 
     * 缺省的日期显示格式： yyyyMMdd 
     */  
    public static final String DATE_FORMAT_NYR = "yyyyMMdd";  
	
    /** 
     * 缺省的日期时间显示格式：yyyy-MM-dd HH:mm:ss 
     */  
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";  
    
    /** 
     * 缺省的日期显示格式： yyyyMMddHHmmss 
     */  
    public static final String DATE_FORMAT_KEY = "yyyyMMddHHmmss"; 
    
    /** 
     * 日期显示格式： HH:mm:ss 
     */  
    public static final String HOUR_MIN_SEC = "HH:mm:ss"; 
    
    /** 
     * 日期显示格式： yyyyMMddHHmmss 
     */  
    public static final String WX_TIME = "yyyyMMddHHmmss"; 
    
    /**
     * 日期显示格式： yyMMddHHmmss 
     */
    public static final String DATE_SY_FORMAT_KEY = "yyMMddHHmmss"; 
    
    /**
     * 日期显示格式： yyMMddHHmm
     */
    public static final String DATE_SYS_FORMAT_KEY = "yyMMddHHmm"; 
    
    
    public static final long ONE_SEC_MILLONS = 1000L * 60L;
    public static final long ONE_HOUR_MILLONS = ONE_SEC_MILLONS * 60L;
    public static final long ONE_DAY_MILLONS = ONE_HOUR_MILLONS * 24L;
    
	private ToolDTime() {
		// 工具类，不需要实例化
	}
	
	/**
	 * 统一为中国时区时间
	 * @return
	 */
	public static long getNowL(){
		return Calendar.getInstance(Locale.CHINA).getTimeInMillis();
	}
	
	/**
	 * 
	 * @Title: getLongByHMS 
	 * @Description: 根据是时分秒获取当天这个时间的long 
	 * @param hour
	 * @param min
	 * @param sec
	 * @return
	 * @return: long
	 */
	public static long getLongByHMS(int hour,int minute,int sec){
		 Calendar calendar = Calendar.getInstance();
		 calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), hour, minute, sec);
		 return calendar.getTimeInMillis();
	}
	
	public static long getLongByHMS(int year,int month,int day,int hour,int minute,int sec){
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day, hour, minute, sec);
		return calendar.getTimeInMillis();
	}
	
	/**
	 * 获得当前日期的月份
	 * @return
	 */
	public static int getMonth(){
		Calendar calendar=Calendar.getInstance();
		//获得当前时间的月份，月份从0开始所以结果要加1
		int month=calendar.get(Calendar.MONTH)+1;
		return month;
	}
	public static int getMonth(long time){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(new Date(time));
		//获得当前时间的月份，月份从0开始所以结果要加1
		int month=calendar.get(Calendar.MONTH)+1;
		return month;
	}
	
	public static int getMonth_(long time){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(new Date(time));
		//获得当前时间的月份，月份从0开始所以结果要加1
		int month=calendar.get(Calendar.MONTH);
		return month;
	}
	/**
	 * 
	 * @return
	 */
	public static int getYear(){
		Calendar calendar=Calendar.getInstance();
		int month=calendar.get(Calendar.YEAR);
		return month;
	}
	
	
	public static int getYear(long time){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(new Date(time));
		int month=calendar.get(Calendar.YEAR);
		return month;
	}
	
	public static int getDay(){
		Calendar calendar=Calendar.getInstance();
		int month=calendar.get(Calendar.DAY_OF_MONTH);
		return month;
	}
	public static int getDay(long time){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(new Date(time));
		int month=calendar.get(Calendar.DAY_OF_MONTH);
		return month;
	}
	
	/** 
     * 得到年月日
     * @return 当前日期及时间 
     */  
    public static String getNYR(long dateTime) {  
    	Date date = new Date(dateTime);
    	return getDateTime(date,DATE_FORMAT_NYR);  
    } 
    
    /** 
     * 得到年-月-日
     * @return 当前日期及时间 YYYY-MM-DD
     */  
    public static String getNYR_(long dateTime) {  
    	Date date = new Date(dateTime);
    	return getDateTime(date,DEFAULT_DATE_FORMAT);  
    } 
    
    public static long  DateS2Long(String ds,String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date  = null ;
	    try {
			date  = sdf.parse(ds);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	    return date  == null ?0L:date.getTime();
	}
	
	/** 
     * 得到系统当前日期及时间字符串，并用指定的方式格式化 
     * @param pattern 显示格式 
     * @return 当前日期及时间 
     */  
    public static String getDateStr(String pattern) {  
      Date datetime = Calendar.getInstance().getTime();  
      return getDateTime(datetime, pattern);  
    }  
    
    /**
     * 根据时间long  获取指定格式的时间字符串
     * @param time
     * @param pattern
     * @return
     */
    public static String getDateStr(long time,String pattern) {  
    	Date datetime = new Date(time);
    	return getDateTime(datetime, pattern);  
    }  
    
    /**
     * 根据时间long  获取默认格式的时间字符串  yyyy-MM-dd HH:mm:ss
     * @param time
     * @param pattern
     * @return
     */
    public static String getDateStr(long time) {  
    	Date datetime = new Date(time);
    	return getDateTime(datetime, DEFAULT_DATETIME_FORMAT);  
    }  
    
    /** 
     * 得到系统当前日期及时间字符串，采用默认格式化  yyyy-MM-dd HH:mm:ss
     * @param pattern 显示格式 
     * @return 当前日期及时间 
     */  
    public static String getNowS() {  
    	Date datetime = Calendar.getInstance().getTime();  
    	return getDateTime(datetime, DEFAULT_DATETIME_FORMAT);  
    }  
    
    /** 
     * 得到用指定方式格式化的日期 
     * @param date 需要进行格式化的日期 
     * @param pattern 显示格式 
     * @return 日期时间字符串 
     */  
    public static String getDateTime(Date date, String pattern) {  
      if (null == pattern || "".equals(pattern)) {  
        pattern = DEFAULT_DATETIME_FORMAT;  
      }  
      SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);  
      return dateFormat.format(date);  
    }  
    
    /**
     * 解析时间格式字符串为long类型
     * @param date 时间字符串
     * @param pattern 解析规则
     * @return 时间的long表达
     * @throws ParseException 格式错误异常
     */
    public static long parserDateStr(String date, String pattern) throws ParseException {
    	if (null == pattern || "".equals(pattern)) {  
            return 0;
        }
    	SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);  
    	return dateFormat.parse(date).getTime();
    }
    
    /**
     * 获取两个时间之间的天数
     * @param minDate
     * @param maxDate
     * @return
     */
    public  static List<String> getMonthBetween(long  minDate, long maxDate)  {
        List<String> result = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//格式化为年月

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(new Date(minDate));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), min.get(Calendar.DAY_OF_MONTH));

        max.setTime(new Date(maxDate));
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), max.get(Calendar.DAY_OF_MONTH));

        Calendar curr = min;
        while (curr.before(max)) {
         result.add(sdf.format(curr.getTime()));
         curr.add(Calendar.DAY_OF_MONTH, 1);
        }

        return result;
      }
	
    /**
     * 获取该时间的    yyyymmdd 类型的int  值
     * @param time
     * @return
     */
    public static  int getYYYYMMDD(long time){
    	if(time == 0L){
    		return 0;
    	}
    	StringBuilder retn = new StringBuilder();
    	int year = getYear(time);
    	retn.append(year);
    	int month = getMonth(time);
    	if(month<10){
    		retn.append(0);
    	}
    	retn.append(month);
    	int day = getDay(time);
    	if(day<10){
    		retn.append(0);
    	}
    	retn.append(day);
    	return ToolString.parseInt(retn.toString());
    }
    
    /**
     * 将yyyymmdd int 类型时间 转换成  0点的   long 类型
     * @param yyyymmdd
     * @return
     */
    public static Long formatYYYYMMDD(int yyyymmdd){
    	if(yyyymmdd <= 0){
    		return 0L;
    	}
    	int yyyy = yyyymmdd/10000;
    	int mm = (yyyymmdd-yyyy*10000)/100;
    	int dd  = yyyymmdd-yyyy*10000-mm*100;
    	
    	return getLongByHMS(yyyy, mm-1, dd, 0, 0, 0);
    }
    
    /**
     * 获取改天的  零点时间
     * @param time
     * @return
     */
    public static Long getZeroForNow(long time){
    	if(time == 0L){
    		return 0L;
    	}
    	int year = getYear(time);
    	int month = getMonth(time);
    	int day = getDay(time);
    	return getLongByHMS(year, month, day, 0, 0, 0);
    }
    
    /**
     * 获取改天的  零点时间
     * @param time
     * @return
     */
    public static Long getZeroForNow_(long time){
    	if(time == 0L){
    		return 0L;
    	}
    	int year = getYear(time);
    	int month = getMonth_(time);
    	int day = getDay(time);
    	return getLongByHMS(year, month, day, 00, 00, 00);
    }
    
    /**
     * 获取当前时间的0点时间long
     * @param time
     * @return
     */
    public static long getZeroFowNow2(){
    	long time = getNowL();
    	int year = getYear(time);
    	int month = getMonth_(time);
    	int day = getDay(time);
    	long t = getLongByHMS(year, month, day, 00, 00, 00) ;
    	String s = t + "";
    	String s1 = s.substring(0, 10)+"000";
    	return Long.parseLong(s1);
    }
    /**
     * 获取当前时间的24点时间long
     * @param time
     * @return
     */
    public static long getZeroFowNow3(){
    	long time = getNowL();
    	int year = getYear(time);
    	int month = getMonth_(time);
    	int day = getDay(time);
    	long t = getLongByHMS(year, month, day, 23, 59, 59) ;
    	String s = t + "";
    	String s1 = s.substring(0, 10)+"000";
    	return Long.parseLong(s1);
    }
    
    /**
     * 获取当前时间之前或者之后时间的0点或者24点时间
     * time 为负数：前多少天，为正数：后多少天
     * isZero true 零点时间（ 00:00:00） false 23时间（23:59:59）
     * @return
     */
    public static long getBeforOrAfterTimeByNow(int time,boolean isZero) {
    	Date date=new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,time);//把日期往前减少一天，若想把日期向后推一天则将负数改为正数
        date=calendar.getTime(); 
       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
       String dateString = formatter.format(date);
       if(isZero) {
    	   dateString = dateString+ " 00:00:00";
       }else {
    	   dateString = dateString+ " 23:59:59";
       }
       	// System.out.println("getTomorrowEnd："+dateString);
       	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date2 = null;
		try {
			date2 = (Date) sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long dateLong = Long.parseLong((date2.getTime()+"").substring(0, 10)) ;
        // System.out.println(dateLong);
    	return dateLong;
    }
    
    
	public static void main(String[] args) {
		
		getBeforOrAfterTimeByNow(-1,false);
	}
	
    
    
}
