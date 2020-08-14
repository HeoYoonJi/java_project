package com.ssm.project.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssm.project.domain.BookSearchVO;
import com.ssm.project.domain.BookVO;
import com.ssm.project.domain.SearchVO;

@Repository
public class BookDAOImpl implements BookDAO {
	
	private static final Logger log = LoggerFactory.getLogger(BookDAOImpl.class);
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<BookVO> getAllBooks() {
		
		log.debug("dao getAllBooks");
		return sqlSession.selectList("mapper.BookMapper.getAllBooks");
	}

	@Override
	public int getFirstBookId() {
		
		log.debug("dao getFirstBookId");
		return sqlSession.selectOne("mapper.BookMapper.getFirstBookId");
	}

	@Override
	public void updateImgBatch(int id, String bookImgFilename) {
		
		log.debug("dao updateImgBatch");
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("bookImg", bookImgFilename);
		sqlSession.update("mapper.BookMapper.updateImgBatch", map);
	}

	@Override
	public List<BookVO> getBookBySearch(String searchKind, String searchWord) {
		
		log.info("dao getBookBySearch");
		
		BookSearchVO bookSearchVO = new BookSearchVO();
        bookSearchVO.setSearchKind(searchKind);
        bookSearchVO.setSearchWord(searchWord);
       
        return sqlSession.selectList("mapper.BookMapper.getBookBySearch", bookSearchVO);
	}

}
