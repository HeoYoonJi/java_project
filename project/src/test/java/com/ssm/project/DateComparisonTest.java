package com.ssm.project;

import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateComparisonTest {
	
	@Test
	public void test() throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date enTime = dateFormat.parse("2020-07-23");
		Date enTime2 = dateFormat.parse(dateFormat.format(new Date()));
		
		log.info("--enTime : "+enTime);
		log.info("--enTime2 : "+enTime2);
		assertTrue(enTime.equals(enTime2));
	}

}
