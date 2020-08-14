<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>시간대 별 방문 현황</title>
<!-- bootstrap css -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<style>
	table#entrance_tbl {
		width:830px;
	}
	
	table#entrance_tbl td {
		height:30px;
	}
</style>
<script>
	$(function(){
		  $.ajax ({
			  
	             url : '${pageContext.request.contextPath}/makeRandomEntrances.do',
	             type : 'get',
	             contentType : "application/json",
	             dataType:'text',
	             success : function(data) {
	               
	                //console.log("data : "+data);
	                var json = JSON.parse(data);
	                
	                //console.log(json['10:30 ~ 11:30']);
	                //console.log("---------------------------------------")
	                //console.log(json['10:30 ~ 11:30'][0]);
	                //console.log(json['10:30 ~ 11:30'][0].entranceNum);
	                /* if (json['10:30 ~ 11:30'][0].books.length > 0){
	                	console.log(json['10:30 ~ 11:30'][0].books[0]);	                	
	                } else {
	                	console.log("책 없음");
	                } */
	                
	                //console.log(Object.keys(json));
	                //시간대 출력
	                var timeLines = Object.keys(json); //시간대
	                for (var i=0; i<timeLines.length; i++){
	                	$("td#timeLine_"+(i+1)).text(timeLines[i])
	                }
	                
	                //시간대별 인원 출력
	                for (var i=0; i<timeLines.length; i++) {
	                	//console.log(timeLines[i]);
	                	console.log(json[timeLines[i]]);
	                	for (var j=0; j<json[timeLines[i]].length; j++) {
	                		$("td#man_"+(i+1)+"_"+(j+1)).text(json[timeLines[i]][j].entranceNum)
	                	}
	                	
	                }
	                               
	            }, // success
	             
	             error : function(xhr, status) {
	                 
	                console.log(xhr+" : "+status); // 에러 코드
	            } //
	 
	        }); // $.ajax
	});
</script>
</head>
<body>
	<table id="entrance_tbl" class="table table-bordered">
		<thead>
			<tr>
				<th width="200">시간대</th>
				<th colspan="17">인원현황</th>
			</tr> 
		</thead>
		<tbody>
			<c:forEach begin="1" end="11" varStatus="timeRowVs">
			<tr>
				<td id="timeLine_${timeRowVs.count}"></td>
				<c:forEach begin="1" end="17" varStatus="vs">
					<td id="man_${timeRowVs.count}_${vs.count}"></td>
				</c:forEach>
			</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="18">현황</td>
			</tr>
		</tfoot>
	</table>
</body>
</html>