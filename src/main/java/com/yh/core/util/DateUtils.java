package com.yh.core.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtils {

	private static final Logger LOG = LoggerFactory.getLogger(DateUtils.class);

	/**
	 * yyyyMMddHHmmss
	 */
	public static final String FORMAT_14 = "yyyyMMddHHmmss";

	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final String FORMAT_DATETIME_14 = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * yyyy-MM-dd HH:mm:ss.SSS
	 */
	public static final String FORMAT_DATETIME_HM_14 = "yyyy-MM-dd HH:mm:ss.SSS";
	
	/**
	 * yyyy-MM-dd HH:mm:ss.SSS
	 */
	public static final String FORMAT_HM_14 = "yyyyMMddHHmmssSSS";

	/**
	 * yyyyMM
	 */
	public static final String FORMAT_6 = "yyyyMM";
	
	/**
	 * MMdd
	 */
	public static final String FORMAT_7 = "yyMM";

	/**
	 * yyyyMMdd
	 */
	public static final String FORMAT_8 = "yyyyMMdd";

	/**
	 * yyyy-MM-dd
	 */
	public static final String FORMAT_DATE_8 = "yyyy-MM-dd";
	
	/**
	 * yyyy-MM-dd HH:mm
	 */
	public static final String FORMAT_DATETIME_15 = "yyyy-MM-dd HH:mm";

	public static SimpleDateFormat format = new SimpleDateFormat(FORMAT_14);

	private DateUtils() {

	}
	
	public static String format(String time, String rformat, String sformat) {
		SimpleDateFormat fmt = new SimpleDateFormat(rformat);
		Date ndate = null;
		try {
			ndate = fmt.parse(time);
			SimpleDateFormat format = new SimpleDateFormat(sformat);
			return format.format(ndate);
		} catch (ParseException e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}

	public static String format(Date date, String format) {
		SimpleDateFormat sformat = new SimpleDateFormat(format);
		return sformat.format(date);
	}
	
	public static String getCurrentFormat19() {
		SimpleDateFormat sformat = new SimpleDateFormat(FORMAT_DATETIME_14);
		return sformat.format(new Date());
	}
	
	public static String getCurrentFormat10() {
		SimpleDateFormat sformat = new SimpleDateFormat(FORMAT_DATE_8);
		return sformat.format(new Date());
	}

	public static Date convert(String time, String format) {
		SimpleDateFormat sformat = new SimpleDateFormat(format);
		try {
			return sformat.parse(time);
		} catch (ParseException e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	public static String datetimeToString(Date date, String format) {
		if (date == null) {
			date = new Date();
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	public static Date stringToDateTime(String str, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(str);
	}

	public static String datetimeToStringForTags(Date date, String format) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	public static Date stringToDateTimeForTags(String str, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * when date1 earlier than date2 ,return -1 when date1 equals date2 ,return
	 * 0 when date1 later than date2 ,return 1
	 * 
	 * @param DATE1
	 * @param DATE2
	 * @return
	 * @throws TradeMsgException
	 */
	public static int compare_date(String DATE1, String DATE2, String dateFormat) throws Exception {

		DateFormat df = new SimpleDateFormat(dateFormat);

		try {
			Date dt1 = df.parse(DATE1);
			Date dt2;
			dt2 = df.parse(DATE2);

			if (dt1.getTime() > dt2.getTime()) {
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (ParseException e) {
			throw new Exception(e.getMessage(), e);

		}
	}
}
