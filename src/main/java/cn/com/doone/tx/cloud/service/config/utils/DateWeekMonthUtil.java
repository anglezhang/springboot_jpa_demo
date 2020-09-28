package cn.com.doone.tx.cloud.service.config.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateWeekMonthUtil {
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static Map<String, String> getTimeThisWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		String imptimeBegin = format.format(cal.getTime());
		// System.out.println("所在周星期一的日期：" + imptimeBegin);
		cal.add(Calendar.DATE, 6);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		String imptimeEnd = format.format(cal.getTime());
		// System.out.println("所在周星期日的日期：" + imptimeEnd);
		Map<String, String> newDate = new HashMap<String, String>();
		newDate.put("start", imptimeBegin);
		newDate.put("end", imptimeEnd);
		return newDate;
	}

	public static Map<String, String> getTimeLastWeek() {
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1;
		int offset1 = 1 - dayOfWeek;
		int offset2 = 7 - dayOfWeek;
		calendar1.add(Calendar.DATE, offset1 - 7);
		calendar1.set(Calendar.HOUR_OF_DAY, 0);
		calendar1.set(Calendar.MINUTE, 0);
		calendar1.set(Calendar.SECOND, 0);
		calendar2.add(Calendar.DATE, offset2 - 7);
		calendar2.set(Calendar.HOUR_OF_DAY, 23);
		calendar2.set(Calendar.MINUTE, 59);
		calendar2.set(Calendar.SECOND, 59);
		// System.out.println(sdf.format(calendar1.getTime()));// last Monday
		String lastBeginDate = format.format(calendar1.getTime());
		// System.out.println(sdf.format(calendar2.getTime()));// last Sunday
		String lastEndDate = format.format(calendar2.getTime());
		Map<String, String> newDate = new HashMap<String, String>();
		newDate.put("start", lastBeginDate);
		newDate.put("end", lastEndDate);
		return newDate;
	}

	public static Map<String, String> getTimeThisMonth() {
		// 获取当前月第一天：
		Calendar cala = Calendar.getInstance();
		cala.add(Calendar.MONTH, 0);
		cala.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		cala.set(Calendar.HOUR_OF_DAY, 0);
		cala.set(Calendar.MINUTE, 0);
		cala.set(Calendar.SECOND, 0);
		String start = format.format(cala.getTime());

		// 获取当前月最后一天
		Calendar cale = Calendar.getInstance();
		cale.set(Calendar.DAY_OF_MONTH, cale.getActualMaximum(Calendar.DAY_OF_MONTH));
		cale.set(Calendar.HOUR_OF_DAY, 23);
		cale.set(Calendar.MINUTE, 59);
		cale.set(Calendar.SECOND, 59);
		String end = format.format(cale.getTime());
		Map<String, String> newDate = new HashMap<String, String>();
		newDate.put("start", start);
		newDate.put("end", end);
		return newDate;
	}

	public static Map<String, String> getTimeLastMonth() {
		// 获取前月的第一天
		Calendar cala = Calendar.getInstance();// 获取当前日期
		cala.add(Calendar.MONTH, -1);
		cala.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		cala.set(Calendar.HOUR_OF_DAY, 0);
		cala.set(Calendar.MINUTE, 0);
		cala.set(Calendar.SECOND, 0);
		String start = format.format(cala.getTime());
		// 获取前月的最后一天
		Calendar cale = Calendar.getInstance();
		cale.set(Calendar.DAY_OF_MONTH, 0);// 设置为1号,当前日期既为本月第一天
		cale.set(Calendar.HOUR_OF_DAY, 23);
		cale.set(Calendar.MINUTE, 59);
		cale.set(Calendar.SECOND, 59);
		String end = format.format(cale.getTime());
		Map<String, String> newDate = new HashMap<String, String>();
		newDate.put("start", start);
		newDate.put("end", end);
		return newDate;
	}

	// 当天前后指定天数
	public static Map<String, String> getTimeSomeDay(int someDay) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 0);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		String imptimeEnd = format.format(cal.getTime());
		cal.add(Calendar.DATE, someDay);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		String imptimeBegin = format.format(cal.getTime());
		Map<String, String> newDate = new HashMap<String, String>();
		newDate.put("start", imptimeBegin);
		newDate.put("end", imptimeEnd);
		return newDate;
	}

}
