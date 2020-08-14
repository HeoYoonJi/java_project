package com.ssm.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
public class TimeEqualTest {
	
	private String entranceTime;
	private Date enTime;
	private String timeBand;
	private Date today;
	
	@Before
	public void setup() throws ParseException {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		entranceTime = "2020-07-28" +" "+ "11:00:00";
		enTime = dateFormat.parse(entranceTime);
		today = dateFormat.parse(dateFormat.format(new Date()));
	}
	
	@Test
	public void test() {
		
		log.info("--enTime : "+enTime);
		log.info("--today : "+today);
		
		enTime.equals(today);
		assertTrue("동등", enTime.equals(today));
		// assertEquals(enTime, today);
		
	}
	
}
