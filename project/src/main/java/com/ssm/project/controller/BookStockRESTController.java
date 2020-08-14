package com.ssm.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssm.project.domain.BookStockForScreenVO;
import com.ssm.project.service.BookStockService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BookStockRESTController {
	
	@Autowired
	private BookStockService bookStockService;
	
	@RequestMapping(value="getAllBookStock.do", produces="application/json; charset=UTF-8")
	public ResponseEntity<String> getAllBookStock(){
		log.debug("도서 재고 현황");
		
		// HTTP Header 정보 setting
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json; charset=UTF-8");
        
        String json="";
        ObjectMapper mapper = new ObjectMapper();
        
        List<BookStockForScreenVO> bookStock=bookStockService.getAllBookStockForScreen();
        
        try {
			json=mapper.writeValueAsString(bookStock);
		} catch (JsonProcessingException e) {
			log.error("json error : "+e);
		}

        return new ResponseEntity<String>(json, responseHeaders, HttpStatus.OK);
	}
	
	@RequestMapping(value = "updateAllStock.do", produces = "text/plain; charset=UTF-8")
	public ResponseEntity<String> updateAllStock(){
		String msg ="";
		
		// HTTP Header 정보 setting
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/plain; charset=UTF-8");
        
		msg = bookStockService.updateAllStock() == true ? "보충 완료" : "보충 실패";
		
		return new ResponseEntity<String>(msg, responseHeaders, HttpStatus.OK);
	}
	
}

