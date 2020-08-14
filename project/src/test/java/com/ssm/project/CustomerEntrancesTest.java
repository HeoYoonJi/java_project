package com.ssm.project;

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

import com.ssm.project.service.BookStockService;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
        "file:src/main/webapp/WEB-INF/spring/root-context.xml"
      })
@WebAppConfiguration
@Slf4j
public class CustomerEntrancesTest {
	
	@Autowired
	private BookStockService svc;
	private Date entranceTime;
	
	@Before
	public void setup() throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		entranceTime = dateFormat.parse("2020-07-17 12:15:00");
	}
	
	@Test
	public void test() throws ParseException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		log.debug("///////////////");
		// assertNull("결과 없음", svc.makeRandomEntrance(entranceTime));
		assertNotNull("결과 있음", svc.makeRandomEntrance(entranceTime));
		// svc.makeRandomEntrance(entranceTime).forEach((k,v) -> { log.debug(k+"="+v); });
	}

}
