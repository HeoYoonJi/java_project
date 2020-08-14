package com.ssm.project;

import static org.junit.Assert.assertEquals;

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
import com.ssm.project.service.BookStockServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@WebAppConfiguration
@Slf4j
public class EntranceListToVOTest {
	
	@Autowired
	private EntranceDAO entranceDAO;
	
	@Autowired
	private BookStockServiceImpl bookStockServiceImpl;
	
	Date entranceTime;
	String timeBand;
	
	@Before
	public void setup() throws ParseException {
		// 13:00:00 -> 22:00:00 반드시 변경(입력 시간)
		// String time = "2020-05-01 22:00:00";
		String time = "2020-05-01 13:00:00";
		time = time.substring(0, 11)+"22:00:00";
		log.info("시간 : "+time);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		entranceTime = dateFormat.parse(time);
		timeBand = "12:30 ~ 13:30";
		//Parameters: 2020-05-01 13:00:00.0(Timestamp), 12:30 ~ 13:30(String)
	}
	
	@Test
	public void test() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		NavigableMap<String, List<EntranceVO>> map = new TreeMap<>();
	
		List<CustomerEntranceVO> customerEntranceList 
		= entranceDAO.getEntrancesTilTimeBand(entranceTime, timeBand);
		
		List<EntranceVO> tempEntranceList = new ArrayList<>();
		
		EntranceVO entranceVO;
		CustomerEntranceVO customerEntranceVO;
		
		assertEquals("[9791165790530, 9791196730031, 9788968480652]",
				customerEntranceList.get(0).getVisit1());
		
		//////////////////////////////////////////////////////////////////////////
		
		/*
		String books = customerEntranceList.get(0).getVisit1();
		String[] booksArr = books.replaceAll("\\[", "").replaceAll("\\]", "").split("\\,");
		entranceVO = new EntranceVO();
		entranceVO.setEntranceNum(booksArr.length);
		entranceVO.setBooks(Arrays.asList(booksArr));
		
		log.info("--entranceVO : "+entranceVO);*/
		
		//////////////////////////////////////////////////////////////////////////
		
		for (int i=0; i<customerEntranceList.size(); i++) {
			
			customerEntranceVO = customerEntranceList.get(i);
			timeBand = customerEntranceVO.getEntranceTimeBand();
			log.info("----timeBand : "+timeBand);
			
			tempEntranceList = new ArrayList<>();
			
			for (int j=0; j<17; j++) { //visit1~visit17
				
				String methodName = "getVisit" + (j+1);
				String books = bookStockServiceImpl.callGetMethod(customerEntranceVO, methodName);
				
				String[] booksArr = books.replaceAll("\\[", "").replaceAll("\\]", "").split("\\,");
				//log.info("booksArr 길이 : "+booksArr.length);
				entranceVO = new EntranceVO();
				entranceVO.setEntranceNum(booksArr.length);
				entranceVO.setBooks(Arrays.asList(booksArr));
				
				tempEntranceList.add(entranceVO);
				
			}// for2-end
			
			map.put(timeBand, tempEntranceList);
			
		}// for1-end
		
		log.info("---map size : "+map.size());
		// map.forEach((k, v) -> {log.info("k : "+k+"="+v);});
		
		//log.info("books : "+map.get("10:30 ~ 11:30"));
		assertEquals("9788915031333", map.get(timeBand).get(0).getBooks().get(0));
	}

}
