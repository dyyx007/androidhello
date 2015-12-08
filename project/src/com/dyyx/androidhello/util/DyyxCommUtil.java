package com.dyyx.androidhello.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DyyxCommUtil {

	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:MM:ss";
	public static final String TIME_FORMAT = "HH:MM:ss";

	public static final String EMPTY = "";

	public static boolean isBlank(String s) {
		if (s == null || EMPTY.equals(s)) {
			return true;
		}
		String tmp = s.trim();
		return tmp.isEmpty();
	}

	public static int getInt(String s, int def) {
		try {
			return Integer.parseInt(s);
		} catch (Throwable e) {
			return def;
		}
	}

	public static String getDateString(Date date, String format) {
		if (date == null) {
			return "";
		}
		if (isBlank(format)) {
			format = DATE_TIME_FORMAT;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} catch (Throwable e) {
			return date.toString();
		}
	}

}
