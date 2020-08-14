package com.ssm.project.controller;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssm.project.dao.BookStockDAO;
import com.ssm.project.domain.EntranceVO;
import com.ssm.project.service.BookStockService;

@RestController
public class EntranceRESTController {

	private static final Logger log = LoggerFactory.getLogger(EntranceRESTController.class);
	
	@Autowired
	private BookStockService bookStockService;
	
	@Autowired
	private BookStockDAO bookStockDAO;
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value="makeRandomEntrances.do", produces="application/json; charset=UTF-8")
	public ResponseEntity<String> makeRandomEntrances(){
		log.debug("################### 서점 내방 고객 데이터 생성 ###################");
		
		// HTTP Header 정보 setting
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json; charset=UTF-8");
        
        String json="";
        ObjectMapper mapper = new ObjectMapper();
        
        NavigableMap<String, List<EntranceVO>> entrances = bookStockService.makeRandomEntrance();
        log.info("entrance : "+entrances);
        
        try {
			json=mapper.writeValueAsString(entrances);
		} catch (JsonProcessingException e) {
			log.error("json error : "+e);
		}

        return new ResponseEntity<String>(json, responseHeaders, HttpStatus.OK);
	}
	
	@RequestMapping(value="makeRandomEntrancesByTimeBand.do",
			produces="application/json; charset=UTF-8")
	public ResponseEntity<String> makeRandomEntrances(@RequestParam("entranceDate") String entranceDate, 
			@RequestParam("entranceTime") String entranceTime) throws ParseException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		log.debug("################### 서점 내방 고객 데이터 생성(시간대 입력) ###################");
		
		// HTTP Header 정보 setting
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json; charset=UTF-8");
        
        entranceTime = entranceDate +" "+ entranceTime;
        log.debug("--------- 입장 시간 entranceTime:"+entranceTime); //2020-08-04 16:00:00
        
        String json="";
        ObjectMapper mapper = new ObjectMapper();
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date enTime = dateFormat.parse(entranceTime);
		
        NavigableMap<String, List<EntranceVO>> entrances = bookStockService.makeRandomEntrance(enTime);
        //log.info("----------------------------- entrances test --------------------------------");
        //entrances.forEach((k, v) -> {log.info(k+"="+v);});
        //log.info("-------------------------------------------------------------");
        
        // 내방고객 도서 구매에 따른 재고량 변경 수정(금일 날짜에 한함)
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		enTime = dateFormat.parse(entranceTime);
		Date today = dateFormat.parse(dateFormat.format(new Date()));
		
		log.info("---- enTime : "+enTime);
		log.info("---- today : "+today);
		
		// 금일 대비 과거 일자 선택시 현황만 나오되 도서 재고 업데이트에 반영 안됨(재고 변동이 없어야함)
		// 도서 재고에 영향을 주는 것은 오늘(금일) 날짜만 하되 시간대별로 1회만 적용
		if(enTime.equals(today)) {
			log.info("금일 재고 반영");
			
			// DB 테이블(stock_update_yn_tbl) 점검
			String updateYn = ""; // 업데이트 여부 상태 변수
			
			// 마지막 시간대
			String lastTimeBandToday = bookStockService.getTimeBand(enTime);
			
			//10:30 ~ 11:30 부터 20:30 ~ 21:30 까지 재고 반영 여부 점검
			String timeBand = "";
			for (int i=1; i<=bookStockService.getOrderByTimeBand(lastTimeBandToday); i++) {
				try {
					timeBand = bookStockService.getTimeBandByOrder(i);
					updateYn = bookStockService.getStockUpdateYN(entranceTime.substring(0,10), timeBand);
					
					log.info("**updateYn : "+updateYn);
					
					// 업데이트 여부 현황 없을 시 1회만 적용 (시간대별로 적용)
					if(updateYn.equals("n")) {
						updateYn = "y";
						bookStockService.updateStockUpdateYN(entranceTime.substring(0,10), timeBand, "y");
						// bookStockService.updateStockUpdateYN(entranceTime.substring(0,10), timeBand, updateYn);
						bookStockService.updateBookStocks(entrances);
						
						updateYn = "n"; //초기화
					}
				} catch (NullPointerException e) {
					// 해당 일자 재고 업데이트 여부 현황 없을 때 
					log.info("-----insert : ");
					
					log.info("entranceTime : "+entranceTime.substring(0,10));
					log.info("timeBand : "+timeBand);
					
					updateYn = updateYn == null ? "n" : updateYn;
					
					log.info("updateYn : "+updateYn);
					
					if(updateYn.equals("n")) {
						log.info("-----저장-----");
						// updateYn = "y";
						bookStockService.insertStockUpdateYN(entranceTime.substring(0,10), timeBand, "y");
						// bookStockService.updateStockUpdateYN(entranceTime.substring(0,10), timeBand, updateYn);
						bookStockService.updateBookStocks(entrances);
						
						updateYn = "n"; //초기화
					}
				
				} //try-catch end
				
			} //for-end
			
		} //if end
		else {
			log.info("특정일 재고 반영");
			
			// DB 테이블(stock_update_yn_tbl) 점검
			String updateYn = ""; // 업데이트 여부 상태 변수
			
			// 마지막 시간대
			String lastTimeBandToday = bookStockService.getTimeBand(enTime);
			
			//10:30 ~ 11:30 부터 20:30 ~ 21:30 까지 재고 반영 여부 점검
			String timeBand = "";
			for (int i=1; i<=bookStockService.getOrderByTimeBand(lastTimeBandToday); i++) {
				try {
					timeBand = bookStockService.getTimeBandByOrder(i);
					updateYn = bookStockService.getStockUpdateYN(entranceTime.substring(0,10), timeBand);
					
					log.info("**updateYn : "+updateYn);
					
					// 업데이트 여부 현황 없을 시 1회만 적용 (시간대별로 적용)
					if(updateYn.equals("n")) {
						updateYn = "y";
						bookStockService.updateStockUpdateYN(entranceTime.substring(0,10), timeBand, "y");
						// bookStockService.updateStockUpdateYN(entranceTime.substring(0,10), timeBand, updateYn);
						bookStockService.updateBookStocks(entrances);
						
						updateYn = "n"; //초기화
					}
				} catch (NullPointerException e) {
					// 해당 일자 재고 업데이트 여부 현황 없을 때 
					log.info("-----insert : ");
					
					log.info("entranceTime : "+entranceTime.substring(0,10));
					log.info("timeBand : "+timeBand);
					
					updateYn = updateYn == null ? "n" : updateYn;
					
					log.info("updateYn : "+updateYn);
					
					if(updateYn.equals("n")) {
						log.info("-----저장-----");
						// updateYn = "y";
						bookStockService.insertStockUpdateYN(entranceTime.substring(0,10), timeBand, "y");
						// bookStockService.updateStockUpdateYN(entranceTime.substring(0,10), timeBand, updateYn);
						bookStockService.updateBookStocks(entrances);
						
						updateYn = "n"; //초기화
					}
				
				} //try-catch end
				
			} //for-end
		} //else-end
        
        log.info("########## entrance : "+entrances);
        
        try {
			json=mapper.writeValueAsString(entrances);
		} catch (JsonProcessingException e) {
			log.error("json error : "+e);
		}
        
        return new ResponseEntity<String>(json, responseHeaders, HttpStatus.OK);
	}
}
