<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>login</title>
<style>
.error 
{
	padding: 10px;
	color: red;
}

.msg 
{
	padding:10px;
	color:red;
}

#login-box 
{
	width: 270px;
	padding: 30px 0 30px 30px;
	margin: 300px auto;
	background: #fff;
	border: 3px solid #68007A;
	color: #68007A;
}

#login, #joinbtn {
	background-color: #68007A;
	color: #fff;
	width: 65px;
	height: 25px;
}
			
td 
{ 
	height: 30px; 
}			
</style>

<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>

<script>
	$(document).ready(function() {
		
		 // 필드 공백 제거
		 $("#username").val().replace(/\s/g, "");
		 $("#password").val().replace(/\s/g, "");
		 
		 $("#login").click(function() {
			
			// 공백 여부 점검
			if ($.trim($("#username").val()) == "" || 
				$.trim($("#password").val()) == "")   {
				
				alert("공백이 입력되었습니다. 다시 입력하십시오.");
				$("#username").val("");
				$("#password").val("");
				
			} else {
				
				/*
				$.ajax({
		    		url : "${pageContext.request.contextPath}/login/idCheck",
		    	    type: "get",
		    	    dataType: "text",
		        	data : {
		        		username : $("#username").val()
		        	},
		        	success : function(data) {
		        		
		        		if (data.trim() == "true") {
			           		 alert("아이디가 존재합니다.");
			       			 document.loginForm.submit();
			           	} else 
			       			 alert("아이디가 존재하지 않습니다."); 
		        			 $("#username").focus();
			        	}
		        	
		    	}); // $.ajax
		    	*/
		    	
			} // if
	    	
	    }) // login
	    
	}) //
</script>
</head>
<body>
	
	<div id="login-box">

		<h3>Login</h3>
		
	    <!-- 인증(auth) 에러 메시징 : 예외처리 메시지가 직접 인쇄됨-->
		<c:if test="${error eq 'true'}">
			<div class="msg">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</div>
		</c:if> 

		<form id="loginForm" 
			  name="loginForm" 
			  action="<c:url value='/login?${_csrf.parameterName}=${_csrf.token}' />"
			  method="POST">
			  <!-- action="<c:url value='j_spring_security_check' />" -->
	 
			<table>
				<tr>
					<td>ID : </td>
					<td><input type="text" 
							   id = "username"	
							   name="username" />
					</td>
				</tr>
				<tr>
					<td>PW : </td>
					<td><input type="password" 
							   id="password"
							   name="password" />
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						
						<input id="login"
							   name="login" 
							   type="submit"
							   value="로그인" />
							   
					    &nbsp;
						<input id="joinbtn"
					    	   name="joinbtn" 	
					    	   type="button"
							   value="회원가입"
							   onclick="location.href='${pageContext.request.contextPath}/member/member_join.do'" />	   
					</td>
				</tr>
			</table>
			
			<!-- csrf -->
			<%-- <input type="hidden"                        
				   name="${_csrf.parameterName}"
				   value="${_csrf.token}"/>  --%>
	 
		</form>
	
 	</div> <!-- login box -->	
</body>
</html>