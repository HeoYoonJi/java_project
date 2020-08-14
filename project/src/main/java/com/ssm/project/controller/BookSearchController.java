package com.ssm.project.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssm.project.domain.BookVO;
import com.ssm.project.domain.PageVO;
import com.ssm.project.service.BookService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BookSearchController {

	@Autowired
	private BookService bookService;

	@RequestMapping(value="/bookListBySearch.do", produces="application/json; charset=UTF-8")
	public ResponseEntity<String> listBookbySearch(@RequestParam("search_kind") String searchKind,
										@RequestParam("search_word") String searchWord) {
		
		log.info("######### 검색 도서 보기");
		log.info("search_kind : "+searchKind);
		log.info("search_word : "+searchWord);
		
		// HTTP Header 정보 setting
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json; charset=UTF-8");
		
		List<BookVO> searchBookList;
		
		String json="";
        ObjectMapper mapper = new ObjectMapper();
		
		searchBookList= bookService.getBookBySearch(searchKind, searchWord);
       
        try {
            json = mapper.writeValueAsString(searchBookList);
            
	     } catch (JsonProcessingException e) {
	         log.error("json exception !");
	         e.printStackTrace();
	     }
		
		return new ResponseEntity<String>(json, responseHeaders, HttpStatus.OK);
	}
	
}
