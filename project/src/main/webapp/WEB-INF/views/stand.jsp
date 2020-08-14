<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="UTF-8">
<title>Stand</title>
<script
	src="https://raw.githubusercontent.com/LeaVerou/prefixfree/gh-pages/prefixfree.min.js"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- 외장 css -->
<link rel="stylesheet" href="${contextPath}/css/custom/stand.css">
<link rel="stylesheet" href="${contextPath}/css/custom/top_nav.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- jqueryui -->
<link rel="stylesheet"
	href="${contextPath}/webjars/jquery-ui/themes/base/jquery-ui.min.css">
<script src="${contextPath}/webjars/jquery-ui/jquery-ui.min.js"></script>

<!-- 외장 자바스크립트 : stand.js -->
<script>
	const contextPath = "${contextPath}";
</script>
<script src="${contextPath}/js/custom/stand.js" charset="UTF-8"></script>

</head>
<body>
	<!-- 개별 도서 현황(팝업) -->
	<div id="one_book_detail_popup">
		<div id="close_btn">
			<button id="popup_close_btn">X</button>
		</div>
		<div id="popup1"></div>
		<div id="popup2"></div>
	</div>
	
	<!-- 내비게이션바 -->
	<%@ include file="./common/top_nav.jsp" %>

	<div id="wrap">
	
		<!-- customer_section & stand_section -->
		<div id="customer_stand_section">
		
			<!-- 검색바 -->
			<div id="search_wrap">
				<select id="select_search_box" name="search_kind">
					<option>제목</option>
					<option>저자</option>
					<option>출판사</option>
				</select>
				<input type="text" id="search_bar" name="search_word">
				<input type="button" id="search_btn">
			</div>
			
			<!-- 내방고객 현황 -->
			<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
			<div id="customer_section">
				<div id="left_entrance_time">
					<div>
						<input type="text" id="entrance_date"
							placeholder="시간대 입력(ex.14:15:00)" name="entrance_date">
					</div>
					<div>
						<select id="entrance_time" name="entrance_time">
							<option value="12:00:00">오후 12시</option>
							<option value="13:00:00">오후 13시</option>
							<option value="14:00:00">오후 14시</option>
							<option value="15:00:00">오후 15시</option>
							<option value="16:00:00">오후 16시</option>
							<option value="17:00:00">오후 17시</option>
							<option value="18:00:00">오후 18시</option>
							<option value="19:00:00">오후 19시</option>
							<option value="20:00:00">오후 20시</option>
							<option value="21:00:00">오후 21시</option>
							<option value="22:00:00">오후 22시</option>
						</select>
					</div>
					<div>
						<a href="#" id="customer_entrances_btn">조회하기</a>
					</div>
				</div>

				<table id="entrance_tbl" class="table table-bordered">
					<thead>
						<tr>
							<th id="entrance_time_title">시간대</th>
							<th colspan="17" id="entrance_man_title">인원현황</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach begin="1" end="11" varStatus="timeRowVs">
							<tr>
								<!--10:30 ~ 11:30-->
								<td id="timeLine_${timeRowVs.count}">
									${9+timeRowVs.index}:30 ~ ${10+timeRowVs.index}:30</td>
								<c:forEach begin="1" end="17" varStatus="vs">
									<td id="man_${timeRowVs.count}_${vs.count}"></td>
								</c:forEach>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			</sec:authorize>

			<!-- 도서 매대 -->
			<div id="stand_section">
				<table width="560" align="center">
					<tr>
						<!-- 1.IT 모바일 -->
						<td colspan="2" align="center">
							<div id="IT_stand">
								<c:forEach begin="1" end="20" varStatus="IT_stand_vs">
									<div class="IT_stand_book_num"
										id="IT_stand_${IT_stand_vs.count}">
										<a href="#" id="it_book_num${IT_stand_vs.count}">
											${IT_stand_vs.count} </a>
									</div>
								</c:forEach>
							</div>
						</td>

						<!-- 2.소설 -->
						<td colspan="2" align="center">
							<div id="novel_stand">
								<c:forEach begin="21" end="40" varStatus="novel_stand_vs">
									<div class="novel_stand_book_num"
										id="novel_stand_${novel_stand_vs.count}">
										<a href="#" id="novel_book_num${novel_stand_vs.current}">
											${novel_stand_vs.current} </a>
									</div>
								</c:forEach>
							</div>

						</td>
					</tr>

					<tr height="150">
						<!-- 3.여행 -->
						<td width="120" align="center">
							<div id="trip_stand">
								<c:forEach begin="41" end="60" varStatus="trip_stand_vs">
									<div class="trip_stand_book_num"
										id="trip_stand_${trip_stand_vs.count}">
										<a href="#" id="trip_book_num${trip_stand_vs.current}">
											${trip_stand_vs.current} </a>
									</div>
								</c:forEach>
							</div>
						</td>

						<td colspan="2"></td>
						<!-- 4.자기계발 -->
						<td width="120" align="center">
							<div id="self_develop_stand">
								<c:forEach begin="61" end="80" varStatus="self_develop_stand_vs">
									<div class="self_develop_stand_book_num"
										id="self_develop_stand_${self_develop_stand_vs.count}">
										<a href="#" id="self_book_num${self_develop_stand_vs.current}">
											${self_develop_stand_vs.current} </a>
									</div>
								</c:forEach>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</div>
		
		<!-- 도서 검색 결과 팝업 -->
		<div id="books_search_section">
			<button id="search_close_btn">X</button>
			<div id="books_search_section_content">
				<%-- <div class="book_search_section" id="book_search_${books_search_section_vs.count}">
					<div id="book_search_num_${books_search_section_vs.count}"
							class="book_cell">${books_search_section_vs.count}</div>
					<div id="book_search_id_${books_search_section_vs.count}" class="book_cell">
						도서 아이디</div>
					<div id="book_search_img_${books_search_section_vs.count}" class="book_cell">
						그림</div>
					<div id="book_search_genre_${books_search_section_vs.count}" class="book_cell">
						도서장르</div>
					<div id="book_search_name_${books_search_section_vs.count}" class="book_cell">
						도서제목</div>
					<div id="book_search_author_${books_search_section_vs.count}" class="book_cell">
						저자, 출판사, 정가</div>
					<div id="book_search_eval_point_${books_search_section_vs.count}" class="book_cell">
						평점</div>
				</div> --%>
			</div>
		</div>

		<!-- 도서 현황 -->
		<div id="books_section">
			<c:forEach begin="1" end="80" varStatus="books_section_vs">
				<div class="book_section" id="book_${books_section_vs.count}">
					<div id="book_num_${books_section_vs.count}" class="book_cell">
						${books_section_vs.count}</div>
					<div id="book_id_${books_section_vs.count}" class="book_cell">
						도서 아이디</div>
					<div id="book_img_${books_section_vs.count}" class="book_cell">
						그림</div>
					<div id="book_genre_${books_section_vs.count}" class="book_cell">
						도서장르</div>
					<div id="book_name_${books_section_vs.count}" class="book_cell">
						도서제목</div>
					<div id="book_author_${books_section_vs.count}" class="book_cell">
						저자, 출판사, 정가</div>
					<div id="book_eval_point_${books_section_vs.count}"
						class="book_cell">평점</div>
				</div>
			</c:forEach>
		</div>

		<!-- 도서 재고 현황 -->
		<div id="books_stock_section">
			<c:forEach begin="1" end="80" varStatus="books_stock_section_vs">
				<div class="book_section" id="stock_book_${books_stock_section_vs.count}">
					<div id="stock_book_num_${books_stock_section_vs.count}"
						class="book_cell">${books_stock_section_vs.count}</div>
					<div id="stock_book_id_${books_stock_section_vs.count}"
						class="book_cell">도서 아이디</div>
					<div id="stock_book_img_${books_stock_section_vs.count}"
						class="book_cell">그림</div>
					<div id="stock_book_genre_${books_stock_section_vs.count}"
						class="book_cell">도서장르</div>
					<div id="stock_book_name_${books_stock_section_vs.count}"
						class="book_cell">도서제목, 저자</div>
					<div id="stock_book_limit_${books_stock_section_vs.count}"
						class="book_cell">재고 한계</div>
					<div id="stock_book_quantity_${books_stock_section_vs.count}"
						class="book_cell">재고 현황</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>