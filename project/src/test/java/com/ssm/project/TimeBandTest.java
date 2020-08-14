package com.ssm.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ssm.project.dao.BookStockDAO;
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
public class TimeBandTest {
	
	@Autowired
	private BookStockService bookStockService;
	
	@Autowired
	private BookStockDAO bookStockDAO;
	
	@Autowired
	private SqlSession sqlSession;
	
	private String entranceTime;
	private String entranceDate;
	private String timeBand;
	private String updateYn;
	
	@Before
	public void setup() throws ParseException {
		entranceTime = "2020-08-01 17:00:00";
		//entranceDate = "2020-08-02";
	}
	
	@Test
	public void test() throws ParseException {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateEntranceTime = dateFormat.parse(entranceTime);
		String lastTimeBandToday = bookStockService.getTimeBand(dateEntranceTime);
		entranceDate = entranceTime.substring(0,10);
		
		for (int i=1; i<=bookStockService.getOrderByTimeBand(lastTimeBandToday); i++) {
			timeBand = bookStockService.getTimeBandByOrder(i);
			log.info("timeBand : "+timeBand);
			
			updateYn = bookStockDAO.getStockUpdateYN(entranceDate, timeBand);
//			Map<String,String> map = new HashMap<>();
//			map.put("stockDate", entranceDate);
//			map.put("timeBand", timeBand);
//			updateYn = sqlSession.selectOne("mapper.BookMapper.getStockUpdateYN", map);
			log.info("updateYn : "+updateYn);
		}
	}
	
}
