package com.ssm.project;

import static org.junit.Assert.assertEquals;

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

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@WebAppConfiguration
@Slf4j
public class EntrancesTest {

	@Autowired
	private EntranceDAO dao;
	Date entranceDate;
	String entranceTimeBand;

	@Before
	public void setup() throws ParseException {
		String time = "2020-05-01 22:00:00";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		entranceDate = dateFormat.parse(time);
		entranceTimeBand = "10:30 ~ 11:30";
	}

	@Test
	public void test() {
		
		assertEquals("OK", "[9791165790530, 9791196730031, 9788968480652]", 
					dao.getEntrancesByDate(entranceDate, entranceTimeBand).get(0).getVisit1()); 
		
	}

}
