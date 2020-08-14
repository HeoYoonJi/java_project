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

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
        "file:src/main/webapp/WEB-INF/spring/root-context.xml"
      })
@WebAppConfiguration
@Slf4j
public class InsertMemberTest {
	
	@Autowired
	private SqlSession sqlSession;
	
	MemberVO memberVO;
	
	@Before
	public void setup() throws ParseException {
		
//		// Date -> String -> Date
		java.sql.Date temp = java.sql.Date.valueOf("2020-07-16");
		//String time = "2020-07-16 00:00:00";
		String time = temp.toString();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = (Date)dateFormat.parse(time); // Date

		
		memberVO = new MemberVO();
		
		memberVO.setMemberId("gjgj4545");
		memberVO.setMemberNickname("닉네임");
		memberVO.setMemberName("이름");
		memberVO.setMemberEmail("sinsm0322@naver.com");
		memberVO.setMemberPhone("010-1234-5678");
		memberVO.setMemberBirth(date1);
		memberVO.setMemberGender("f");
		memberVO.setMemberZip("12345");
		memberVO.setMemberAddress("서울");
	}
	
	@Test
	public void test() {
		
		log.info("memberVO :"+memberVO);
		//assertEquals("gjgj4545", memberVO.getMemberId());
		
		sqlSession.insert("com.ssm.project.mapper.MemberMapper.insertMember", memberVO);
	}

}
