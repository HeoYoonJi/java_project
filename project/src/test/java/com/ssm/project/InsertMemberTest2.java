package com.ssm.project;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ssm.project.domain.MemberVO;
import com.ssm.project.domain.TempVO;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
        "file:src/main/webapp/WEB-INF/spring/root-context.xml"
      })
@WebAppConfiguration
@Slf4j
public class InsertMemberTest2 {
	
	@Autowired
	private SqlSession sqlSession;
	
	Date birthday;
	TempVO tempVO;
	
	@Before
	public void setup() throws ParseException {
		
		tempVO = new TempVO();
//		// Date -> String -> Date
		java.sql.Date temp = java.sql.Date.valueOf("2020-07-16");
		//String time = "2020-07-16 00:00:00";
		String time = temp.toString();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		birthday = new Date(dateFormat.parse(time).getTime()); // Date
		tempVO.setBirthday(birthday);

	}
	
	@Test
	public void test() {
		sqlSession.insert("com.ssm.project.mapper.MemberMapper.inserttemp", tempVO);
	}

}
