<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<!-- 내비게이션바 -->
	<ol id="nav_top">
		<li><a href="#" id="home">Home</a></li>
	
		<!-- 일반 사용자, 관리자 공용 -->		
		<sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
			<li><a href="${contextPath}/logoutProc" id="logout_btn">로그아웃</a></li>
		</sec:authorize>

		<!-- 관리자 전용 -->	
		<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
			<li><a href="#" id="stock_recovery_btn">도서 입고</a></li>
		</sec:authorize>
		
		<li><a href="${contextPath}/board/boardList.do/1" id="book_apply_btn">도서 신청</a></li>
		<li><a href="#" id="books_stock_state_btn">전체 도서 재고 현황</a></li>
		<li><a href="#" id="books_state_btn">전체 도서 목록</a></li>
	</ol>