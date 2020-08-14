package com.ssm.project;

import java.io.IOException;

import javax.servlet.ServletContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.support.ServletContextResource;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
        "file:src/main/webapp/WEB-INF/spring/root-context.xml"
      })
@WebAppConfiguration
@Slf4j
public class ServletContextPathTest {
	
	@Autowired
	private ServletContext servletContext;
	
	@Test
	public void test() throws IOException {
		
		log.info("servletContext Test");
		Resource resource 
		= new ServletContextResource(servletContext, "/resources/upload/");
		
		log.info("-- file path : "+resource.getFile().getPath());
	}

}
