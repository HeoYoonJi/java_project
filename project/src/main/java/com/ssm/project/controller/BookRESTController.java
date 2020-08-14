package com.ssm.project.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssm.project.domain.BookVO;
import com.ssm.project.service.BookService;

@RestController
public class BookRESTController {
	
	private static final Logger log = LoggerFactory.getLogger(BookRESTController.class);
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value="getAllBooks.do", produces="application/json; charset=UTF-8")
	public ResponseEntity<String> getAllBooks(){
		log.debug("전체 도서 현황 조회");
		
		// HTTP Header 정보 setting
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json; charset=UTF-8");
        
        String json="";
        ObjectMapper mapper = new ObjectMapper();
        
        List<BookVO> books=bookService.getAllBooks();
        
        try {
			json=mapper.writeValueAsString(books);
		} catch (JsonProcessingException e) {
			log.error("json error : "+e);
		}

        return new ResponseEntity<String>(json, responseHeaders, HttpStatus.OK);
	}
	
}
