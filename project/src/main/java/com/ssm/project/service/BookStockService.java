package com.ssm.project.service;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.NavigableMap;

import com.ssm.project.domain.BookStockForScreenVO;
import com.ssm.project.domain.BookStockVO;
import com.ssm.project.domain.CustomerEntranceVO;
import com.ssm.project.domain.EntranceVO;

public interface BookStockService {
	
	/**
	 * 도서 재고 수량 임의 변경
	 */
	void modifyAutoStock();
	
	/**
	 * 전체 도서 재고 현황 조회
	 * @return 전체 도서 재고 현황
	 */
	List<BookStockVO> getAllBookStock();
	
	/**
	 * 개별 도서 재고 현황 조회
	 * @param bookId 도서 아이디
	 * @return 개별 도서 재고 현황
	 */
	BookStockVO getBookStockByBookId(int bookId);

	/**
	 * 임의 내방 고객 현황 시뮬레이터 
	 * @return 임의 내방 고객 현황 
	 */
	NavigableMap<String,List<EntranceVO>> makeRandomEntrance();
	
	/**
	 * 임의 내방 고객 현황 시뮬레이터
	 * @param entranceTime 입장 시간 ex) 2020-07-16 15:10:00
	 * @return 임의 내방 고객 현황 
	 * @throws ParseException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	NavigableMap<String,List<EntranceVO>> makeRandomEntrance(Date entranceTime) throws ParseException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;
	
	/**
	 * 임의 내방 고객 누적 현황 시뮬레이터
	 * @param entranceTime 입장 시간 ex) 2020-07-16 15:10:00
	 * @throws ParseException 
	 */
	void makeAccrueEntrance(Date entranceTime) throws ParseException;

	/**
	 * 고객 별 임의 도서(isbn13) 선택
	 * @param num 도서 권 수 
	 * @return 선택 도서 리스트
	 */
	List<String> chooseBooks(int num);
	
	/**
	 * 전체 도서 재고 현황 조회 : 우측 도서 매대 비치
	 * @return 전체 도서 재고 현황 (화면용)
	 */
	List<BookStockForScreenVO> getAllBookStockForScreen();
	
	
	/**
	 * 전체 도서 재고 현황 업데이트 (수정)
	 * @param entrances 시간대별 내방고객 현황
	 */
	void updateBookStocks(NavigableMap<String, List<EntranceVO>> entrances);
	
	/**
	 * 전체 도서 재고량 보충(5권씩)
	 * @return 보충 성공 여부 
	 */
	boolean updateAllStock();

	/**
	 * 메서드명으로 get 메서드(getter) 호출(reflection 활용) ex) getVisit1
	 * 
	 * @param customerVO 내방 고객 VO
	 * @param methodName 호출할 get 메서드명
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @return 구매한 도서
	 */
	public String callGetMethod(CustomerEntranceVO customerVO, String methodName) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;

	/**
	 * 날짜 입력 시 시간대 출력
	 * 
	 * ex) 2020-07-16 16:58:00 timeBand1 : 15:30 timeBand2 : 16:30 timeBand3 : 17:30
	 * timeBand1 ~ timeBand2 : 15:30 ~ 16:30 timeBand2 ~ timeBand3 : 16:30 ~ 17:30
	 * 
	 * @param entranceTime
	 * @return
	 * @throws ParseException
	 */
	public String getTimeBand(Date entranceTime) throws ParseException;
	
	/**
	 * 현재 시간을 DB 테이블(customer_entrance_tbl)의 entrance_date에 22시로 변환 하여 저장
	 * ex) 2020-07-24 15:00:00 -> 2020-07-24 22:00:00
	 * 
	 * @param date 원래 날짜
	 * @return 변환 날짜
	 * @throws ParseException
	 */
	public Date parseEntranceDate(Date date) throws ParseException;
	
	/**
	 * 재고 업데이트 반영 여부 조회
	 * @param stockDate 재고 업데이트 반영일
	 * @param timeBand 시간대
	 * @return 재고 업데이트 반영 여부 
	 */
	String getStockUpdateYN(String stockDate, String timeBand);
	
	/**
	 * 재고 업데이트 반영 여부 생성(각 날짜 시간대별)
	 * @param stockDate 재고 업데이트 반영일
	 * @param timeBand 시간대
	 * @param updateYn 재고 업데이트 반영 여부 
	 */
	void insertStockUpdateYN(String stockDate, String timeBand, String updateYn);
	
	/**
	 * 재고 업데이트 반영 여부 업데이트 수정(각 날짜 시간대별)
	 * @param stockDate 재고 업데이트 반영일
	 * @param timeBand 시간대
	 * @param updateYn 재고 업데이트 반영 여부 
	 */
	void updateStockUpdateYN(String stockDate, String timeBand, String updateYn);

	/**
	 * 순서별 시간대 출력
	 * 
	 * @param order 순서
	 * @return 시간대
	 */
	String getTimeBandByOrder(int order);

	/**
	 * 시간대 별 순서 출력
	 * 
	 * @param timeBand 시간대
	 * @return 순서
	 */
	int getOrderByTimeBand(String timeBand);

}
