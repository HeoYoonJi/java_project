package com.ssm.project;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ssm.project.domain.CustomerEntranceVO;
import com.ssm.project.domain.DemoVO;
import com.ssm.project.service.BookStockService;
import com.ssm.project.service.BookStockServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@WebAppConfiguration
@Slf4j
public class MethodTest2 {

	@Autowired
	private BookStockServiceImpl svc;

	CustomerEntranceVO customerEntranceVO;

	String methodName;
	String tempBooks;

	@Before
	public void setup() {
		customerEntranceVO = new CustomerEntranceVO();
		methodName = "setVisit1";
		tempBooks = "tmp";
	}

	@Test
	public void test() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {

		try {
			svc.callSetMethod(customerEntranceVO, methodName, tempBooks);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			log.error("------------ reflection error");
			e.printStackTrace();
		}

		assertEquals("OK", "tmp", customerEntranceVO.getVisit1());

	}

}
