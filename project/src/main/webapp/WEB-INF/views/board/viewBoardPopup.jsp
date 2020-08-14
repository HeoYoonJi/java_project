<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" /> 
<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="UTF-8">

<spring:url value="/css/custom/main.css" var="custom_css" />
<spring:url value="/bootstrap/4.4.1/css/bootstrap.min.css" var="bootstrap_css" />
<spring:url value="/js/jquery/jquery-3.5.1.min.js" var="jquery" />
<spring:url value="/bootstrap/4.4.1/js/bootstrap.min.js" var="bootstrap_js" />

<!-- bootstrap CSS : 4.4.1 -->
<link rel="stylesheet" href="${bootstrap_css}">

<!-- jQuery : 3.5.1 -->
<script src="${jquery}" charset="UTF-8"></script>

<!-- bootstrap JS : 4.4.1 -->
<script src="${bootstrap_js}" charset="UTF-8"></script>


<script>
//외장화에 따른 광역 변수화 : 절대 경로, page
//context Path
//javascript 외장화시 절대 경로 문제 발생 => 미리 js 변수로 치환하여 설정하고 진입 
const contextPath= "${rootPath}";
</script>

<!-- javascript 외장화  -->
<script src="${rootPath}/custom/board.js" charset="UTF-8"></script>

<style>
div#popup_view_board {
	width:100%;
	margin:30px auto;
	text-align:center;
}

h3 {
	text-align:center;
	margin:20px 0;
}
table#view_board_tbl {
	width: 600px;
	margin:auto;
}

/* 글내용 쓰기 필드 크기 조절 방지 */
textarea {
	resize: none;
}
</style>
</head>

<body>

	<!-- 팝업 방식 게시글 보기 -->
	<!-- 게시글정보 보기(팝업) 시작 -->
	<%@ include file="viewBoardPopup.jspf" %>
	<!-- 게시글정보 보기(팝업) 끝 -->
	
	<div id="popup_view_board">
		<a href="#?page=1"
		   id="boardNum_${board.boardNum}"
		   data-toggle="modal"
		   data-target="#viewModal">
			${board.boardSubject} (팝업 방식)
		</a><br>
	</div>

	<!-- -------------------------------------------- -->

	<!-- 페이지 전환 방식 게시글 보기 -->
	<h3>게시글 보기</h3>
	
	<!-- bootstrap table 적용 -->
	<table id="view_board_tbl" class="table table-striped">

		<tr class="form-group">
			<td class="col-xs-2">
				<label for="boardName" class="control-label"> 
					<span style="color: red">*</span>글쓴이
				</label>
			</td>
			<td class="col-xs-10">
				${board.boardName}
			</td>
		</tr>

		<tr>
			<td>
				<label for="boardSubject" class="control-label">
					<span style="color: red">*</span>제 목
				</label>
			</td>
			<td>
				${board.boardSubject}
			</td>
		</tr>

		<tr>
			<td>
				<label for="boardContent" class="control-label">
					<span style="color: red">*</span>내 용
				</label>
			</td>
			<td>
				<textarea id="boardContent"
						  name="boardContent"
						  cols="40" 
						  rows="15" 
						  class="form-control bg-light"
						  readonly>${board.boardContent}</textarea>
		  	</td>
		</tr>

		<tr>
			<td>
				<label for="boardFile" class="control-label">첨부 파일</label>
			</td>
			<td>				
				<a href="${rootPath}/upload/${board.boardNum}/${board.boardFile}">
					${board.boardFile}
				</a>
		    </td>
		</tr>

	</table>

</body>