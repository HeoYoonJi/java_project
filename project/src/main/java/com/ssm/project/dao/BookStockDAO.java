package com.ssm.project.dao;

import java.util.List;

import com.ssm.project.domain.BookStockForScreenVO;
import com.ssm.project.domain.BookStockVO;

public interface BookStockDAO {
	
	/**
	 * 도서 재고 현황 업데이트
	 * @param bookStockVO 도서 재고 객체
	 */
	void updateBookStock(BookStockVO bookStockVO);
	
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
	 * 전체 도서 재고 현황 조회 : 우측 도서 매대 비치 
	 * @return 전체 도서 재고 현황 (화면용)
	 */
	List<BookStockForScreenVO> getAllBookStockForScreen();
	
	/**
	 * 개별 도서 재고량 조회
	 * @param isbn13 isbn13
	 * @return 도서 재고 수량
	 */
	int getStockByIsbn10(String isbn13);
	
	/**
	 * 개별 도서 재고량 갱신
	 * @param isbn13 isbn13
	 * @param quantity 도서 재고 수량(갱신)
	 */
	void updateStock(String isbn13, int quantity);
	
	/**
	 * 전체 도서 재고량 보충(5권씩)
	 */
	void updateAllStock();
	
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
}
