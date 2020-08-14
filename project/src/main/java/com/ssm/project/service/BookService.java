package com.ssm.project.service;

import java.util.List;

import com.ssm.project.domain.BookVO;

public interface BookService {
	
	/**
	 * 전체 도서 현황 조회
	 * @return 전체 도서 현황
	 */
	List<BookVO> getAllBooks();
	
	/**
	 * 이미지 업데이트 배치 작업
	 */
	void updateImgsBatch(Integer[] books);
	
	/**
	 * 특정 필드(제목, 저자, 출판사)를 이용한 검색 조회
	 * 
	 * @param fld 검색할 필드
	 * @param value 필드 값
	 * @return 검색 결과(도서 정보)
	 */
	List<BookVO> getBookBySearch(String fld, String value);

}
