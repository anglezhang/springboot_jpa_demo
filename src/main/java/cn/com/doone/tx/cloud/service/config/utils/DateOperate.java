package cn.com.doone.tx.cloud.service.config.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/*******************************************************************************
 * class name:DateOperate
 *
 * description:日期时间处理类，封装了处理日期时间的常用方法。包括获取当前时间， 按指定格式格式化时间等方法
 *
 ******************************************************************************/
public class DateOperate {

	private static final String DEFAULTSTYLE = "yyyy-MM-dd";//默认时间格式

	public static final String DATE_TIME_STYLE_All="yyyy-MM-dd HH:mm:ss";//时间格式（全）

	/***************************************************************************
	 * 函数功能：获取当前年份
	 *
	 * 参数说明：无
	 *
	 * 返回值：返回当前年份字符串
	 **************************************************************************/
	public static String getNowYear() {
		Calendar cd = Calendar.getInstance();
		int year = cd.get(Calendar.YEAR);
		return Integer.toString(year);
	}

	/***************************************************************************
	 * 函数功能：获取当前月份
	 *
	 * 参数说明：无
	 *
	 * 返回值：返回当前月份字符串
	 **************************************************************************/
	public static String getNowMonth() {
		Calendar cd = Calendar.getInstance();
		int month = cd.get(Calendar.MONTH) + 1;
		if (month < 10)
			return "0" + Integer.toString(month);
		else
			return Integer.toString(month);
	}

	/***************************************************************************
	 * 函数功能：获取当前日期号
	 *
	 * 参数说明：无
	 *
	 * 返回值：返回当前日期号字符串
	 **************************************************************************/
	public static String getNowDay() {
		Calendar cd = Calendar.getInstance();
		int day = cd.get(Calendar.DAY_OF_MONTH);
		if (day < 10)
			return "0" + Integer.toString(day);
		else
			return Integer.toString(day);
	}

	/***************************************************************************
	 * 函数功能：获取当前小时
	 *
	 * 参数说明：无
	 *
	 * 返回值：返回当前小时字符串
	 **************************************************************************/
	public static String getNowHour() {
		Calendar cd = Calendar.getInstance();
		int hour = cd.get(Calendar.HOUR_OF_DAY);
		return Integer.toString(hour);
	}

	/***************************************************************************
	 * 函数功能：获取当前分钟
	 *
	 * 参数说明：无
	 *
	 * 返回值：返回当前分钟字符串
	 **************************************************************************/
	public static String getNowMinute() {
		Calendar cd = Calendar.getInstance();
		int minute = cd.get(Calendar.MINUTE);
		return Integer.toString(minute);
	}

	/***************************************************************************
	 * 函数功能：获取当前秒钟
	 *
	 * 参数说明：无
	 *
	 * 返回值：返回当前秒钟字符串
	 **************************************************************************/
	public static String getNowSecond() {
		Calendar cd = Calendar.getInstance();
		int second = cd.get(Calendar.SECOND);
		return Integer.toString(second);
	}

	/***************************************************************************
	 * 函数功能：按照指定的时间格式，格式化时间字符串
	 *
	 * 参数说明：
	 *
	 * @dateString:时间字符串
	 * @formatStyle:指定的时间格式
	 *
	 * 返回值：返回被格式化后的日期 /
	 **************************************************************************/
	public static Date formatStringToDate(String dateString, String formatStyle) {
		Date date = null;
		if (formatStyle == null || formatStyle.equals("") == true)// 如果没有指定格式，则使用默认格式
			formatStyle = DEFAULTSTYLE;

		try {
			if(dateString==null || dateString.trim().equals(""))
				return null;
			return new SimpleDateFormat(formatStyle).parse(dateString);
		} catch (ParseException e) {
			date = null;
			e.printStackTrace();
		}
		return date;
	}

	/***************************************************************************
	 * 函数功能：按照指定的格式获取当前时间。
	 *
	 * 参数说明：
	 *
	 * @formatStyle:指定的时间格式
	 *
	 * 返回值：返回指定格式的当前日期
	 **************************************************************************/
	public static String getNowDate(String formatStyle)
	{
		if(formatStyle==null || formatStyle.equals(""))
			formatStyle=DEFAULTSTYLE;
		Date now = new Date();
		SimpleDateFormat formater = new SimpleDateFormat(formatStyle);
		return formater.format(now);
	}

	/***************************************************************************
	 * 函数功能：比较时间迟早
	 *
	 * 参数说明：
	 *
	 * @date1:时间对象1
	 *
	 * @date2:时间对象2
	 *
	 * 返回值：如果时间对象1在时间对象2之后则返回true; 否则如果两个时间相等或时间对象1在时间对象2之前返回false;异常也返回false;
	 **************************************************************************/
	public static boolean compareDate(Date date1, Date date2) {
		boolean flag = false;
		try {
			if (date1.after(date2) == true)// 如果时间对象1在时间对象2之后
				flag = true;
			else
				flag = false;
		} catch (Exception ex) {
			flag = false;
			ex.printStackTrace();
		}
		return flag;
	}

	// 获取某一时间段内的双休日天数
	public static int getWeekendDays(String beginDate, String endDate) {
		int count = 0;
		Date begin = formatStringToDate(beginDate, DEFAULTSTYLE);// 将起始时间字符串转换成Date类型
		Date end = formatStringToDate(endDate, DEFAULTSTYLE);// 将结束时间字符串转换成Date类型
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(begin);// 设置从起始时间开始计算
		while (true) {
			Date tempDate = calendar.getTime();// 获取当前参与判断的时间
			if (tempDate.after(end))// 如果在结束时间之后,则退出
				break;
			else
			{
				// 如果是周6或者周日
				if((calendar.get(Calendar.DAY_OF_WEEK) == 7) || (calendar.get(Calendar.DAY_OF_WEEK) == 1))
					count++;// 计数器递增
				calendar.add(Calendar.DATE, 1); // 日期递增
			}
		}
		return count;
	}


	//将日期字符串转换成Calendar对象
	public static Calendar convertDateStringToCalendar(String dateString)
	{
		Date date = formatStringToDate(dateString, DEFAULTSTYLE);// 将起始时间字符串转换成Date类型
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	/**
	 * 将日期字符串转换成Calendar对象
	 * @param dateString 时间字符串
	 * @param timeStyle  格式
	 * @return
	 * @date 2008-12-30
	 * @author huangzr
	 */
	public static Calendar convertDateStringToCalendar(String dateString,String timeStyle)
	{
		if (null == timeStyle || "".equals(timeStyle.trim())) {
			timeStyle = DEFAULTSTYLE;
		}
		Date date = formatStringToDate(dateString, timeStyle);// 将起始时间字符串转换成Date类型
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	/**
	 * 获取当前日期所在的星期中的第一天(星期一)的日期
	 *@param:dateString 日期字符串
	 *@return:返回当前日期所在的星期中的第一天的日期
	 *@throws:
	 */
	public static String getFirstDayOfWeek(String dateString)
	{
		int nowweek=DateOperate.getDayOfWeek(dateString);//获取日期的是星期几
		int distance=1-nowweek;//获取与周一相差的天数
		String firstDayOfWeek=DateOperate.getAnyDate(dateString, distance);
		return firstDayOfWeek;
	}


	/**
	 * 获取当前日期所在的星期中的最后一天(星期天)的日期
	 *@param:dateString 日期字符串
	 *@return:返回当前日期所在的星期中的最后一天的日期
	 *@throws:
	 */
	public static String getLastDayOfWeek(String dateString)
	{
		int nowweek=DateOperate.getDayOfWeek(dateString);//获取日期的是星期几
		int distance=7-nowweek;//获取与周末相差的天数
		String lastDayOfWeek=DateOperate.getAnyDate(dateString, distance);
		return lastDayOfWeek;
	}



	/**
	 * 获取指定年指定月的最后一天号数(支持闰月)
	 *@param:year 年
	 *@param:month 月
	 *@return:返回最后一天号数
	 *@throws:
	 */
	public static String getLastDayOfMonth(String year,String month)
	{
		Calendar   c   =   Calendar.getInstance();
		c.set(Calendar.YEAR, Integer.parseInt(year));
		c.set(Calendar.MONTH,Integer.parseInt(month)-1);
		int day=c.getActualMaximum(Calendar.DAY_OF_MONTH);
		return Integer.toString(day);
	}



	//获取指定的日期星期数
	public static int getDayOfWeek(String dateString)
	{
		Calendar calendar=convertDateStringToCalendar(dateString);
		int week=calendar.get(Calendar.DAY_OF_WEEK);
		if(week==1) //如果是周日,则返回7;
			week=7;
		else
			--week;//减1是因为,第一天是从周日开始算的
		return week;
	}


	//获取与当前日期相距任意天数的日期
	public static String getAnyDate(String nowDate, int days)
	{
		Calendar calendar = convertDateStringToCalendar(nowDate);
		calendar.add(Calendar.DATE, days);
		Date nextDate= calendar.getTime();
		return formatDateToString(nextDate,DEFAULTSTYLE);

	}





	//将日期格式化成指定格式的字符串
	public static String formatDateToString(Date date,String formatStyle)
	{
		if (formatStyle == null || formatStyle.equals("") == true)// 如果没有指定格式，则使用默认格式
			formatStyle = DEFAULTSTYLE;
		SimpleDateFormat   formatter = new SimpleDateFormat(formatStyle);
		return formatter.format(date);
	}

	// 获取两个日期之间的天数差
	public static long getApartDate(String startDate, String endDate) {
		long result=0;
		try {
			Date begin = formatStringToDate(startDate, DEFAULTSTYLE);
			Date end = formatStringToDate(endDate, DEFAULTSTYLE);
			long l = end.getTime()-begin.getTime();
			result = l / (24 * 60 * 60 * 1000);

		} catch (Exception ex) {
			result=-1;
			ex.printStackTrace();
		}
		return result;
	}

	//获取指定月份的上一个月份
	public static String getFrontMonth(int month)
	{
		int frontMonth=0;

		if(month>12 || month<1)//如果传入的月份是不合法的
			frontMonth=-1;
		else
		{
			if(month==1)//如果传入的月份是一月份
				frontMonth=12;//则上一个月就是12月了
			else//否则,上一个月就是当前月减1
				frontMonth=--month;
		}
		if (frontMonth < 10)
			return "0" + Integer.toString(frontMonth);
		else
			return Integer.toString(frontMonth);
	}

	/**
	 * 日期相减
	 * Author linh
	 * date 20100111
	 */
	public static String dateMinus(int i, String formatDate, int dateType){
		try {
			Calendar c = Calendar.getInstance();
			//c.add(Calendar.DATE,i);
			c.add(dateType,i);
			SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
			String now = sdf.format(c.getTime());
			return now;
		} catch (RuntimeException e) {
			return "";
		}
	}

	public static String dateAdd(Date date, int i, String formatDate, int dateType){
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(dateType,i);
			SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
			String now = sdf.format(c.getTime());
			return now;
		} catch (RuntimeException e) {
			return "";
		}
	}

	public static String getRandomString(int length) { //length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**秒数转字符串 */
	public static String formatSecond(Long s){
		String  timeStr = "00:00:00";
		if(s!=null){
			Integer days = (int)(s /(60 * 60 * 24));
			Integer hours =(int)(s/(60*60) - days * 24);
			String hoursStr = hours+"";
			if (hours<10){
				hoursStr = "0"+hoursStr;
			}
			Integer minutes = (int)(s/60 - hours*60 - days * 24 * 60);
			String minutesStr = minutes+"";
			if (minutes<10){
				minutesStr = "0"+minutesStr;
			}
			Integer seconds = (int)(s-minutes*60-hours*60*60 - days * 24 * 60 * 60);
			String secondsStr = seconds+"";
			if (seconds<10){
				secondsStr = "0"+secondsStr;
			}
			return hoursStr+":"+minutesStr+":"+secondsStr;
		}
		return timeStr ;
	}

	public static String timesToDtSr(String times,String formatType){
		if(times==null||times.equals("")){
			return "";
		}
		Long timesLong = Long.parseLong(times);
		//将Long类型转化为Date
		Date date = new Date(timesLong);
		//将Date类型格式化
		SimpleDateFormat sdf=new SimpleDateFormat(formatType);
		String dateString = sdf.format(date);
		return dateString;
	}
	


	/**
	 * 获取当前时间是周几
	 * @return 周几
	 */
	public static int getNowDayOfWeek(){
		Calendar cd = Calendar.getInstance();
		int dayWeek = cd.get(Calendar.DAY_OF_WEEK);
		if(dayWeek==1) //如果是周日,则返回7;
			dayWeek=7;
		else
			--dayWeek;//减1是因为,第一天是从周日开始算的
		return dayWeek;
	}
	
	/**
	 * 获取每隔半小时的整时间时分秒时间，[0,15)取向下取整点 [15,45)向中间取整半小时，[45,60)向上取整
	 * 注意  可取23:45:00之后去00:00:00
	 * @return
	 */
	public static String getNowHalfAnHour() {
		Calendar cd = Calendar.getInstance();
		int hour = cd.get(Calendar.HOUR_OF_DAY);
		int minute = cd.get(Calendar.MINUTE);
		String hm = ":00:00";
		if (hour == 23 && minute >= 45) {
			return "00:00:00";
		}
		if (minute >= 45) {
			hour++;
		} else if (minute >= 15 && minute <45) {
			hm =":30:00";
		} 
		String hoursStr = String.valueOf(hour);
		if (hour < 10) {
			hoursStr = "0"+hoursStr;
		} 
		hoursStr += hm;
		return hoursStr;
	}
	
	/**
	 * 获取一年前的时间
	 * @param formatDate 
	 * @return
	 */
	public static String getBeforeYear(String formatStyle) {
		if(formatStyle==null || formatStyle.equals(""))
			formatStyle=DEFAULTSTYLE;
		SimpleDateFormat format = new SimpleDateFormat(formatStyle);
		Calendar c = Calendar.getInstance();
	    c.setTime(new Date());
        c.add(Calendar.YEAR, -1);
        Date y = c.getTime();
        return format.format(y);
	}
	
	/**
	 * 格式化时间 [0,15)取向下取整点 [15,45)向中间取整半小时，[45,60)向上取整
	 * @param date
	 * @return
	 */
	public static String getTime(Date date) {
		Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int iday = c.get(Calendar.DAY_OF_YEAR);
		
		if (hour == 23 && minute >= 45) {
			c.set(Calendar.DAY_OF_YEAR, iday+1);
		}
		
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int day = c.get(Calendar.DATE);
		
		String timeStr = String.valueOf(year);
		
		if (month<10) {
			timeStr += "-0"+month;
		} else {
			timeStr += "-"+month;
		}
		
		if (day<10) {
			timeStr += "-0"+day;
		} else {
			timeStr += "-"+day;
		}
		
		if (hour == 23 && minute >= 45) {
			timeStr += " 00:00:00";
			return timeStr;
		}
		
		if (minute > 45) {
			hour++;
			minute = 0;
		} else if (minute >= 15 && minute <45) {
			minute = 30;
		} else {
			minute = 0;
		}
		
		if (hour<10) {
			timeStr += " 0" + hour;
		}else {
			timeStr += " " + hour;
		}
		
		if (minute < 30) {
			timeStr += ":0" + minute + ":00";
		} else {
			timeStr += ":" + minute + ":00";
		}
		return timeStr;
	}
	
	/**
	 * 获取当前时间是周几
	 * @return 周几
	 */
	public static int getNowDayOfWeek(Date date){
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		int dayWeek = cd.get(Calendar.DAY_OF_WEEK);
		if(dayWeek==1) //如果是周日,则返回7;
			dayWeek=7;
		else
			--dayWeek;//减1是因为,第一天是从周日开始算的
		return dayWeek;
	}
	
	/***************************************************************************
	 * 函数功能：获取当前日期号
	 *
	 * 参数说明：无
	 *
	 * 返回值：返回当前日期号字符串
	 **************************************************************************/
	public static String getNowDay(Date date) {
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		int day = cd.get(Calendar.DAY_OF_MONTH);
		if (day < 10)
			return "0" + Integer.toString(day);
		else
			return Integer.toString(day);
	}
	
}