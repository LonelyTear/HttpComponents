package kingtool;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
 
/**
 * 
 * 
 * @author King
 * @date 2015-06-08
 */
public class DateTool {
	public static final SimpleDateFormat milliSecondFmt1 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	public static final SimpleDateFormat milliSecondFmt2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
	public static final SimpleDateFormat FMT_yMd1 = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat FMT_yMdHms1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat FMT_yMd2 = new SimpleDateFormat("yyyy/MM/dd");
	public static final SimpleDateFormat FMT_yMdHms2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	public static final SimpleDateFormat FMT_yMd3 = new SimpleDateFormat("yyyyMMdd");
	public static final SimpleDateFormat FMT_yMdHms3 = new SimpleDateFormat("yyyyMMddHHmmss");
	public static final SimpleDateFormat FMT_yMdHm1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static final SimpleDateFormat FMT_yMdHm2 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	public static final SimpleDateFormat FMT_yMdHm3 = new SimpleDateFormat("yyyyMMddHHmm");
	public static final SimpleDateFormat FMT_yM = new SimpleDateFormat("yyyy/MM");
	public static final SimpleDateFormat FMT_y = new SimpleDateFormat("yyyy");
	public static final  SimpleDateFormat FMT_yMd4 = new SimpleDateFormat("yyyy/M/dd");
	public static final  SimpleDateFormat FMT_yMd5 = new SimpleDateFormat("yyyy/M/d");
	public static final SimpleDateFormat[] FMTS = 	new SimpleDateFormat[]{FMT_yMd1,FMT_yMdHms1,FMT_yMd2,FMT_yMdHms2,FMT_yMd3,FMT_yMdHms3,FMT_yMdHm1,FMT_yMdHm2,FMT_yMdHm3,FMT_yM,FMT_y,FMT_yMd4,FMT_yMd5};
	
	
	
	
	
	/**
	 * 把String转换成Date</br> 
	 * 1.先用指定的specifiedFmt去转换
	 * 2.指定的specifiedFmt转换失败,则用默认的时间格式化样式数组去转换
	 * 
	 * @param sourceStr 将要被转换成Date类型的String
	 * @param specifiedFmt 指定格式化样式的String
	 * @author King
	 * @return Date
	 */
	public static final Date getDate(String sourceStr, String specifiedFmt) {
		Date ret = null;
		boolean hasException = true;
		if (specifiedFmt != null && !"".equals(specifiedFmt)) {
			try {
				SimpleDateFormat sdf1 = new SimpleDateFormat(specifiedFmt);
				ret = sdf1.parse(sourceStr);
			} catch (ParseException e) {
				hasException = true;
			}
		}
		if (!hasException)//没有异常表示正常
			return ret;
		ret = getDate(sourceStr);//有异常就偿试着用默认的格式化
		return ret;
	}
	 
	/**
	 * 把String转换成Date</br>
	 *  用默认的时间格式化样式数组去转换
	 * @param sourceStr 将要被转换成Date类型的String
	 * @author King
	 * return Date
	 */
	public static final Date getDate(String sourceStr) {
		 sourceStr = sourceStr.replace("-", "/");
		 Date d = null;
		 boolean hasException = true;
		 for(SimpleDateFormat sdf :FMTS){
			 if( sdf.toPattern().length() != sourceStr.length())
				 continue;
			 if(hasException){//如果有异常,那么继续格式化转换日期
				 try {
					 d = sdf.parse(sourceStr);
					 break;
				 } catch (ParseException e) {
					 hasException = true;
				 }
			 }else{//如果没异常了,那么马上退出循环
				 break;
			 }
		 }
       return d;
    }
	
    /**
     * 以指定样式格式化字符串成日期
     * @param date 将要被转换成String类型的Date
     * @param specifiedFmt 指定格式化样式的String
     * @author King
     * @return String
     */
    public static final String getDate(Date date , String specifiedFmt) {
        SimpleDateFormat formatter = new SimpleDateFormat(specifiedFmt);
        String ret = formatter.format(date);
        return ret;
    }
    
    /**
     * 获得指定日期上个月第一天的日期</br>
     * @param date Date日期类型
     * @author King
     * return String
     */
    public static final String getLastMonthFirstDay(Date date){
	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	   String str = "";
	   Calendar calendar = Calendar.getInstance();
	   calendar.setTime(date);//设置时间
	   calendar.add(Calendar.MONTH, -1);//加/减一个月,正数往后推,负数往前移动 
	   calendar.set(Calendar.DATE, 1);//把日期设置为当月第一天
	   str = sdf.format(calendar.getTime());
	   return str;
    }

    /**
     * 获得指定日期上个月最后一天的日期</br>
     * @param date Date日期类型
     * @author King
     * return String
     */
    public static final Date getLastMonthLastDay(Date date){
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(date); 
		cal.add(Calendar.MONTH, -1);//加/减一个月,正数往后推,负数往前移动 
		cal.setTime(cal.getTime());
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);//取得该月份最后一天
		cal.set(Calendar.DAY_OF_MONTH, lastDay);//设置成该月份的最后一天
		return cal.getTime();
    }
	 
	 /**
     * 得到指定日期的月初日期,返回字符串</br>
     * add zhutf 2013-04-08
     */
    public static final String getFirstDay(String today){      
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       String str = "";
		try {
			Date date = sdf.parse(today);
			Calendar lastDate = Calendar.getInstance();    
			lastDate.setTime(date);
			lastDate.set(Calendar.DATE, 1);//设为当前月的1号    
		    str = sdf.format(lastDate.getTime());    
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return str;  
    }    
	    
    /**
     * 得到指定日期的月末日期,返回字符串</br>
     */
    public static final String getLastDay(String today){      
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       String str = "";
		try {
			Date date = sdf.parse(today);
			Calendar lastDate = Calendar.getInstance();    
			lastDate.setTime(date);
			lastDate.set(Calendar.DATE, 1);//设为当前月的1号    
			lastDate.add(Calendar.MONTH, 1);//加一个月，变为下月的1号    
			lastDate.add(Calendar.DATE, -1);//减去一天，变为当月最后一天    
		    str = sdf.format(lastDate.getTime());    
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return str;  
    }    

    /**
     * 在日期上增加相应年份
     * @param years  年份
     * @param years  要增加的年份数 (整数)
     * @time 2015/06/23
     * @return
     */
    public static Date addYear(Date date, int years) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, years);//把日期往后增加years年.正数往后推,负数往前移动 
        return cal.getTime();
    }
    
    /**
     * 在日期上增加相应月份
     * @param months  月份
     * @param months  要增加的月份数 (整数)
     * @time 2015/06/23
     * @return
     */
    public static Date addMonth(Date date, int months) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.MONTH, months);//把日期往后增加months月.正数往后推,负数往前移动 
    	return cal.getTime();
    }
    /**
     * 在日期上增加相应天数
     * @param date  日期
     * @param days  要增加的天数 (整数)
     * @time 2015/06/23
     * @return
     */
    public static Date addDay(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);//把日期往后增加days天.正数往后推,负数往前移动 
        return cal.getTime();
    }
    
    
	/**
		 
	  /**
     * 得到两个日期间的间隔天数 </br>
     * @param firstDay	String格式 "2015-06-08"
     * @param lastDay	String格式 "2015-06-07"
     * @author King
     */
	 public static final Long getDays(String firstDay, String lastDay) {
		 if (firstDay == null || "".equals(firstDay) ){
			 return 0L;
		 }
         if (lastDay == null || "".equals(firstDay) ){
        	return 0L;
         }
	    long days = 0;
		try {
			Date beginDate = FMT_yMd1.parse(firstDay);
			Date endDate = FMT_yMd1.parse(lastDay);
			days = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);    
		} catch (ParseException e) {
			e.printStackTrace();
		} 
	    return days;
	 }
	    
	 /**
     * 统计两个日期相差小时数 <br/>
     * 例:3600秒是一小时,那么如果相差3500秒,本方法返回0,如果相差3700秒,本方法返回1,即舍去余数
     * @param date1 第一个日期
     * @param date2 第二个日期
     * @return int
     * @author King
     */
	 public static int countHours(Date date1,Date date2) {
	    	long l = date2.getTime() - date1.getTime();
	        return (int) (l/1000) / 3600 ;
	    }  
	 
	 /**
     * 统计两个日期相差天数 <br/>
     * 例:相差3700秒,那么3700/3600 = 1 返回
     * 例:24小时是一天,如果相差23小时,本方法返回0,如果相差25小时,本方法返回1,,即舍去余数
     * @param date1 第一个日期
     * @param date2 第二个日期
     * @return int
     * @author King
     */
    public static int countDays(Date date1,Date date2) {
    	int hours = countHours(date1,date2);
        return (int) hours / 24;
    }

	    
    
   	/**
   	 * 在当前给定时间 如果是 23:59:59就加一秒</br>
   	 * @param date Date日期类型
     * @author King
     * @return Date
   	 */
	public static final Date addOneSecond(Date date) {
		Date ret = null;
		// 格式化为时/分/秒样式
		String dateStr = getDate(date, "yyyyMMddHHmmss");
		if (dateStr.endsWith("235959")) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.SECOND, 1);//加1秒
			ret = cal.getTime();
		}
		return ret;
	}
   	
   	
   	
	 
	public static void main(String[] args) {
		long l1 = new Date().getTime();
		long l2 = new Date().getTime();
		for(int i = 0 ; i < 2000 ; i++){
			Date d = addOneSecond(getDate("2016-05-19 23:59:59"));
			System.out.println(getDate(d,"yyyy-MM-dd HH:mm:ss"));
		}
		
		long l3 = new Date().getTime();
		
		System.out.println(l2 - l1);
		System.out.println(l3 - l2);
		
		
		
	}
}
