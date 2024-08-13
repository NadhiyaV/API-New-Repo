package api.genericUtility;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	public int getRandomNumber() {
		Random r= new Random();
		int random=r.nextInt(5000);
		return random;
	}
	public String getSystemDate() {
		Date date= new Date();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		String currentDate=sdf.format(date);
		return currentDate;
	}
	public String getSystemDate(int days) {
		Date date= new Date();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		String date1=sdf.format(date);
		Calendar c=sdf.getCalendar();
		c.add(Calendar.DAY_OF_MONTH, days);
		String reqDate=sdf.format(c.getTime());
		return reqDate;
	}
}
