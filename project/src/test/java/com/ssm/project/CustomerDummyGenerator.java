package com.ssm.project;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ssm.project.service.BookStockService;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@WebAppConfiguration
@Slf4j
public class CustomerDummyGenerator {
	
	@Autowired
	private BookStockService svc;
	
	@Test
	public void make() throws ParseException {
		
		// 5월 부터 각 달별로 마지막 일자
		LocalDate birthDate = LocalDate.of(2020, 5, 1);
		int lastDate = birthDate.lengthOfMonth();
		Calendar cal = Calendar.getInstance();

		for (int i=1; i<=lastDate; i++) {
			cal.set(2020, 5-1, i, 22, 0, 0);
			Date date =new Date(cal.getTimeInMillis());
			svc.makeAccrueEntrance(date);
		}
		
		// 6월 부터 각 달별로 마지막 일자
		birthDate = LocalDate.of(2020, 6, 1);
		lastDate = birthDate.lengthOfMonth();
		
		for (int i=1; i<=lastDate; i++) {
			cal.set(2020, 6-1, i, 22, 0, 0);
			Date date =new Date(cal.getTimeInMillis());
			svc.makeAccrueEntrance(date);
		}
		
		// 7월 부터 각 달별로 현재 기준 어제까지의 일자
		birthDate = LocalDate.of(2020, 7, 1);
		cal = Calendar.getInstance();
		lastDate = cal.get(Calendar.DATE)-1;
		
		for (int i=1; i<=lastDate; i++) {
			cal.set(2020, 7-1, i, 22, 0, 0);
			Date date =new Date(cal.getTimeInMillis());
			svc.makeAccrueEntrance(date);
		}
	}

}
