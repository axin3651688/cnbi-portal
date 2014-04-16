package org.cnbi.utils.date;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *  时间工具类
 * company: cnbisoft
 */
public class DateUtil {
	
	final static Log logger = LogFactory.getLog(DateUtil.class);
	
	/**
	 * 相对期间处理
	 * @param formula  表达式  0$0 or0&0
	 * @param period 一个期间 如：201203
	 * @return
	 */
	public static String getRelativePeriod(String formula, String period){
		String year = period.substring(0, 4), month = period.substring(4, 6);
		int real_year = Integer.parseInt(year), real_month = Integer.parseInt(month);
		if (formula.contains("$"))
			formula = formula.replace("$", "&");
		if (formula.contains("&")) {
			String[] temp = formula.split("\\&");
			int temp_year = Integer.parseInt(temp[0]), temp_month = Integer.parseInt(temp[1]);
			if (temp_year == 0 && temp_month == 0)
				return period;
			if (temp_year < 0) {
				real_year = real_year - Math.abs(temp_year);
			}
			if (temp_month < 0) {
				if (real_month == 1) {
					real_year = real_year - 1;
					real_month = 12;
				} else {
					real_month = real_month - Math.abs(temp_month);
				}
			}
			if (temp_month > 0)
				real_month = temp_month;
			String ms = String.valueOf(real_month);
			if (ms.length() == 1) {
				ms = "0" + ms;
			}
			return real_year + ms;
		} else {
			logger.error("进行了非法操作，不应该来到这里的，因为！" + formula + "不符合所定义");
		}

		return period;
	}

	
	/**
	 * 返回当前字符串型日期
	 * @return String 返回的字符串型日
	 */
	public static String getCurDate(String format) {
		if (null == format || "".equals(format)) {
			format = "yyyy-MM-dd";
		}
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpledateformat = new SimpleDateFormat(format);
		String strDate = simpledateformat.format(calendar.getTime());
		return strDate;
	}


	/**
	 * 根据日期id取得年龄的范�?
	 * @param ageId
	 *            日期id
	 * @return 范围的数�?
	 */
	public static String[] getAge(String ageId) {
		String[] ages = new String[2];
		int age = Integer.parseInt(ageId);
		if (age == 0) {
			ages[0] = "1";
			ages[1] = "10";
		} else if (age == 1) {
			ages[0] = "11";
			ages[1] = "14";
		} else if (age == 2) {
			ages[0] = "15";
			ages[1] = "19";
		} else if (age == 3) {
			ages[0] = "20";
			ages[1] = "24";
		} else if (age == 4) {
			ages[0] = "25";
			ages[1] = "29";
		} else if (age == 5) {
			ages[0] = "30";
			ages[1] = "34";
		} else if (age == 6) {
			ages[0] = "35";
			ages[1] = "39";
		} else if (age == 7) {
			ages[0] = "40";
			ages[1] = "44";
		} else if (age == 8) {
			ages[0] = "45";
			ages[1] = "49";
		} else if (age == 9) {
			ages[0] = "50";
			ages[1] = "54";
		} else if (age == 10) {
			ages[0] = "55";
			ages[1] = "59";
		} else if (age == 11) {
			ages[0] = "60";
			ages[1] = "64";
		} else if (age == 12) {
			ages[0] = "65";
			ages[1] = "69";
		} else if (age == 13) {
			ages[0] = "70";
			ages[1] = "74";
		} else if (age == 14) {
			ages[0] = "75";
			ages[1] = "79";
		} else if (age == 15) {
			ages[0] = "80";
			ages[1] = "84";
		} else if (age == 16) {
			ages[0] = "85";
			ages[1] = "89";
		} else if (age == 17) {
			ages[0] = "90";
			ages[1] = "94";
		} else if (age == 18) {
			ages[0] = "95";
			ages[1] = "99";
		} else if (age == 19) {
			ages[0] = "100";
			ages[1] = "200";
		} else {
			ages[0] = "201";
			ages[1] = "999";// 老不�?
		}

		return ages;
	}

	/**
	 * 根据日期之差取得岁数
	 * 
	 * @param g1
	 *            日历1
	 * @param g2
	 *            日历2
	 * @return 时间的差�?
	 */
	public static int getAge(GregorianCalendar g1, GregorianCalendar g2) {

		int elapsed = 0;
		GregorianCalendar gc1, gc2;
		if (g2.after(g1)) {
			gc2 = (GregorianCalendar) g2.clone();
			gc1 = (GregorianCalendar) g1.clone();
		} else {
			gc2 = (GregorianCalendar) g1.clone();
			gc1 = (GregorianCalendar) g2.clone();
		}

		gc1.clear(Calendar.MILLISECOND);
		gc1.clear(Calendar.SECOND);
		gc1.clear(Calendar.MINUTE);
		gc1.clear(Calendar.HOUR_OF_DAY);
		gc1.clear(Calendar.DATE);
		gc2.clear(Calendar.MILLISECOND);
		gc2.clear(Calendar.SECOND);
		gc2.clear(Calendar.MINUTE);
		gc2.clear(Calendar.HOUR_OF_DAY);
		gc2.clear(Calendar.DATE);
		while (gc1.before(gc2)) {
			gc1.add(Calendar.MONTH, 1);
			elapsed++;

		}

		return elapsed / 12;

	}

	/**
	 * 把日期字符串格式化为Date类型
	 * 
	 * @param s
	 *            要格式化的日期字符串
	 * @param format
	 *            格式:如yyyy-MM-dd
	 * @return 格式化以后的日期
	 */
	public static Date convertDate(String s, String format) {
		SimpleDateFormat formatter = null;
		if (format == null) {
			formatter = new SimpleDateFormat("yyyy-MM-dd");
		} else {
			formatter = new SimpleDateFormat(format);
		}
		ParsePosition pos = new ParsePosition(0);
		Date dt = formatter.parse(s, pos);
		return dt;
	}

	/**
	 * 把Date类型格式化为日期字符�?
	 * 
	 * @param date
	 *            要格式化的日�?
	 * @param format
	 *            格式:如yyyy-MM-dd
	 * @return 返回�?��格式化好的日期字符串
	 */
	public static String formatDate(Date date, String format) {
		if (date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			String str = formatter.format(date);
			return str;
		} else {
			return "";
		}
	}

	/**
	 * 通过日期取得年份
	 * 
	 * @param date
	 *            要取得年份的日期
	 * @return 取得的年�?
	 */
	public static int getYear(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int y = calendar.get(Calendar.YEAR);
		return y;
	}

	/**
	 * 通过日期取得月份
	 * 
	 * @param date
	 *            要取得月份的日期
	 * @return 取得的月�?
	 */
	public static int getMonth(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int m = calendar.get(Calendar.MONTH);
		return m + 1;
	}

	/**
	 * 通过日期取得�?
	 * 
	 * @param date
	 *            要取得天的日�?
	 * @return 取得的天
	 */
	public static int getDay(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int d = calendar.get(Calendar.DATE);
		return d;
	}

	/**
	 * 通过日期取得小时
	 * 
	 * @param date
	 *            要取得小时的日期
	 * @return 取得的小�?
	 */
	public static int getHour(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int h = calendar.get(Calendar.HOUR);
		return h;
	}

	/**
	 * 通过日期取得�?��字段
	 * 
	 * @param date
	 *            要取得月份的日期
	 * @param calendarField
	 *            日期的field
	 * @return �?��的字段�? exp: getDateField(Date date,int Calendar.MONTH) 取得月份
	 */
	public static int getDateField(Date date, int calendarField) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int m = calendar.get(calendarField);
		return m;
	}

	// /**
	// * 取得�?��三年的年份和月份 页面显示效果如下�?2006�?1�?2�?3�?4�?5�?6�?7�?8�?9�?10�?11�?12�?
	// * 2005�?1�?2�?3�?4�?5�?6�?7�?8�?9�?10�?11�?12�?2004�?1�?2�?3�?4�?5�?6�?7�?
	// * 8�?9�?10�?11�?12�?
	// *
	// * @return 装满了年份和月份的List
	// */
	// public static List getLastThreeYearAndMonth() {
	// int currentYear = DateUtil.getYear(new Date());
	// int lastYear = currentYear - 1;
	// int theYearBeforeLastYear = lastYear - 1;
	//
	// int currentYearCurrentMonth = DateUtil.getMonth(new Date());
	//
	// List<Month> currentYearMonths = new ArrayList<Month>();
	// List<Month> lastYearMonths = new ArrayList<Month>();
	// List<Month> theYearBeforeLastYearMonths = new ArrayList<Month>();
	// for (int i = 1; i <= currentYearCurrentMonth; i++) {
	// Month month = new Month();
	// month.setMonth(String.valueOf(i));
	// currentYearMonths.add(month);
	// }
	// for (int i = currentYearCurrentMonth + 1; i <= 12; i++) {
	// Month month = new Month();
	// month.setMonth(String.valueOf(i));
	// month.setShowFlag("0");
	// currentYearMonths.add(month);
	// }
	// for (int i = 1; i <= 12; i++) {
	// Month month = new Month();
	// month.setMonth(String.valueOf(i));
	// lastYearMonths.add(month);
	// theYearBeforeLastYearMonths.add(month);
	// }
	//
	// List<Year> years = new ArrayList<Year>();
	// Year currentY = new Year();
	// currentY.setYear(String.valueOf(currentYear));
	// currentY.setMonths(currentYearMonths);
	//
	// Year lastY = new Year();
	// lastY.setYear(String.valueOf(lastYear));
	// lastY.setMonths(lastYearMonths);
	//
	// Year theYearBeforeLastY = new Year();
	// theYearBeforeLastY.setYear(String.valueOf(theYearBeforeLastYear));
	// theYearBeforeLastY.setMonths(theYearBeforeLastYearMonths);
	//
	// years.add(currentY);
	// years.add(lastY);
	// years.add(theYearBeforeLastY);
	// return years;
	// }

	/**
	 * 得到当前日期前day的日�?lilan
	 * 
	 * @param date
	 *            日期
	 * @param day
	 *            前几�?
	 * @return 前几天的日期
	 */
	public static Date subDay(Date date, int day) {
		try {
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(date);
			cal.add(Calendar.DATE, day * -1);
			String dateStr = formatDate(cal.getTime(), "yyyy-MM-dd");
			return convertDate(dateStr, "yyyy-MM-dd");
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 得到当前日期后day的日�?
	 * 
	 * @param date
	 *            日期
	 * @param day
	 *            后几�?
	 * @return 后几天的日期
	 */
	public static Date dateOfAfter(Date date, int day) {
		try {
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(date);
			cal.add(Calendar.DATE, day);
			String dateStr = formatDate(cal.getTime(), "yyyy-MM-dd");
			return convertDate(dateStr, "yyyy-MM-dd");
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得过去N小时之前的时�?
	 * 
	 * @param date
	 *            日期
	 * @param n
	 *            小时
	 * @return N小时前的时间
	 */
	public static Date getDateByHour(Date date, int n) {
		try {
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(date);
			cal.add(Calendar.HOUR, n * -1);
			return cal.getTime();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获得两个时间之间相差的天�?将两个时间中较大的一个减去较小的�?��获得天数�?
	 * 
	 * @param date1
	 *            时间1
	 * @param date2
	 *            时间2
	 * @return 天数 long
	 */
	public static long dateDiff(Date date1, Date date2) {
		long millis1 = date1.getTime();
		long millis2 = date2.getTime();
		long millis;
		if (millis1 > millis2) {
			millis = (millis1 - millis2) / 1000 / 60 / 60 / 24;
		} else {
			millis = (millis2 - millis1) / 1000 / 60 / 60 / 24;
		}
		return millis;
	}

	/**
	 * 获得两个时间之间相差的小�?
	 * 
	 * @param date1
	 *            时间1
	 * @param date2
	 *            时间2
	 * @return 小时 long
	 */
	public static long hourDiff(Date date1, Date date2) {
		long millis1 = date1.getTime();
		long millis2 = date2.getTime();
		long millis;
		if (millis1 > millis2) {
			millis = (millis1 - millis2) / 1000 / 60 / 60;
		} else {
			millis = (millis2 - millis1) / 1000 / 60 / 60;
		}
		return millis;
	}

	/**
	 * 得到�?��期前的日�?liuhandong
	 * 
	 * @param date
	 *            日期
	 * @param weekDay
	 *            星期�?
	 * @return Date 星期几日�?
	 */
	public static Date getDateOfWeekBefore(Date date, String weekDay) {
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL);
		// Create our Gregorian Calendar.
		GregorianCalendar cal = new GregorianCalendar();
		// Set the date and time of our calendar
		// to the system's date and time
		cal.setTime(date);
		int beforeWeek = cal.get(Calendar.WEEK_OF_YEAR) - 1;
		cal.set(Calendar.WEEK_OF_YEAR, beforeWeek);

		if (weekDay.equals("SUNDAY")) {
			cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		}
		if (weekDay.equals("SATURDAY")) {
			cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		}
		dateFormat.format(cal.getTime());
		return cal.getTime();
	}

	/**
	 * 取得两日期间的周�?
	 * 
	 * @param dateMax
	 *            日期
	 * @param dateMix
	 *            日期
	 * @return weekCount 周数
	 */
	public static long getWeekCount(Date dateMax, Date dateMix) {
		long weekCount = 0;
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(dateMax);
		int weekMax = cal.get(Calendar.WEEK_OF_YEAR);
		cal.setTime(dateMix);
		int weekMix = cal.get(Calendar.WEEK_OF_YEAR);
		weekCount = weekMax - weekMix;
		return weekCount;
	}

	/**
	 * 比较两个日期
	 * 
	 * @param firstDate
	 *            日期1
	 * @param nextDate
	 *            日期2
	 * @param format
	 *            格式
	 * @return boolean
	 */
	public static boolean compareDate(String firstDate, String nextDate, String format) {
		int result = 0;
		Date first = DateUtil.convertDate(firstDate, format);
		Date next = DateUtil.convertDate(nextDate, format);
		result = first.compareTo(next);
		if (result > 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 生日取得年龄
	 * 
	 * @param date
	 *            生日日期
	 * @return Short 年龄
	 */
	public static Short getAgeOfNow(Date date) {
		GregorianCalendar cal = new GregorianCalendar();
		Date today = new Date();

		cal.setTime(date);
		int birthYear = cal.get(Calendar.YEAR);
		int birthMonth = cal.get(Calendar.MONTH);
		int birthDay = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(today);
		int nowYear = cal.get(Calendar.YEAR);
		int nowMonth = cal.get(Calendar.MONTH);
		int nowDay = cal.get(Calendar.DAY_OF_MONTH);
		if (nowMonth - birthMonth > 0 || nowDay - birthDay >= 0 && nowMonth - birthMonth == 0) {
			return Short.parseShort(Integer.valueOf(nowYear - birthYear).toString());
		} else {
			return Short.parseShort(Integer.valueOf(nowYear - birthYear - 1).toString());
		}
	}

	/**
	 * 日期与时间中间加空格
	 * 
	 * @param str
	 *            String
	 * @param len
	 *            Integer
	 * @return 结果
	 */
	public static String getStringSplit(String str, Integer len) {
		String date = str.substring(0, len);
		String time = str.substring(len);
		return date + "  " + time;
	}

	/**
	 * 日期与时间中间加空格
	 * 
	 * @param str
	 *            String
	 * @return 结果
	 */
	public static String getStringSplit(String str) {
		return getStringSplit(str, 11);
	}

	/**
	 * 比较两个日期的天�?
	 * 
	 * @param date1
	 *            Date
	 * @param date2
	 *            Date
	 * @return Long
	 */
	public static Long getDiffDate(Date date1, Date date2) {
		String dateStr1 = DateUtil.formatDate(date1, "yyyy-MM-dd 00:00:00");
		date1 = DateUtil.convertDate(dateStr1, "yyyy-MM-dd 00:00:00");
		String dateStr2 = DateUtil.formatDate(date2, "yyyy-MM-dd 00:00:00");
		date2 = DateUtil.convertDate(dateStr2, "yyyy-MM-dd 00:00:00");
		return DateUtil.dateDiff(date1, date2);
	}

	/**
	 * 得到指定星期几和时间的已经过去的某日
	 * 
	 * @param weekday
	 *            星期�?周日�?周六�?
	 * @param time
	 *            HH:mm:ss
	 * @return Date
	 */
	public static Date getWeekDay(int weekday, String time) {
		Calendar c = Calendar.getInstance();
		String dateStr = formatDate(c.getTime(), "yyyy/MM/dd");
		Calendar cc = Calendar.getInstance();
		cc.setTime(convertDate(dateStr + " " + time, "yyyy/MM/dd HH:mm:ss"));

		int now = c.get(Calendar.DAY_OF_WEEK);
		if (now > weekday) {
			c.add(Calendar.DATE, weekday - now);
			cc.add(Calendar.DATE, weekday - now);
		} else if (now < weekday) {
			c.add(Calendar.DATE, weekday - now - 7);
			cc.add(Calendar.DATE, weekday - now - 7);
		} else {
			if (c.before(cc)) {
				cc.add(Calendar.WEEK_OF_YEAR, -1);
			}
		}
		return cc.getTime();
	}

	/**
	 * 获取查询默认时间
	 * 
	 * @return
	 */
	public static String[] getTimeStr() {
		Calendar rightNow = Calendar.getInstance();
		int year = rightNow.get(Calendar.YEAR), month = rightNow.get(Calendar.MONTH) + 1;
		//day = rightNow.get(Calendar.DATE);
		String period = 8 + "", // System.getProperty("period"),
		unit = "万元";// System.getProperty("unit");// monthStr
		month = month - Integer.parseInt(period);
		if (month <= 0) {
			if (month == 0) {
				month = 12;
			} else {
				month = 12 + month;
			}
			year = year - 1;
		}
		String monthStr = "";
		if (month < 10) {
			monthStr = "0" + month;

		}
		String[] temp = new String[]{String.valueOf(year), monthStr, unit };
		return temp;

	}

	
	
}
