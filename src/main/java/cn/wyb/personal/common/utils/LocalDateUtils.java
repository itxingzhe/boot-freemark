package cn.wyb.personal.common.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * LocalDateUtils: (请描述这个类).
 *
 * @author wangyibin
 * @date 2018/9/12 16:30
 * @see
 */
public class LocalDateUtils {

	/**
	 * 常用变量
	 */
	public static final String DATE_FORMAT_FULL = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_ALL = "yyyy-MM-dd a hh:mm:ss SSS E ";
	public static final String DATE_FORMAT_YMD = "yyyy-MM-dd";
	public static final String DATE_FORMAT_HMS = "HH:mm:ss";
	public static final String DATE_FORMAT_HM = "HH:mm";
	public static final String DATE_FORMAT_YMDHM = "yyyy-MM-dd HH:mm";
	public static final String DATE_FORMAT_YMDHMS = "yyyyMMddHHmmss";
	public static final long ONE_DAY_MILLS = 3600000 * 24;
	public static final int WEEK_DAYS = 7;
	private static final int dateLength = DATE_FORMAT_YMDHM.length();

	/**
	 * LocalDate转为Date.
	 *
	 * @author wangyibin
	 * @date 2018/9/12 16:36
	 * @param localDateTime
	 * @return java.util.Date
	 *
	 */
	public static Date localDate2Date(LocalDateTime localDateTime){
		if(localDateTime != null){
			return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		}
		return null;
	}

}
