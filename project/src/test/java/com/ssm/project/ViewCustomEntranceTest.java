package com.ssm.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ssm.project.dao.EntranceDAO;
import com.ssm.project.domain.CustomerEntranceVO;
import com.ssm.project.domain.EntranceVO;
import com.ssm.project.service.BookStockService;
import com.ssm.project.service.BookStockServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
        "file:src/main/webapp/WEB-INF/spring/root-context.xml"
      })
@WebAppConfiguration
@Slf4j
public class ViewCustomEntranceTest {
	
	@Autowired
	private EntranceDAO entranceDAO;
	
	@Autowired
	private BookStockService bookStockService;
	
	// private Date entranceTime;
	Date enTime;
	private String timeBand;
	private String entranceTime2;
	private String entranceDate;
	
	@Before
	public void setup() throws ParseException {
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		entranceTime = dateFormat.parse("2020-07-24 22:00:00");
//		timeBand = "20:30 ~ 21:30";
		
		entranceDate = "2020-07-24";
		entranceTime2 = "21:00:00";
		entranceTime2 = entranceDate +" "+ entranceTime2;
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		enTime = dateFormat.parse(entranceTime2);
		timeBand = bookStockService.getTimeBand(enTime);
		
		log.info("timeBand : "+timeBand);
	}
	
	@Test
	public void test() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		NavigableMap<String, List<EntranceVO>> map = new TreeMap<>();
		List<EntranceVO> tempEntranceList = new ArrayList<EntranceVO>();
		
		List<CustomerEntranceVO> customerEntranceList
			= entranceDAO.getEntrancesTilTimeBand(enTime, timeBand);
		
		EntranceVO entranceVO;
		CustomerEntranceVO customerEntranceVO;

		for (int i = 0; i < customerEntranceList.size(); i++) {

			customerEntranceVO = customerEntranceList.get(i);
			timeBand = customerEntranceVO.getEntranceTimeBand();
			tempEntranceList = new ArrayList<>();

			for (int j = 0; j < 17; j++) { // visit1~visit17

				String methodName = "getVisit" + (j + 1);
				String books = bookStockService.callGetMethod(customerEntranceVO, methodName);

				int bookNum = 0;
				String[] booksArr = null;
				String temp = books.replaceAll("\\[", "").replaceAll("\\]", "");
				List<String> booksList = new ArrayList<>();

				if (temp.contentEquals("")) {
					bookNum = 0;
				} else if (!books.contains(",")) {
					booksArr = new String[1];
					booksArr[0] = temp;
					bookNum = 1;
					booksList.add(temp);
				} else {
					// System.out.println(temp);
					booksArr = temp.split(",");
					bookNum = booksArr.length;
					booksList = Arrays.asList(booksArr);
				}

				entranceVO = new EntranceVO();
				// log.info("길이 : " + bookNum);
				entranceVO.setEntranceNum(bookNum);

				entranceVO.setBooks(booksList);

				tempEntranceList.add(entranceVO);

			} // for2-end

			map.put(timeBand, tempEntranceList);
		} // for1-end
		
		for (int i=0; i<10; i++) {
			log.info("-------------------------------------------------------------------------------");
			map.forEach((k, v) -> {log.info(k+"="+v);});
		}
	}
	
}
