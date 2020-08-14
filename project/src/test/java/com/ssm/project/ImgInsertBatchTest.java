package com.ssm.project;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ssm.project.service.BookService;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
        "file:src/main/webapp/WEB-INF/spring/root-context.xml"
      })
@WebAppConfiguration
@Slf4j
public class ImgInsertBatchTest {
	
	@Autowired
	private BookService svc;
	
	//IT모바일-소설-자기계발-여행
	Integer books[] = { 
			
			90339705, 90344496, 90617738, 90452827, 89904189, 90004431, 90003539, 90440033, 11781589, 77669043,
			78233628, 90118480, 74419916, 67883659, 67883315, 61929871, 84803146, 85019231, 64494679, 64340061,
			
			90391442, 90619474, 90409154, 90365965, 90367403, 90628036, 90176697, 90443286, 90164265, 90081428,
			90693052, 90537663, 90384912, 90593436, 90636147, 90275173, 89871484, 90427625, 90534413, 37300128,
			
			90397771, 89309569, 79297023, 80499154, 90084493, 90526327, 90686937, 65282018, 90687516, 90866231,
			90594107, 90687185, 90600806, 90602123, 85963416, 42683819, 8949343, 90234637, 59715259, 3736582,
			
			90125491, 82223884, 78524826, 76897656, 78860337, 89016194, 74406341, 79177512, 70962644, 89641193,
			59158805, 67505670, 74036140, 68883051, 64367382, 1394142, 78599358, 378870, 372136, 370132 
			
			};// 책 배열
	
	@Test
	public void test() {
		
		log.info("---------------------------");
		svc.updateImgsBatch(books);
		
	}

}//class
