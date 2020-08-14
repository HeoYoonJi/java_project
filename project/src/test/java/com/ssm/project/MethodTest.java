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
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
        "file:src/main/webapp/WEB-INF/spring/root-context.xml"
      })
@WebAppConfiguration
@Slf4j
public class MethodTest {
	
	@Autowired
	private BookStockServiceImpl svc;
	
	CustomerEntranceVO customerEntranceVO;
	DemoVO demoVO;
	
	String methodName;
	String tempBooks;
	
	public static void reflectionTest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		DemoVO demoVO = new DemoVO();
		 
        Class<String>[] arg = new Class[1];
        arg[0] = String.class;
       
        Method method = demoVO.getClass().getDeclaredMethod("setVisit1", arg);
        method.invoke(demoVO, "tmp");
	}
	
	@Before
	public void setup() {
		customerEntranceVO = new CustomerEntranceVO();
		methodName = "setVisit1";
		tempBooks = "tmp";
		demoVO = new DemoVO();
	}
	
	@Test
	public void test() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		/*
		 * try { svc.callMethod(methodName, tempBooks); } catch (IllegalAccessException
		 * | IllegalArgumentException | InvocationTargetException |
		 * NoSuchMethodException | SecurityException e) {
		 * log.error("------------ reflection error"); e.printStackTrace(); }
		 */
		
		reflectionTest();
		
		// assertEquals("OK", "tmp", customerEntranceVO.getVisit1());
		assertEquals("OK", "tmp", demoVO.getVisit1());
		
	}

}
