import java.util.Map;
import java.util.TreeMap;

public class TimeTest3 {

	public static void main(String[] args) {
		
		Map<String, Integer> map = new TreeMap<>();
		String timeBand = "15:30 ~ 16:30";
		map.put("10:30 ~ 11:30", 1);
		map.put("11:30 ~ 12:30", 2);
		map.put("12:30 ~ 13:30", 3);
		map.put("13:30 ~ 14:30", 4);
		map.put("14:30 ~ 15:30", 5);
		map.put("15:30 ~ 16:30", 6);
		map.put("16:30 ~ 17:30", 7);
		map.put("17:30 ~ 18:30", 8);
		map.put("18:30 ~ 19:30", 9);
		map.put("19:30 ~ 20:30", 10);
		map.put("20:30 ~ 21:30", 11);
		
		System.out.println(map.get(timeBand));
	}

}
