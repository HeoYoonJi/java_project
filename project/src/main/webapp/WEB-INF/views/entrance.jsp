<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>시간대 별 방문 현황</title>
<!-- bootstrap css -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
	/* 시간대 별 방문 현황 테이블 */
	table#entrance_tbl {
		width:600px;
		border-spacing:0;
		border-left:1px solid #ccc;
		border-top:1px solid #ccc;
		/* background-color:yellow; */
	}
	
	/* 시간대 별 방문 현황 테이블 : 인원 현황 개별셀  */
	table#entrance_tbl td[id^=man] {
		width: 27px;
		height:27px;
		text-align:center;
		border-spacing:0;
		border-right:1px solid #DCC1DC;
		border-bottom:1px solid #DCC1DC;
		padding:5px 0;
		font-size:10pt;
		color:#A550A6;
		font-weight:bold;
		background-color:#fff;
	}
	
	/* 시간대 별 방문 현황 테이블 : 시간대 개별셀  */
	table#entrance_tbl td[id^=timeLine] {
		/* background-color:#EDD3ED; */
		width:124px;
		font-weight:bold;
		color:#B952BA;
		font-size:10pt;
		text-align:center;
		border-spacing:0;
		border-right:1px solid #DCC1DC;
		border-bottom:1px solid #DCC1DC;
		padding:5px 0;
		font-size:10pt;
		color:#A550A6;
		font-weight:bold;
	}
	
	/* 시간대 별 방문 현황 테이블 : 시간대 타이틀  */
	table#entrance_tbl thead tr th#entrance_time_title {
		border-bottom:1px solid #DCC1DC;
		border-right:1px solid #DCC1DC;
		padding:15px 10px;
		background-color:#C785C8;
		color:#fff;
	}
	
	/* 시간대 별 방문 현황 테이블 : 인원현황 타이틀  */
	table#entrance_tbl thead tr th#entrance_man_title {
		border-right:1px solid #DCC1DC;
		border-bottom:1px solid #DCC1DC;
		padding:15px 10px;
		background-color:#C785C8;
		color:#fff;
	}
	
	/* 시간대 별 방문 현황 테이블 : 시간대 타이틀(짝수셀) */
	table#entrance_tbl tr:nth-child(2n) {
		background-color:#E1BDE1;
	}
	
	/* 시간대 별 방문 현황 테이블 : 시간대 타이틀(홀수셀) */
	table#entrance_tbl tr:nth-child(2n+1) {
		background-color:#EDD3ED;
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
				<th id="entrance_time_title">시간대</th>
				<th colspan="17" id="entrance_man_title">인원현황</th>
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
		<!-- <tfoot>
			<tr>
				<td colspan="18">현황</td>
			</tr>
		</tfoot> -->
	</table>
</body>
</html>