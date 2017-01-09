package helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeHelper {

	public static String getTimeStamp(){
		
		String timeStamp = new SimpleDateFormat("dd/MM/yyyy-hh:mm:ss").format(Calendar.getInstance().getTime());
		
		return timeStamp;
	}
	
}
