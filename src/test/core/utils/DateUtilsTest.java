package test.core.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class DateUtilsTest {

	@Test
	public void testGetMediumDate() {
		Date date = new Date();
		DateFormat mediumFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
		System.out.println(mediumFormat.format(date));
	}

	@Test
	public void testGetFullFormatDate() {
		Date date = new Date();
		DateFormat fullFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);
		System.out.println(fullFormat.format(date));
	}

	@Test
	public void testGetNowDate() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(dateFormat.format(date));
	}

}
