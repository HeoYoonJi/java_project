package com.ssm.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ssm.project.dao.EntranceDAO;
import com.ssm.project.domain.CustomerEntranceVO;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@WebAppConfiguration
@Slf4j
public class CustomerEntranceListTest {
	
	@Autowired
	private EntranceDAO entranceDAO;
	Date entranceTime;
	String timeBand;
	
	@Before
	public void setup() throws ParseException {
		String time = "2020-05-01 22:00:00";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		entranceTime = dateFormat.parse(time);
		timeBand = "13:30 ~ 14:30";
	}
	
	@Test
	public void test() {
		List<CustomerEntranceVO> customerEntranceList 
		= entranceDAO.getEntrancesTilTimeBand(entranceTime, timeBand);
		
		log.info("customerEntranceList 크기 : "+customerEntranceList.size());
		assertNotNull("not null", customerEntranceList);
		assertEquals(3, customerEntranceList.size());
	}
}
