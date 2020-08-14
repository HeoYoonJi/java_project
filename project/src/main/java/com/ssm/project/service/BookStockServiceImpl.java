package com.ssm.project.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.project.dao.BookStockDAO;
import com.ssm.project.dao.EntranceDAO;
import com.ssm.project.domain.BookStockForScreenVO;
import com.ssm.project.domain.BookStockVO;
import com.ssm.project.domain.CustomerEntranceVO;
import com.ssm.project.domain.EntranceVO;

@Service
public class BookStockServiceImpl implements BookStockService {

	private static final Logger log = LoggerFactory.getLogger(BookStockServiceImpl.class);

	@Autowired
	private BookStockDAO bookStockDAO;

	@Autowired
	private EntranceDAO entranceDAO;

	@Override
	public void modifyAutoStock() {

		log.debug("service modifyAutoStock");

		BookStockVO bookStockVO;

		List<BookStockVO> books = bookStockDAO.getAllBookStock(); // 전체 도서 재고 현황

		for (int i = 0; i <= 80; i++) {

			// 도서 아이디
			int bookId = books.get(i).getBookId();

			// 도서 비치 수량 , 도서 재고 수량 임의 변경 (재너레이터)
			// 비치수량 한계 : 5개
			// 각 도서 재고량 한계 : 10권
			// 1. 임의로 도서 비치 수량 감소 (소비자 구매)
			// 2. 도서 비치 수량이 0일 때 (소진 시) 다시 5개 채워넣어야 함
			// 3. 도서 재고량 10권에서 -5 (재고량 반영)
			bookStockVO = new BookStockVO();
			bookStockDAO.updateBookStock(bookStockVO);

		} // for

	}// main

	@Override
	public List<BookStockVO> getAllBookStock() {
		log.debug("service getAllBookStock");
		return bookStockDAO.getAllBookStock();
	}

	@Override
	public BookStockVO getBookStockByBookId(int bookId) {
		log.debug("service getBookStockByBookId");
		return bookStockDAO.getBookStockByBookId(bookId);
	}

	@Override
	public List<String> chooseBooks(int num) {

		String books[] = { "9791165211615", "9791163031635", "9791162243039", "9791189909147", "9791163031567",
				"9788965402701", "9791162242971", "9788997924585", "9788968480652", "9791162242087", "9791160508796",
				"9791162242780", "9791163030911", "9791158391348", "9791158391331", "9791162240861", "9791163031222",
				"9791165210205", "9791162241196", "9791189184018", "9791190052337", "9788954672771", "9788937473272",
				"9791165790530", "9788954672214", "9788936434427", "9791189770051", "9788982182587", "9788936434410",
				"9791164791200", "9788974565282", "9788954442732", "9788946473317", "9791196977481", "9788954672863",
				"9788936438142", "9788937473258", "9791196654214", "9791196730031", "9788936434267", "9788947545822",
				"9791190382175", "9791187142560", "9791188388905", "9788998294762", "9788927811190", "9788901243665",
				"9788947544085", "9791163220428", "9791190776066", "9791162541517", "9788993525809", "9791190312158",
				"9791195843848", "9791162143056", "9788960863859", "9788962605815", "9791190233866", "9788950974152",
				"9788901106434", "9791189159634", "9782067242401", "9788925566870", "9791190032209", "9788952738660",
				"9788962918656", "9791189166960", "9791162998748", "9791188502127", "9788926898567", "9788970301204",
				"9791157766499", "9791185831725", "9791196370275", "9791196370237", "9788989701569", "9788925567754",
				"9788985673914", "9788986427288", "9788915031333" };// 책 배열

		List<String> bookList = Arrays.asList(books);

		// for (int i=0; i<10; i++) {
		Collections.shuffle(bookList);
		// bookList.stream().limit(5).forEach(x->System.out.print(x+" "));
		return bookList.subList(0, num);
		// }
	}

	@Override
	public NavigableMap<String, List<EntranceVO>> makeRandomEntrance() {
		log.debug("service makeRandomEntrance");
		NavigableMap<String, List<EntranceVO>> map = new TreeMap<>();
		List<EntranceVO> tempEntranceList = new ArrayList<EntranceVO>();
		EntranceVO tempEntranceVO = null;

		// 1~5 사이의 난수 발생
		int cnt = 0; // 초과횟수(1~5사이의 난수 발생 초과 횟수)
		int noBuyCnt = 0; // 미구매 시 횟수
		int totalCnt = 180; // 총 반복 횟수(고객 방문 횟수)
		int startTime = 10; // 오전 10시
		int timeCnt = 0; // 시간 카운터

		String timeLine = "";// 시간대 변수 ex) 10:30 ~ 11:30

		// 관련근거 : 2018 출판산업 실태조사 p.7 고객현황 > 방문객 및 구매율
		// http://portal.kocca.kr/portal/bbs/view/B0000204/1940026.do?menuNo=200247&categorys=4&subcate=58&cateCode=0#
		// 방문객 중 실제로 도서를 구입하는 비율 63.6%

		for (int i = 0; i < totalCnt; i++) {

			int rand = 0;

			if (timeCnt == 2 || (timeCnt >= 8 && timeCnt <= 10)) { // 12:30~13:30, 18:30~21:30 -> 내방 인원 피크 타임

				rand = (int) (Math.random() * 6);

				int weight = (int) (Math.random() * 5);
				rand = (i + weight) % ((int) (Math.random() * 20) + 1) == 0 ? 0 : rand;
				rand = rand >= 5 ? 5 : rand; // 초과 방지

			} else {

				rand = (int) (Math.random() * 4);

				rand = (i + 1) % ((int) (Math.random() * 10) + 1) == 0 ? 0 : rand;
				rand = rand >= 3 ? 3 : rand; // 초과 방지
				// rand = (i+1) % ((int)(Math.random()*15)+1) == 0 ? 0 : rand;
			}

			// System.out.println("rand : "+rand);

			if (rand > 5) {
				// System.out.println("초과");
				cnt++;
			}

			// if ((i % 17 == 0 && i > 0) || i == 179) {
			if (i < 179) {
				// System.out.println("i="+i);

				/*
				 * if (i < 179) { //System.out.printf(" : %d:%d ~ %d:%d\n", startTime + timeCnt,
				 * 30, startTime + timeCnt + 1, 30); } else { System.out.print(rand + "=" +
				 * this.chooseBooks(rand) + " "); System.out.printf(" : %d:%d ~ %d:%d",
				 * startTime + timeCnt, 30, startTime + timeCnt + 1, 30); }
				 */

				// 시간대 입력
				timeLine = String.format("%d:%d ~ %d:%d", startTime + timeCnt, 30, startTime + timeCnt + 1, 30);

				// System.out.println("시간대 : "+timeLine);

				if ((i % 17 == 0 && i > 0) || i == 179) {
					timeCnt++;
				}
			}

			if (i < 179) {
				// System.out.print(rand + " ");
				tempEntranceVO = new EntranceVO();
				tempEntranceVO.setEntranceNum(rand);
				tempEntranceVO.setBooks(this.chooseBooks(rand));
				tempEntranceList.add(tempEntranceVO);

				map.put(timeLine, tempEntranceList);

				if (i % 17 == 0) { // 시간대별 인원삽입 중복 방지
					// System.out.println(i);
					tempEntranceList = new ArrayList<EntranceVO>(); // 초기화
				}

				// System.out.print(rand + "=" + tempEntranceList + " ");
			}

			noBuyCnt = rand == 0 ? noBuyCnt + 1 : noBuyCnt; // 미구매 횟수

		} // for

		// System.out.println("\n---------------------------------------------");
		// System.out.println("초과횟수 : " + cnt);
		// System.out.println("미구매횟수 : " + noBuyCnt);
		// System.out.println("구매횟수 :" + (totalCnt - noBuyCnt));
		// System.out.println("총 방문횟수 : " + totalCnt);
		// float buyRate = ((float) (totalCnt - noBuyCnt) / totalCnt) * 100;
		// System.out.printf("구매비율 : %f\n ", buyRate);

		return map;
	}

	@Override
	public NavigableMap<String, List<EntranceVO>> makeRandomEntrance(Date entranceTime)
			throws ParseException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		log.info("-------------service makeRandomEntrance");
		NavigableMap<String, List<EntranceVO>> map = new TreeMap<>();
		List<EntranceVO> tempEntranceList = new ArrayList<EntranceVO>();
		EntranceVO tempEntranceVO = null;

		// 시간 입력시 시간대 검색/출력 ex) 2020-07-16 12:15:00 -> 11:30 ~ 12:30
		String timeBand = getTimeBand(entranceTime);

		// 1~5 사이의 난수 발생
		int cnt = 0; // 초과횟수(1~5사이의 난수 발생 초과 횟수)
		int noBuyCnt = 0; // 미구매 시 횟수
		int totalCnt = 180; // 총 반복 횟수(고객 방문 횟수)
		int startTime = 10; // 오전 10시
		int timeCnt = 0; // 시간 카운터

		// 1. 현재 시간대 이전 방문객만 출력
		// 2. DB에 저장된 방문객 우선 적용
		// 3. DB에서 지정날짜 현재 시간대 이전 현황 조회
		// 3-1. 현황이 있으면 우선 조회/적용
		// 3-2. 현황이 없으면 임의 생성(난수발생)
		int totalEntCnt = entranceDAO.getEntrances().size();

		log.info("#### entranceTime : "+entranceTime);
		
		Date dateEntranceTime = this.parseEntranceDate(entranceTime);
		log.info("dateEntranceTime : "+dateEntranceTime);
		
		List<CustomerEntranceVO> customerEntrances = entranceDAO.getEntrancesByDate(dateEntranceTime, timeBand); // 패치
		log.info("customerEntrances(그날의 데이터베이스 현황) : " + customerEntrances.size());

		if (totalEntCnt == 0) {

			log.info("전체 방문 현황 없을 때 : ");
			// 방문 현황이 없을 때 : 현재 시간대 이전 방문객 생성
			// 시간대별 -> (17명/시간대)*n
			int order = getOrderByTimeBand(timeBand);
			/*
			 * 13:00:00 입력시 10:30 ~ 12:30까지의 두시간대의 영역(order-1)까지만 생성 21:30:00 마지막 시간대면 180명
			 */
			//
			log.info("timeBand:" + timeBand);
			log.info("order:" + order);
			totalCnt = order == 12 ? 180 : 17 * (order - 1);

			String timeLine = "";// 시간대 변수 ex) 10:30 ~ 11:30

			// 관련근거 : 2018 출판산업 실태조사 p.7 고객현황 > 방문객 및 구매율
			// http://portal.kocca.kr/portal/bbs/view/B0000204/1940026.do?menuNo=200247&categorys=4&subcate=58&cateCode=0#
			// 방문객 중 실제로 도서를 구입하는 비율 63.6%

			for (int i = 0; i <= totalCnt; i++) {

				int rand = 0;

				if (timeCnt == 2 || (timeCnt >= 8 && timeCnt <= 10)) { // 12:30~13:30, 18:30~21:30 -> 내방 인원 피크 타임

					rand = (int) (Math.random() * 6);

					int weight = (int) (Math.random() * 5);
					rand = (i + weight) % ((int) (Math.random() * 20) + 1) == 0 ? 0 : rand;
					rand = rand >= 5 ? 5 : rand; // 초과 방지

				} else {

					rand = (int) (Math.random() * 4);

					rand = (i + 1) % ((int) (Math.random() * 10) + 1) == 0 ? 0 : rand;
					rand = rand >= 3 ? 3 : rand; // 초과 방지
				}

				if (rand > 5) {
					cnt++;
				}

				if (i < totalCnt - 1) {
					// 시간대 입력
					timeLine = String.format("%d:%d ~ %d:%d", startTime + timeCnt, 30, startTime + timeCnt + 1, 30);

					if ((i % 17 == 0 && i > 0) || i == 179) {
						timeCnt++;
					}
				}

				if (i <= 180) {
					tempEntranceVO = new EntranceVO();
					tempEntranceVO.setEntranceNum(rand);
					tempEntranceVO.setBooks(this.chooseBooks(rand));
					tempEntranceList.add(tempEntranceVO);

					map.put(timeLine, tempEntranceList);

					if (i % 17 == 0) { // 시간대별 인원삽입 중복 방지
						tempEntranceList = new ArrayList<EntranceVO>(); // 초기화
					}

				}

				noBuyCnt = rand == 0 ? noBuyCnt + 1 : noBuyCnt; // 미구매 횟수

			} // for

			log.info("/////////////// map size : " + map.size());

			// DB에 내방 인원 현황 저장
			CustomerEntranceVO customerEntranceVO;
			Set<String> set = map.keySet();
			Iterator<String> it = set.iterator();
			int customerCnt = 1;

			// NavigableMap<String, List<EntranceVO>>

			while (it.hasNext()) {

				String tempTimeBand = it.next();
				List<EntranceVO> entranceList = map.get(tempTimeBand);
				Iterator<EntranceVO> entIt = entranceList.iterator();
				EntranceVO entranceVO = null;
				log.info("---------------- entranceList : " + entranceList);

				customerEntranceVO = new CustomerEntranceVO();
				// String methodName = "setVisit"+customerCnt;
				customerEntranceVO.setEntranceDate(entranceTime);
				customerEntranceVO.setEntranceTimeBand(tempTimeBand);

				// 한줄(개별 시간대)
				// ex) "10:30 ~
				// 11:30":[{"entranceNum":3,"books":["9788947544085","9791190032209","9791189166960"]...
				for (customerCnt = 1; customerCnt <= 17; customerCnt++) {

					String tempBooks = "";

					if (entIt.hasNext()) {
						entranceVO = entIt.next();

						if (entranceVO.getBooks() != null) {
							tempBooks = entranceVO.getBooks().toString();
						} else {
							tempBooks = "[]";
						}

					} else {
						entranceVO = new EntranceVO();
						tempBooks = "[]";
					}

					try {
						String methodName = "setVisit" + customerCnt;
						callSetMethod(customerEntranceVO, methodName, tempBooks);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
							| NoSuchMethodException | SecurityException e) {
						log.error("------------ reflection error");
						e.printStackTrace();
					}

				} // for

				log.info("******** 저장 전(customerEntranceVO) : " + customerEntranceVO);

				// 방문 현황 데이터베이스 저장
				entranceDAO.insertEntrance(customerEntranceVO);
				log.info("///////////////////customerEntranceVO : " + customerEntranceVO);

			} // while-end

		} // 방문 현황이 없을 때 : 현재 시간대 이전 방문객 생성 끝
		else if (customerEntrances.size() == 0) { // 요청한 날짜의 내방 현황이 DB에 없을 때
			
			log.info("---------------------- 요청한 날짜의 내방 현황이 DB에 없을 때 --------------------------");
			
			// 요청 날짜의 시간대까지 현황 생성(DB에 삽입)
			int order = getOrderByTimeBand(timeBand);

			/*
			 * 13:00:00 입력시 10:30 ~ 12:30까지의 두시간대의 영역(order-1)까지만 생성 21:30:00 마지막 시간대면 180명
			 */
			//
			log.info("timeBand:" + timeBand);
			log.info("order:" + order);
			totalCnt = order == 12 ? 180 : 17 * (order - 1);
			log.info("///////////////// 2 : " + totalCnt);

			String timeLine = "";// 시간대 변수 ex) 10:30 ~ 11:30

			// 반복
			for (int i = 0; i <= totalCnt; i++) {

				int rand = 0;

				if (timeCnt == 2 || (timeCnt >= 8 && timeCnt <= 10)) { // 12:30~13:30, 18:30~21:30 -> 내방 인원 피크 타임

					rand = (int) (Math.random() * 6);
					int weight = (int) (Math.random() * 5);
					rand = (i + weight) % ((int) (Math.random() * 20) + 1) == 0 ? 0 : rand;
					rand = rand >= 5 ? 5 : rand; // 초과 방지

				} else {

					rand = (int) (Math.random() * 4);

					rand = (i + 1) % ((int) (Math.random() * 10) + 1) == 0 ? 0 : rand;
					rand = rand >= 3 ? 3 : rand; // 초과 방지
				}

				if (rand > 5) {
					cnt++;
				}

				if (i < totalCnt - 1) {
					// 시간대 입력
					timeLine = String.format("%d:%d ~ %d:%d", startTime + timeCnt, 30, startTime + timeCnt + 1, 30);

					if ((i % 17 == 0 && i > 0) || i == 179) {
						timeCnt++;
					}
				}

				if (i <= 180) {
					tempEntranceVO = new EntranceVO();
					tempEntranceVO.setEntranceNum(rand);
					tempEntranceVO.setBooks(this.chooseBooks(rand));
					tempEntranceList.add(tempEntranceVO);

					map.put(timeLine, tempEntranceList);

					if (i % 17 == 0) { // 시간대별 인원삽입 중복 방지
						tempEntranceList = new ArrayList<EntranceVO>(); // 초기화
					}

				}

				noBuyCnt = rand == 0 ? noBuyCnt + 1 : noBuyCnt; // 미구매 횟수

			} // for

			log.info("/////////////// map size : " + map.size());

			// DB에 내방 인원 현황 저장
			CustomerEntranceVO customerEntranceVO;
			Set<String> set = map.keySet();
			Iterator<String> it = set.iterator();
			int customerCnt = 1;

			int insertCnt = 1; // 레코드 삽입 카운터

			while (it.hasNext()) {

				String tempTimeBand = it.next();
				List<EntranceVO> entranceList = map.get(tempTimeBand);
				Iterator<EntranceVO> entIt = entranceList.iterator();
				EntranceVO entranceVO = null;
				log.info("---------------- entranceList : " + entranceList);

				customerEntranceVO = new CustomerEntranceVO();

				// 방문 현황 DB 중복 여부 점검
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				entranceTime = dateFormat.parse(dateFormat.format(entranceTime).substring(0, 11) + "22:00:00");

				customerEntranceVO.setEntranceDate(entranceTime);
				customerEntranceVO.setEntranceTimeBand(tempTimeBand);

				// 한줄(개별 시간대)
				// ex) "10:30 ~
				// 11:30":[{"entranceNum":3,"books":["9788947544085","9791190032209","9791189166960"]...
				for (customerCnt = 1; customerCnt <= 17; customerCnt++) {

					String tempBooks = "";

					if (entIt.hasNext()) {
						entranceVO = entIt.next();

						if (entranceVO.getBooks() != null) {
							tempBooks = entranceVO.getBooks().toString();
						} else {
							tempBooks = "[]";
						}

					} else {
						entranceVO = new EntranceVO();
						tempBooks = "[]";
					}

					try {
						String methodName = "setVisit" + customerCnt;
						callSetMethod(customerEntranceVO, methodName, tempBooks);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
							| NoSuchMethodException | SecurityException e) {
						log.error("------------ reflection error");
						e.printStackTrace();
					}

				} // for

				log.info("******** 저장 전(customerEntranceVO) : " + customerEntranceVO);

				timeBand = getTimeBandByOrder(insertCnt);

				log.info("order : " + order);
				log.info("insertCnt : " + insertCnt);
				log.info("entranceTime : " + entranceTime);
				log.info("timeBand : " + timeBand);

				log.info("--------------중복성:"
						+ (entranceDAO.getEntrancesByTimeBand(entranceTime, timeBand).size() >= 1 ? "중복" : "미중복"));

				if (entranceDAO.getEntrancesByTimeBand(entranceTime, timeBand).size() > 0) {
					log.info("---------------중복:");
				} else {
					log.info("-----------------미중복:");
					// 방문 현황 데이터베이스 저장
					entranceDAO.insertEntrance(customerEntranceVO);
				}

				if (insertCnt <= order) {
					insertCnt++;
				}

				// log.info("///////////////////customerEntranceVO : " + customerEntranceVO);

			} // while-end

			log.info("/////////////// map size : " + map.size());

		} else { // 방문자 현황이 있을 때
			// 금일 이전 날짜일 경우 -> 이미 생성(저장) 되어 있어야 함 (더미 데이터)
			// =>2020-05-01부터 금일 이전까지는 더미 데이터 미리 생성

			log.info("********* 방문 현황 있을 때 : ");
			// 추가
			// 기존 방문자 현황 조회
			// 시간 입력시 시간대 검색/출력 ex) 2020-07-16 12:15:00 -> 11:30 ~ 12:30
			// String timeBand = getTimeBand(entranceTime);
			// NavigableMap<String, List<EntranceVO>>
			// 주의) 데이터베이스 테이블에 시간 저장 시 22:00:00로 모두 고정되어 있으므로 22시로 변경
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			entranceTime = dateFormat.parse(dateFormat.format(entranceTime).substring(0, 11) + "22:00:00");

			List<CustomerEntranceVO> customerEntranceList = entranceDAO.getEntrancesTilTimeBand(entranceTime, timeBand);

			EntranceVO entranceVO;
			CustomerEntranceVO customerEntranceVO;

			for (int i = 0; i < customerEntranceList.size(); i++) {

				customerEntranceVO = customerEntranceList.get(i);
				timeBand = customerEntranceVO.getEntranceTimeBand();
				tempEntranceList = new ArrayList<>();

				for (int j = 0; j < 17; j++) { // visit1~visit17

					String methodName = "getVisit" + (j + 1);
					String books = this.callGetMethod(customerEntranceVO, methodName);

					int bookNum = 0;
					String[] booksArr = null;
					String temp = books.replaceAll("\\[", "").replaceAll("\\]", "");
					List<String> booksList = new ArrayList<>();

					if (temp.contentEquals("")) {
						bookNum = 0;
					} else if (!books.contains(",")) {
						booksArr = new String[1];
						booksArr[0] = temp;
						bookNum = 1;
						booksList.add(temp);
					} else {
						System.out.println(temp);
						booksArr = temp.split(",");
						bookNum = booksArr.length;
						booksList = Arrays.asList(booksArr);
					}

					entranceVO = new EntranceVO();
					log.info("길이 : " + bookNum);
					entranceVO.setEntranceNum(bookNum);

					entranceVO.setBooks(booksList);

					tempEntranceList.add(entranceVO);

				} // for2-end

				map.put(timeBand, tempEntranceList);
			} // for1-end

			log.info("/////////////// map size : " + map.size());

		} // 방문자 현황이 있을 때 끝
		log.info("-------------end");

		return map;
	}

	// 방문자 누적 현황 있을 때
	@Override
	public void makeAccrueEntrance(Date entranceTime) throws ParseException {
		log.info("-------------service makeAccrueEntrance");
		NavigableMap<String, List<EntranceVO>> map = new TreeMap<>();
		List<EntranceVO> tempEntranceList = new ArrayList<EntranceVO>();
		EntranceVO tempEntranceVO = null;

		// 시간 입력시 시간대 검색/출력 ex) 2020-07-16 12:15:00 -> 11:30 ~ 12:30
		String timeBand = getTimeBand(entranceTime);

		// 1~5 사이의 난수 발생
		int cnt = 0; // 초과횟수(1~5사이의 난수 발생 초과 횟수)
		int noBuyCnt = 0; // 미구매 시 횟수
		int totalCnt = 180; // 총 반복 횟수(고객 방문 횟수)
		int startTime = 10; // 오전 10시
		int timeCnt = 0; // 시간 카운터

		// 1. 현재 시간대 이전 방문객만 출력
		// 2. DB에 저장된 방문객 우선 적용
		// 3. DB에서 지정날짜 현재 시간대 이전 현황 조회
		// 3-1. 현황이 있으면 우선 조회/적용
		// 3-2. 현황이 없으면 임의 생성(난수발생)
		List<CustomerEntranceVO> customerEntrances = entranceDAO.getEntrances();
		log.info("customerEntrances : " + customerEntrances.size());
		log.info("///////////////// 1 ///////");

		// 방문 현황이 없을 때 : 현재 시간대 이전 방문객 생성
		// 시간대별 -> (17명/시간대)*n
		int order = getOrderByTimeBand(timeBand);
		/*
		 * 13:00:00 입력시 10:30 ~ 12:30까지의 두시간대의 영역(order-1)까지만 생성 21:30:00 마지막 시간대면 180명
		 */
		//
		log.info("timeBand:" + timeBand);
		log.info("order:" + order);
		totalCnt = order == 12 ? 180 : 17 * (order - 1);
		log.info("///////////////// 2 : " + totalCnt);

		String timeLine = "";// 시간대 변수 ex) 10:30 ~ 11:30

		// 관련근거 : 2018 출판산업 실태조사 p.7 고객현황 > 방문객 및 구매율
		// http://portal.kocca.kr/portal/bbs/view/B0000204/1940026.do?menuNo=200247&categorys=4&subcate=58&cateCode=0#
		// 방문객 중 실제로 도서를 구입하는 비율 63.6%

		for (int i = 0; i <= totalCnt; i++) {

			int rand = 0;

			if (timeCnt == 2 || (timeCnt >= 8 && timeCnt <= 10)) { // 12:30~13:30, 18:30~21:30 -> 내방 인원 피크 타임

				rand = (int) (Math.random() * 6);

				int weight = (int) (Math.random() * 5);
				rand = (i + weight) % ((int) (Math.random() * 20) + 1) == 0 ? 0 : rand;
				rand = rand >= 5 ? 5 : rand; // 초과 방지

			} else {

				rand = (int) (Math.random() * 4);

				rand = (i + 1) % ((int) (Math.random() * 10) + 1) == 0 ? 0 : rand;
				rand = rand >= 3 ? 3 : rand; // 초과 방지
			}

			if (rand > 5) {
				cnt++;
			}

			if (i < totalCnt - 1) {
				// 시간대 입력
				timeLine = String.format("%d:%d ~ %d:%d", startTime + timeCnt, 30, startTime + timeCnt + 1, 30);

				if ((i % 17 == 0 && i > 0) || i == 179) {
					timeCnt++;
				}
			}

			if (i <= 180) {
				tempEntranceVO = new EntranceVO();
				tempEntranceVO.setEntranceNum(rand);
				tempEntranceVO.setBooks(this.chooseBooks(rand));
				tempEntranceList.add(tempEntranceVO);

				map.put(timeLine, tempEntranceList);

				if (i % 17 == 0) { // 시간대별 인원삽입 중복 방지
					tempEntranceList = new ArrayList<EntranceVO>(); // 초기화
				}

			}

			noBuyCnt = rand == 0 ? noBuyCnt + 1 : noBuyCnt; // 미구매 횟수

		} // for

		log.info("/////////////// map size : " + map.size());

		// DB에 내방 인원 현황 저장
		CustomerEntranceVO customerEntranceVO;
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		int customerCnt = 1;

		// NavigableMap<String, List<EntranceVO>>

		while (it.hasNext()) {

			String tempTimeBand = it.next();
			List<EntranceVO> entranceList = map.get(tempTimeBand);
			Iterator<EntranceVO> entIt = entranceList.iterator();
			EntranceVO entranceVO = null;
			log.info("---------------- entranceList : " + entranceList);

			customerEntranceVO = new CustomerEntranceVO();
			// String methodName = "setVisit"+customerCnt;
			customerEntranceVO.setEntranceDate(entranceTime);
			customerEntranceVO.setEntranceTimeBand(tempTimeBand);

			// 한줄(개별 시간대)
			// ex) "10:30 ~
			// 11:30":[{"entranceNum":3,"books":["9788947544085","9791190032209","9791189166960"]...
			for (customerCnt = 1; customerCnt <= 17; customerCnt++) {

				String tempBooks = "";

				if (entIt.hasNext()) {
					entranceVO = entIt.next();

					log.info("@@@@@@@@@@@@@@ entranceVO : " + entranceVO);

					if (entranceVO.getBooks() != null) {
						tempBooks = entranceVO.getBooks().toString();
					} else {
						tempBooks = "[]";
					}

				} else {
					entranceVO = new EntranceVO();
					log.info("@@@@@@@@@@@@@@ entranceVO : " + entranceVO);

					tempBooks = "[]";
				}

				try {
					String methodName = "setVisit" + customerCnt;
					callSetMethod(customerEntranceVO, methodName, tempBooks);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| NoSuchMethodException | SecurityException e) {
					log.error("------------ reflection error");
					e.printStackTrace();
				}

			} // for

			log.info("******** 저장 전(customerEntranceVO) : " + customerEntranceVO);

			// 방문 현황 데이터베이스 저장
			entranceDAO.insertEntrance(customerEntranceVO);
			log.info("///////////////////customerEntranceVO : " + customerEntranceVO);

		} // while-end
	}

	/**
	 * 메서드명으로 set 메서드(setter) 호출(reflection 활용) ex) setVisit1
	 * 
	 * @param customerVO 내방 고객 VO
	 * @param methodName 호출할 set 메서드명
	 * @param books      구매한 도서
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public void callSetMethod(CustomerEntranceVO customerVO, String methodName, String books)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
			SecurityException {

		Class<String>[] arg = new Class[1];
		arg[0] = String.class;

		Method method = customerVO.getClass().getDeclaredMethod(methodName, arg);
		method.invoke(customerVO, books);
	}
	
	@Override
	public String callGetMethod(CustomerEntranceVO customerVO, String methodName) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		Method method = customerVO.getClass().getDeclaredMethod(methodName);
		return method.invoke(customerVO).toString();
	}

	@Override
	public String getTimeBand(Date entranceTime) throws ParseException {
		log.debug("시간대 변환");

		long time = entranceTime.getTime();
		// 금일 날짜 년-월-일
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		String today = dt.format(entranceTime);

		log.debug("금일 날짜 : " + today);

		// 1. 현재시간(15:55)
		// 2. 현재 시간 기준으로 인근 시간대 확보 (14:30 ~ 15:30 또는 15:30 ~ 16:30)
		// 3. 현재 시(hour) 우선 확보
		// Date todayHour = new Date();
		SimpleDateFormat dh = new SimpleDateFormat("HH");
		int hour = Integer.parseInt(dh.format(entranceTime));

		log.debug("현재 시(hour) : " + hour);

		long entranceTimeLong = entranceTime.getTime();
		// 2020-07-16 15:30:00(String) -> Date -> long
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date date1 = dateFormat.parse(today + " " + (hour - 1) + ":30:00");
		String timeBand1 = (hour - 1) + ":30";
		long time1 = date1.getTime();

		Date date2 = dateFormat.parse(today + " " + hour + ":30:00");
		String timeBand2 = hour + ":30";
		long time2 = date2.getTime();

		String timeBand3 = (hour + 1) + ":30";

		String result = "";

		if (entranceTimeLong >= time1 && entranceTimeLong < time2) {
			result = timeBand1 + " ~ " + timeBand2;
		} else {
			result = timeBand2 + " ~ " + timeBand3;
		}

		return result;
	}

	@Override
	public List<BookStockForScreenVO> getAllBookStockForScreen() {
		log.debug("service getAllBookStockForScreen");
		return bookStockDAO.getAllBookStockForScreen();
	}

	/**
	 * 시간대 별 순서 출력
	 * 
	 * @param timeBand 시간대
	 * @return 순서
	 */
	public int getOrderByTimeBand(String timeBand) {
		Map<String, Integer> map = new TreeMap<>();
		map.put("10:30 ~ 11:30", 1);
		map.put("11:30 ~ 12:30", 2);
		map.put("12:30 ~ 13:30", 3);
		map.put("13:30 ~ 14:30", 4);
		map.put("14:30 ~ 15:30", 5);
		map.put("15:30 ~ 16:30", 6);
		map.put("16:30 ~ 17:30", 7);
		map.put("17:30 ~ 18:30", 8);
		map.put("18:30 ~ 19:30", 9);
		map.put("19:30 ~ 20:30", 10);
		map.put("20:30 ~ 21:30", 11);
		map.put("21:30 ~ 22:30", 12);

		return map.get(timeBand);
	}

	/**
	 * 순서별 시간대 출력
	 * 
	 * @param order 순서
	 * @return 시간대
	 */
	@Override
	public String getTimeBandByOrder(int order) {
		Map<Integer, String> map = new TreeMap<>();
		map.put(1, "10:30 ~ 11:30");
		map.put(2, "11:30 ~ 12:30");
		map.put(3, "12:30 ~ 13:30");
		map.put(4, "13:30 ~ 14:30");
		map.put(5, "14:30 ~ 15:30");
		map.put(6, "15:30 ~ 16:30");
		map.put(7, "16:30 ~ 17:30");
		map.put(8, "17:30 ~ 18:30");
		map.put(9, "18:30 ~ 19:30");
		map.put(10, "19:30 ~ 20:30");
		map.put(11, "20:30 ~ 21:30");
		map.put(12, "21:30 ~ 22:30");

		return map.get(order);
	}

	@Override
	public void updateBookStocks(NavigableMap<String, List<EntranceVO>> entrances) {
		
		// 판매 도서 리스트(중복허용)
		List<String> bookStockList = new ArrayList<>();
		Collection<List<EntranceVO>> entranceList = entrances.values();
		Iterator<List<EntranceVO>> it = entranceList.iterator();
		List<EntranceVO> list = null;

		while (it.hasNext()) {
			list = it.next();

			for (int i = 0; i < list.size(); i++) {
				// log.info("----:"+list.get(i));
				bookStockList.addAll(list.get(i).getBooks());
			} //
				// log.info("---------------------------");
		} // while-end

		for (String book : bookStockList) {
			// log.info("------book:"+book);
		}

		// 도서 isbn13, 판매수량 map 구조로 치환
		Set<String> bookSet = new TreeSet<>(bookStockList);
		// log.info("------책 중복허용:"+bookStockList.size());
		// log.info("------책 종류:"+bookSet.size());

		Map<String, Integer> map = new TreeMap<>();

		for (String book : bookSet) {
			int bookcnt = (int) bookStockList.stream().filter(x -> x.equals(book)).count();
			map.put(book, bookcnt);
		}
		
		log.info("------------ 맵 확인 -------------------");

		map.forEach((k, v) -> {
			log.info(k + "=" + v);
		});

		// 기존 도서 재고량과 비교하여 재고량 조정/갱신 필요 
		Set<String> set = map.keySet();
		Iterator<String> stockIt = set.iterator();
		
		while (stockIt.hasNext()) {
			String isbn13 = stockIt.next();
			
			int defaultStock = 0;
			log.info("--------- isbn13 : "+isbn13);
			// 패치 : NullPointerException
			try {
				defaultStock = bookStockDAO.getStockByIsbn10(isbn13);
			} catch (NullPointerException e) {
				defaultStock = 0;
			}
			
			int newStock = map.get(isbn13);
			int updateStock = defaultStock - newStock <= 0 ? 0 : defaultStock - newStock;
			
			log.info("---- updateStock : " +updateStock);
			bookStockDAO.updateStock(isbn13, updateStock);
		} // while-end
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean updateAllStock() {
		log.info("------재고 보충: updateAllStock");
		boolean flag = false;
		try {
			bookStockDAO.updateAllStock(); 
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	@Override
	public Date parseEntranceDate(Date date) throws ParseException {
		
		log.info("parseEntranceDate : ");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = dateFormat.format(date);
		Date date1 = dateFormat.parse(time); // Date

		String entranceTime = dateFormat.format(date1);
		entranceTime = entranceTime.substring(0, 11) + "22:00:00";
		date1 = dateFormat.parse(entranceTime);
		
		return date1;
	}

	@Transactional(readOnly = true)
	@Override
	public String getStockUpdateYN(String stockDate, String timeBand) {
		return bookStockDAO.getStockUpdateYN(stockDate, timeBand);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public void insertStockUpdateYN(String stockDate, String timeBand, String updateYn) {
		bookStockDAO.insertStockUpdateYN(stockDate, timeBand, updateYn);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public void updateStockUpdateYN(String stockDate, String timeBand, String updateYn) {
		bookStockDAO.updateStockUpdateYN(stockDate, timeBand, updateYn);
	}

}// class
