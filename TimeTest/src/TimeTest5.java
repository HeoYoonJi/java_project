import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTest5 {

	public static void main(String[] args) throws ParseException {

		// Date -> String -> Date
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = dateFormat.format(date);
		Date date1 = dateFormat.parse(time); // Date

		String entranceTime = dateFormat.format(date1);
		entranceTime = entranceTime.substring(0, 11) + "22:00:00";
		date1 = dateFormat.parse(entranceTime);

		System.out.println("date1 : " + date1);
	}

}
