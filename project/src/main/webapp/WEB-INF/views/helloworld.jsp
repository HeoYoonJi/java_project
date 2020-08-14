<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8">
</head>
<body>
userPrincipal.name : ${pageContext.request.userPrincipal.name}<br>
<h3>Hello World!</h3>
<h4>${message}</h4>
</body>
</html>