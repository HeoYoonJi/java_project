package com.ssm.project;

import static org.junit.Assert.*;

import java.util.List;
import java.util.NavigableMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ssm.project.domain.EntranceVO;
import com.ssm.project.service.BookStockService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
        "file:src/main/webapp/WEB-INF/spring/root-context.xml"
      })
@WebAppConfiguration
public class RandomEntranceTest {
	
	private static final Logger log = LoggerFactory.getLogger(RandomEntranceTest.class);
	
	@Autowired
	private BookStockService svc;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		NavigableMap<String, List<EntranceVO>> entrances = svc.makeRandomEntrance();
		log.info("----------------------------------------------------------------");
		entrances.forEach((x,y)->{log.info("시간대 : "+x+"="+y);});
	}

}
