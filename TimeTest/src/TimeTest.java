import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTest {

	public static void main(String[] args) throws ParseException {
		
		Date time = new Date();
		System.out.println("금일 날짜 : "+time);
		
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		String entranceTime = dt.format(time);
		
		System.out.println("입장 시간 : "+entranceTime);
		
		long time1 = time.getTime();
		//2020-07-16 15:30:00(String) -> Date -> long
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = dateFormat.parse("2020-07-16 15:00:00");
		long time2 = date.getTime();
		
		if (time1<time2) {
			System.out.println("15시 00분 이전");
		} else {
			System.out.println("15시 00분 이후");
		}
		
	}

}
