package core.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间格式转换工具类
 * @author unclesky4 09/09/2017
 *
 */
public class DateUtils {

	/**
	 * example: Sep 9, 2017 8:17:19 PM
	 * @return
	 */
	public static String getMediumDate() {
		Date date = new Date();
		DateFormat mediumFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
		return mediumFormat.format(date);
	}
	
	/**
	 * example: Saturday, September 9, 2017 8:17:19 PM CST
	 * @return
	 */
	public static String getFullFormatDate() {
		Date date = new Date();
		DateFormat fullFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);
		return fullFormat.format(date);
	}
	
	/**
	 * example: Saturday, September 9, 2017 8:17:19 PM CST
	 * @return
	 */
	public static String getNowDate() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(date);
	}
}
