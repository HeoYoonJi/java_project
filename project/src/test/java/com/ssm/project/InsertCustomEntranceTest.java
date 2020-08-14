package com.ssm.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ssm.project.dao.EntranceDAO;
import com.ssm.project.service.BookStockService;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
        "file:src/main/webapp/WEB-INF/spring/root-context.xml"
      })
@WebAppConfiguration
@Slf4j
public class InsertCustomEntranceTest {
	
	@Autowired
	private EntranceDAO entranceDAO;
	private Date entranceTime;
	private String timeBand;
	
	@Before
	public void setup() throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		entranceTime = dateFormat.parse("2020-07-22 22:00:00");
		timeBand = "15:30 ~ 16:30";
	}
	
	@Test
	public void test() {
		
		log.info("entranceTime : "+entranceTime);
		log.info("timeBand : "+timeBand);
		

//		if (entranceDAO.getEntrancesByTimeBand(entranceTime, timeBand).size() == 0) {
//			// 방문 현황 데이터베이스 저장
//			entranceDAO.insertEntrance(customerEntranceVO);
//			log.info("---------------미중복:");
//		}else {
//			log.info("-----------------중복:");
//		}
	}
	
}
