package com.ssm.project.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssm.project.domain.BookStockForScreenVO;
import com.ssm.project.domain.BookStockVO;

import lombok.extern.java.Log;

@Repository
public class BookStockDAOImpl implements BookStockDAO {
	
	private static final Logger log = LoggerFactory.getLogger(BookStockDAOImpl.class);
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void updateBookStock(BookStockVO bookStockVO) {
		
		log.debug("dao updateBookStock");
		sqlSession.update("mapper.BookMapper.updateBookStock", bookStockVO);
	}

	@Override
	public List<BookStockVO> getAllBookStock() {
		log.debug("dao getAllBookStock");
		return sqlSession.selectList("mapper.BookMapper.getAllBookStock");
	}

	@Override
	public BookStockVO getBookStockByBookId(int bookId) {
		log.debug("dao getBookStockByBookId");
		return sqlSession.selectOne("mapper.BookMapper.getBookStockByBookId", bookId);
	}

	@Override
	public List<BookStockForScreenVO> getAllBookStockForScreen() {
		log.debug("dao getAllBookStockForScreen");
		return sqlSession.selectList("mapper.BookMapper.getAllBookStockForScreen");
	}

	@Override
	public int getStockByIsbn10(String isbn13) {
		log.info("dao getStockByIsbn10");
		return sqlSession.selectOne("mapper.BookMapper.getStockByIsbn10", isbn13);
	}

	@Override
	public void updateStock(String isbn13, int quantity) {
		log.info("dao updateStock");
		Map<String, Object> map = new HashMap<>();
		map.put("isbn13", isbn13);
		map.put("quantity", quantity);
		sqlSession.update("mapper.BookMapper.updateStock", map);
	}

	@Override
	public void updateAllStock() {
		log.info("dao updateAllStock");
		sqlSession.update("mapper.BookMapper.updateAllStock");
	}

	@Override
	public String getStockUpdateYN(String stockDate, String timeBand) {
		log.info("dao getStockUpdateYN");
		Map<String,String> map = new HashMap<>();
		map.put("stockDate", stockDate);
		map.put("timeBand", timeBand);
		return sqlSession.selectOne("mapper.BookMapper.getStockUpdateYN", map);
	}

	@Override
	public void insertStockUpdateYN(String stockDate, String timeBand, String updateYn) {
		log.info("dao insertStockUpdateYN");
		Map<String,String> map = new HashMap<>();
		map.put("stockDate", stockDate);
		map.put("timeBand", timeBand);
		map.put("updateYn", updateYn);
		sqlSession.insert("mapper.BookMapper.insertStockUpdateYN", map);
	}

	@Override
	public void updateStockUpdateYN(String stockDate, String timeBand, String updateYn) {
		log.info("dao updateStockUpdateYN");
		Map<String,String> map = new HashMap<>();
		map.put("stockDate", stockDate);
		map.put("timeBand", timeBand);
		map.put("updateYn", updateYn);
		sqlSession.update("mapper.BookMapper.updateStockUpdateYN", map);
	}

}
