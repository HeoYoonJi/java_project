<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="UTF-8">
<title>도서 신청</title>

<spring:url value="/css/custom/main.css" var="custom_css" />
<spring:url value="/bootstrap/4.4.1/css/bootstrap.min.css" var="bootstrap_css" />
<spring:url value="/bootstrap/4.4.1/js/bootstrap.min.js" var="bootstrap_js" />
<spring:url value="/js/jquery/jquery-3.5.1.min.js" var="jquery" />
<spring:url value="/angularjs/1.7.9/angular.min.js" var="angularjs" />

<!-- bootstrap CSS : 4.4.1 -->
<link rel="stylesheet" href="${bootstrap_css}">

<!-- jQuery : 3.5.1 -->
<script src="${jquery}" charset="UTF-8"></script>

<!-- bootstrap JS : 4.2.1 -->
<script src="${bootstrap_js}" charset="UTF-8"></script>

<!-- AngularJS : 1.7.8 -->
<script src="${angularjs}" charset="UTF-8"></script>

<!-- custom(자체) CSS -->
<link rel="stylesheet" href="${custom_css}">

<script>
// 외장화에 따른 광역 변수화 : 절대 경로, page
// context Path
// javascript 외장화시 절대 경로 문제 발생 => 미리 js 변수로 치환하여 설정하고 진입

const contextPath= "${pageContext.request.contextPath}";
var page = "${pageVO.page}";
</script>

<!-- javascript 외장화  -->
<script src="${pageContext.request.contextPath}/js/custom/board.js" charset="UTF-8"></script>

</head>

<body ng-app="boardFormApp" ng-controller="boardFormController">
	
	<!-- 메인페이지 돌아가기 버튼 -->
	<div><a href="${contextPath}/project/stand.do" id="home_btn">Home</a></div>

	<!-- 페이지 관련 변수들 -->
	<div id="form_dashboard">
		<table id="flag_tbl" class="col-10 table table-dark table-striped table-bordered">
			<tr>
				<th>총 게시글 수 : </th><td>${pageVO.listCount}</td>
				<th>현재 페이지 : </th><td>${pageVO.page}</td>
				<th>총 페이지 : </th><td>${pageVO.maxPage}</td>
				<th>시작 페이지 : </th><td>${pageVO.startPage}</td>
				<th>끝 페이지 : </th><td>${pageVO.endPage}</td>
			</tr>
		</table>
	</div>

	<!-- 게시글정보 쓰기 시작 -->
	<%@ include file="writeBoardPopup.jspf" %>
	<!-- 게시글정보 쓰기 끝 -->

	<!-- 게시글정보 보기(팝업) 시작 -->
	<%@ include file="viewBoardPopup.jspf" %>
	<!-- 게시글정보 보기(팝업) 끝 -->

	<!-- 게시글정보 수정(팝업) 시작 -->
	<%@ include file="updateBoardPopup.jspf" %>
	<!-- 게시글정보 수정(팝업) 끝 -->

	<!-- 게시글 답글(댓글) 쓰기(팝업) 시작 -->
	<%@ include file="replyBoardPopup.jspf" %>
	<!-- 게시글 답글(댓글) 쓰기(팝업) 끝 -->

	<!-- 게시글 삭제(팝업) 시작 -->
	<%@ include file="deleteBoardPopup.jspf" %>
	<!-- 게시글 삭제(팝업) 끝 -->

	<br>
	<h3 align="center">도서 신청</h3>
    <br>

	<!-- 게시판 리스트 시작 -->
	<div id="listForm" style="width: 800px; margin: auto">

		<c:if test="${not empty articleList && pageVO.listCount > 0}">

			<!-- 게시글 부분 시작 -->
			<table id="board_tbl" class="table table-striped table-hover">

				<tr id="tr_top">
					<td>번호</td>
					<td>글번호</td>
					<td>제목</td>
					<td>작성자</td>
					<td>날짜</td>
					<td>조회수</td>
				</tr>

				<c:forEach var="article" items="${articleList}" varStatus="st">

					<tr>
						<td>${st.count + (pageVO.page-1)*10}</td>
						<td>${article.boardNum}</td>

						<td>
							<c:choose>
								<c:when test="${article.boardReLev != 0}">
									<c:forEach var="a" begin="0" end="${article.boardReLev * 2}"
										step="1" varStatus="st">
										&nbsp;
									</c:forEach>
									 	▶
								</c:when>
								<c:otherwise>
									▶
								</c:otherwise>
							</c:choose>

							<%-- ${article.boardSubject} --%>
							<%-- <a href="#?page=1"
							   id="boardNum_${article.boardNum}"
							   data-toggle="modal"
							   data-target="#viewModal"
							   onClick="viewBoard(${article.boardNum})">
								${article.boardSubject}
							</a>	 --%>

							<a href="#?page=1"
							   id="boardNum_${article.boardNum}"
							   data-toggle="modal"
							   data-target="#viewModal">
								${article.boardSubject}
							</a>

						</td>

						<td>${article.boardName}</td>
						<td>
							<fmt:formatDate value="${article.boardDate}" pattern="yyyy년  M월 d일  HH:mm:ss" />
						</td>
						<td id="boardReadCount_${article.boardNum}">
							${article.boardReadCount}
						</td>
					</tr>

				</c:forEach>

			</table>
			<!-- 게시글 부분 끝 -->

			<!-- 페이징 시작 -->
			<!-- 페이징 모듈 외장화 -->

			<c:if test="${search_YN == 'search'}">
				<%@ include file="paging_search.jspf" %>
			</c:if>
			<c:if test="${search_YN != 'search'}">
				<%@ include file="paging.jspf" %>
			</c:if>
			<!-- 페이징 끝 -->

		</c:if>

		<!-- 등록글 없을 경우 -->
		<c:if test="${empty articleList || pageVO.listCount==0}">

			<div id="boardEmptyArea">등록된 글이 없습니다.</div>

		</c:if>

		<!-- 게시글 메뉴  -->
		<div id="board_foot_menu" class="row mt-5">

			<!-- 메뉴 버튼 시작  -->

				<!-- 글쓰기 버튼 시작  -->
				<div class="col-3">

					<div class="row">

						<div class="col-6 pr-2">
							<button type="button"
									id="writeContentBtn"
									class="btn btn-info"
									data-toggle="modal"
									data-target="#writeModal"
									ng-click="initWriteForm()">
									글쓰기
							</button>
						</div>

						<div class="col-6 pl-2">
							<button type="button"
								id="list_btn"
								class="btn btn-info">
								목록
							</button>
						</div>
					</div>

				</div>
				<!-- 글쓰기 버튼 끝 -->

				<!-- 검색 시작  -->
				<form method="post"
					  class="col-9"
					  action="${pageContext.request.contextPath}/board/boardListbySearch.do">

					<div class="row">

						<input type="hidden" name="page" value="${pageVO.page}"/>

						<div class="col-2 pr-0">
							<select id="search_kind"
									name="search_kind"
									class="form-control shadow-sm">
								<option>제목</option>
								<option>내용</option>
							</select>
						</div>

						<div class="col-7 ml-0">

							<input type="text"
								   id="search_word"
								   name="search_word"
								   class="form-control shadow-sm"
								   placeholder="검색 ..."
								   required="true"
								   title="검색어를 입력하십시오">

						</div>

						<div class="col-3 pl-0">
							<input type="submit"
								   id="search_board_btn"
								   class="btn btn-info"
								   value="검색">
						</div>

					</div>

				</form>
				<!-- 검색 끝  -->

		    </div>
		    <!-- 메뉴 버튼 끝 -->

		<!-- 게시글 메뉴  끝 -->

	</div>
	<!-- 게시판 리스트 끝 -->

</body>
</html>