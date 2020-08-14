import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTest2 {
	
	/**
	 * 날짜 입력 시 시간대 출력
	 * 
	 * ex) 2020-07-16 16:58:00
	 * timeBand1 : 15:30
	 * timeBand2 : 16:30
	 * timeBand3 : 17:30
	 * timeBand1 ~ timeBand2 : 15:30 ~ 16:30
	 * timeBand2 ~ timeBand3 : 16:30 ~ 17:30
	 * 
	 * @param entranceTime
	 * @return
	 * @throws ParseException
	 */
	public static String getTimeBand(Date entranceTime) throws ParseException {
		System.out.println("시간대 변환");
		
		long time = entranceTime.getTime();
		//금일 날짜 년-월-일
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		String today = dt.format(entranceTime);
		
		System.out.println("금일 날짜 : "+today);
		
		// 1. 현재시간(15:55)
		// 2. 현재 시간 기준으로 인근 시간대 확보 (14:30 ~ 15:30 또는 15:30 ~ 16:30)
		// 3. 현재 시(hour) 우선 확보
		// Date todayHour = new Date();
		SimpleDateFormat dh = new SimpleDateFormat("HH");
		int hour = Integer.parseInt(dh.format(entranceTime));
		
		System.out.println("현재 시(hour) : "+hour);
		
		long entranceTimeLong = entranceTime.getTime();
		// 2020-07-16 15:30:00(String) -> Date -> long
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date date1 = dateFormat.parse(today+" "+(hour-1)+":30:00");
		String timeBand1 = (hour-1)+":30";
		long time1 = date1.getTime();
		
		Date date2 = dateFormat.parse(today+" "+hour+":30:00");
		String timeBand2 = hour+":30";
		long time2 = date2.getTime();
		
		String timeBand3 = (hour+1)+":30";
		
		String result = "";
		
		if (entranceTimeLong >= time1 && entranceTimeLong < time2) {
			result = timeBand1 + " ~ " + timeBand2;
		} else {
			result = timeBand2 + " ~ " + timeBand3;
		}
		
		return result;
	}

	public static void main(String[] args) throws ParseException {
		
		//System.out.println(TimeTest2.getTimeBand(new Date()));
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = dateFormat.parse("2020-07-24 22:00:00");
		System.out.println(TimeTest2.getTimeBand(date));
		
	}//main

}//class
