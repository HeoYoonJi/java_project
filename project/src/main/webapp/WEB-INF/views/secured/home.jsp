<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Secured Home</title>
</head>
<body>
<h3>Secured Home !</h3>
<h4>${username},  ${message}</h4>

	<c:if test="${not empty pageContext.request.userPrincipal.name}">
		${pageContext.request.userPrincipal.name} 님 환영합니다.<br>
		<input type="button" 
			   value="로그아웃"
			   onclick="location.href='${pageContext.request.contextPath}/logoutProc'" />	
	</c:if>

</body>
</html>