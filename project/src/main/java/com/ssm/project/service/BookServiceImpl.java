package com.ssm.project.service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.project.dao.BookDAO;
import com.ssm.project.domain.BookVO;

@Service
public class BookServiceImpl implements BookService {
	
	private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);
	
	@Autowired
	private BookDAO bookDao;

	@Transactional(readOnly=true)
	@Override
	public List<BookVO> getAllBooks() {
		log.info("svc getAllBooks");
		return bookDao.getAllBooks();
	}

	@Override
	public void updateImgsBatch(Integer[] books) {
		log.info("svc getFirstBookId");
		
		//첫번째 도서 아이디 조회
		int id = bookDao.getFirstBookId();
		
		//Integer[] books -> List로 변환
		List<Integer> bookImgList = Arrays.asList(books);
		List<BookVO> bookList = this.getAllBooks();
		
		//이미지 업데이트
//		for (int i=0; i<bookList.size(); i++) {
//			log.info("--------id : "+(bookList.get(i).getId()+"="+bookList.get(i).getGenre()));
//		}
		
		Iterator<Integer> it = bookImgList.iterator();
		Iterator<BookVO> it2 = bookList.iterator();
		
		while (it.hasNext()) {
			
			String bookImgFilename = it.next()+".jpg";
			id = it2.next().getId();
			bookDao.updateImgBatch(id, bookImgFilename);
			log.info("id : {}, bookImgFilename : {}",id, bookImgFilename);
		}
		
	}

	@Override
	public List<BookVO> getBookBySearch(String fld, String value) {
		
		log.info("svc getBookBySearch");
		return bookDao.getBookBySearch(fld, value);
	}
	
}
