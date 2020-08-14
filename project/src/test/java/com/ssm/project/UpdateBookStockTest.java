package com.ssm.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ssm.project.dao.BookStockDAO;
import com.ssm.project.dao.EntranceDAO;
import com.ssm.project.domain.EntranceVO;
import com.ssm.project.service.BookStockService;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@WebAppConfiguration
@Slf4j
public class UpdateBookStockTest {
	
	@Autowired
	private BookStockService bookStockService;
	
	@Autowired
	private BookStockDAO bookStockDAO;

	@Test
	public void test() throws ParseException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		log.info("----------------------------");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date enTime = dateFormat.parse("2020-07-22 15:00:00");
		 
		NavigableMap<String, List<EntranceVO>> entrances = bookStockService.makeRandomEntrance(enTime);
		
		Collection<List<EntranceVO>> entranceList = entrances.values();
		Iterator<List<EntranceVO>> it = entranceList.iterator();
		List<EntranceVO> list =null;
		
		// 판매 도서 리스트(중복허용)
		List<String> bookStockList = new ArrayList<>();
		
		while(it.hasNext()) {
			list = it.next();
			
			for(int i=0; i < list.size(); i++) {
				//log.info("----:"+list.get(i));
				bookStockList.addAll(list.get(i).getBooks());
			}//
			//log.info("---------------------------");
		}//while-end
		
		for(String book : bookStockList) {
			log.info("------book:"+book);
		}
		
		// 도서 isbn13, 판매수량 map 구조로 치환
		Set<String> bookSet = new TreeSet<>(bookStockList);
		log.info("------책 중복허용:"+bookStockList.size());
		log.info("------책 종류:"+bookSet.size());
	
		Map<String,Integer> map = new TreeMap<>();

		for(String book : bookSet) {
			int bookcnt = (int)bookStockList.stream().filter(x->x.equals(book)).count();
			map.put(book, bookcnt);
		}
		
		map.forEach((k,v)->{log.info(k+"="+v);});
		
		Set<String> set = map.keySet();
		Iterator<String> stockIt = set.iterator();
		
		log.info("-------------------------------------");
		
		while (stockIt.hasNext()) {
			String isbn13 = stockIt.next();
			int defaultStock = bookStockDAO.getStockByIsbn10(isbn13);
			int newStock = map.get(isbn13);
			int updateStock = defaultStock - newStock <= 0 ? 0 : defaultStock - newStock;
			
			log.info("--isbn13 = {}, updateStock = {} : ", isbn13, updateStock);
			bookStockDAO.updateStock(isbn13, updateStock);
		}
	}
		
}
