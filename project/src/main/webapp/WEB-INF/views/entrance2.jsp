<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>시간대 별 방문 현황</title>
<!-- bootstrap css -->
<link rel="stylesheet" type="text/css" href="${contextPath}/webjars/bootstrap/4.5.0/css/bootstrap.min.css">

<!-- jQuery -->
<script src="${contextPath}/webjars/jquery/3.5.1/dist/jquery.min.js"></script>
<!-- popper js -->
<script src="${contextPath}/webjars/popper.js/1.16.0/popper.min.js"></script>
<!-- bootstrap js -->
<script src="${contextPath}/webjars/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<style>
table#entrance_tbl {
	width:370px;
	
}
</style>
</head>
<body>
	<table id="entrance_tbl" class="table table-bordered">
		<thead>
			<th>시간대</th>
			<th colspan="11">인원현황</th>
		</thead>
		<tbody>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tbody>
		<tfoot>
			<td colspan="12">현황</td>
		</tfoot>
	</table>
</body>
</html>