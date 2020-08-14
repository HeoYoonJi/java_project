<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style>
#join-box 
{
	width: 250px;
	padding:30px;
	margin: 0px auto;
	background: #fff;
	border: 1px solid #333;
	line-height: 30px;
 }
</style>
</head>
<body>

	<div id="join-box">

		<h3>Join</h3>
			
		<form id="join" 
			  action="joinAction" 
			  method="post">
		
			ID : <input type="text" 
						id="username"
						name="username"
						maxlength="20"
						size="23">
				 <br>
			PW : <input type="password" 
						id="password"
						name="password"
						maxlength="20"
						size="23">
				 <br>
			<input type="submit" value="join">&nbsp;
			<input type="reset" value="reset">&nbsp;
			<input type="button" value="login" onclick="location.href='./login'">
			
		</form>
	
	</div>		

</body>
</html>