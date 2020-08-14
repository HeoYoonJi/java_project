// 도서 재고 현황 갱신 함수
function updateBookStock() {

	console.log("도서 재고 현황 갱신 : ")

	$.ajax({

		url: contextPath + '/getAllBookStock.do',
		type: 'get',
		contentType: "application/json",
		dataType: 'text',
		success: function (data) {
			// console.log("-------재고현황---------")
			// console.log("data : " + data);

			var json = JSON.parse(data);

			// console.log("JSON : "+json);
			// console.log("json[0]: "+json[0]["id"]);

			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			for (var i = 0; i < json.length; i++) {
				$("#books_stock_section [id^=stock_book_] [id=stock_book_id_" + (i + 1) + "]").text(json[i].id);
				$("#books_stock_section [id^=stock_book_] [id=stock_book_name_" + (i + 1) + "]").html(json[i].name + "<br><br>" + json[i].author);
				$("#books_stock_section [id^=stock_book_] [id=stock_book_genre_" + (i + 1) + "]").html("<b>" + json[i].genre + "</b>");
				$("#books_stock_section [id^=stock_book_] [id=stock_book_img_" + (i + 1) + "]").html("<img src=\"" + contextPath + "/thumb/small_" + json[i].bookImg + "\">");
				$("#books_stock_section [id^=stock_book_] [id=stock_book_limit_" + (i + 1) + "]").html("<b>" + json[i].limit + "</b>");
				$("#books_stock_section [id^=stock_book_] [id=stock_book_quantity_" + (i + 1) + "]").html("<b>" + json[i].quantity + "</b>");
			}
		}, //  success

		error: function (xhr, status) {

			console.log(xhr + " : " + status); //  에러 코드
		} // 

	}); //  $.ajax
}


// 도서 현황/재고 현황 패널(우측) - 서랍식
window.onload = function () {
	
	var panel_status_cnt = 0;// 도서 현황 패널 카운터
	var stock_panel_status_cnt = 0;// 도서 재고 현황 패널 카운터

	var panel_spreading_btn = document.getElementById("books_state_btn");// 도서 현황 버튼
	var stock_panel_spreading_btn = document.getElementById("books_stock_state_btn");// 도서 재고 현황 버튼

	var panel = document.getElementById("books_section");// 도서 현황 패널
	var stock_panel = document.getElementById("books_stock_section");// 도서 재고 현황 패널

	// 도서 현황
	panel.style.transitionProperty = "left";
	panel.style.transitionDelay = ".1s";
	panel.style.transitionDuration = ".5s";

	// 도서 재고 현황
	stock_panel.style.transitionProperty = "left";
	stock_panel.style.transitionDelay = ".1s";
	stock_panel.style.transitionDuration = ".5s";

	// 도서 현황 펼칠 때/접을 때
	panel_spreading_btn.onclick = function (e) {

		console.log("x좌표 : " + panel.style.left);
		console.log("panel_status_cnt : " + panel_status_cnt);

		panel.style.zIndex = 3;
		stock_panel.style.zIndex = 2;

		if (panel_status_cnt % 2 == 0) {

			panel.style.left = "1900px";
			stock_panel.style.left = "1900px";
			panel_status_cnt = 0;
			stock_panel_status_cnt = 0;

		} else {

			panel.style.left = "1300px";
			stock_panel.style.left = "1900px";
			panel_status_cnt = 1;
			stock_panel_status_cnt = 0;

		} // 
		panel_status_cnt++;
		stock_panel_status_cnt++;
	}

	// 도서 재고 현황 펼칠 때/접을 때
	stock_panel_spreading_btn.onclick = function (e) {

		console.log("stock_x좌표 : " + stock_panel.style.left);
		console.log("stock_panel_status_cnt : " + stock_panel_status_cnt);

		panel.style.zIndex = 2;
		stock_panel.style.zIndex = 3;

		if (stock_panel_status_cnt % 2 == 0) {

			stock_panel.style.left = "1900px";
			panel.style.left = "1900px";
			stock_panel_status_cnt = 0;
			panel_status_cnt = 0;

		} else {

			stock_panel.style.left = "1300px";
			panel.style.left = "1900px";
			stock_panel_status_cnt = 1;
			panel_status_cnt = 0;

		} // 
		stock_panel_status_cnt++;
		panel_status_cnt++;
	}

}

// 가격 구분점 (,)표기 함수 
function numberWithCommas(x) {
	x = x.toString();
	var pattern = /(-?\d+)(\d{3})/;
	while (pattern.test(x))
		x = x.replace(pattern, "$1,$2");
	return x;
}

/////////////////////////////////////////////////////////////////////////////

// 내방 고객 조회 날짜 선택
$(function () {

	$.datepicker.regional['ko'] = {
		autoSize: true,
		closeText: '닫기',
		prevText: '이전달',
		nextText: '다음달',
		currentText: '오늘',
		yearRange: "2020:2020",
		monthNames: ['1월', '2월', '3월', '4월', '5월', '6월',
			'7월', '8월', '9월', '10월', '11월', '12월'],
		monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월',
			'7월', '8월', '9월', '10월', '11월', '12월'],
		dayNames: ['일', '월', '화', '수', '목', '금', '토'],
		dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
		dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
		weekHeader: 'Wk',
		dateFormat: 'yy-mm-dd', // 날짜 포맷
		firstDay: 0,
		minDate: new Date(2020, 5 - 1, 1), // 2020-05-01
		isRTL: false,
		showMonthAfterYear: true,
		yearSuffix: '년',
		// 버그 패치 : 컴포넌트 겹침 현상 해소 ex) 성별 필드와의 겹침
		beforeShow: function () {
			setTimeout(function () {
				$('.ui-datepicker').css('z-index', 10);
			}, 0);
		}
	};

	$.datepicker.setDefaults($.datepicker.regional['ko']);

	$("#entrance_date").datepicker({ changeYear: true, changeMonth: true });
});

//////////////////////////////////////////////////////////////////////////////

$(function () {

	// 개별 도서 현황 조회 팝업 초기화(은닉)
	$("div#one_book_detail_popup").hide();

	// 도서 검색 결과 팝업 초기화(은닉)
	$("div#books_search_section").hide();

	// 전체 도서 현황
	$.ajax({

		url: contextPath + '/getAllBooks.do',
		type: 'get',
		contentType: "application/json",
		dataType: 'text',
		success: function (data) {

			// console.log("data : "+data);
			var json = JSON.parse(data);

			// console.log(json['10:30 ~ 11:30']);
			// console.log("---------------------------------------")
//			console.log(json['10:30 ~ 11:30'][0]);
			// console.log(json['10:30 ~ 11:30'][0].entranceNum);
//			if (json['10:30 ~ 11:30'][0].books.length > 0){
//				console.log(json['10:30 ~ 11:30'][0].books[0]);	                	
//			} else {
//				console.log("책 없음");
//			} 
//			console.log("길이:"+json.length);
//			console.log(json[0]);
//			$("#books_section [id^=book_] [id=book_id_1]").text(json[0].id);
//			$("#books_section [id^=book_] [id=book_name_1]").text(json[0].name);
//			$("#books_section [id^=book_] [id=book_genre_1]").text(json[0].genre);
//			$("#books_section [id^=book_] [id=book_img_1]").html("<img src=\""+contextPath+"/thumb/small_"+json[0].bookImg+"\">");
//			$("#books_section [id^=book_] [id=book_author_1]").text(json[0].author);
//			$("#books_section [id^=book_] [id=book_pubs_1]").text(json[0].pubs);
//			$("#books_section [id^=book_] [id=book_price_1]").text(json[0].price); 

			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			for (var i = 0; i < json.length; i++) {
				// console.log("-- 도서 이미지 : "+json[i].bookImg);
				$("#books_section [id^=book_] [id=book_id_" + (i + 1) + "]").text(json[i].id);
				$("#books_section [id^=book_] [id=book_name_" + (i + 1) + "]").text(json[i].name);
				$("#books_section [id^=book_] [id=book_genre_" + (i + 1) + "]").html("<b>" + json[i].genre + "</b>");
				$("#books_section [id^=book_] [id=book_img_" + (i + 1) + "]").html("<img src=\"" + contextPath + "/thumb/small_" + json[i].bookImg + "\">");
				$("#books_section [id^=book_] [id=book_author_" + (i + 1) + "]").html(json[i].author + "<br><br>" + json[i].pubs + "<br><br>" + numberWithCommas(json[i].price) + "원");
				$("#books_section [id^=book_] [id=book_eval_point_" + (i + 1) + "]").html("<b>" + json[i].evalPoint + "</b>");
			}
		}, //  success

		error: function (xhr, status) {

			console.log(xhr + " : " + status); //  에러 코드
		} // 

	}); //  $.ajax

	///////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// 빈 화면으로 시작
	// 도서 재고 현황
	updateBookStock();
	/*
	$.ajax({

		url: contextPath + '/getAllBookStock.do',
		type: 'get',
		contentType: "application/json",
		dataType: 'text',
		success: function (data) {
			// console.log("-------재고현황---------")
			// console.log("data : " + data);

			var json = JSON.parse(data);

			// console.log("JSON : "+json);
			// console.log("json[0]: "+json[0]["id"]);


			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			for (var i = 0; i < json.length; i++) {
				$("#books_stock_section [id^=stock_book_] [id=stock_book_id_" + (i + 1) + "]").text(json[i].id);
				$("#books_stock_section [id^=stock_book_] [id=stock_book_name_" + (i + 1) + "]").html(json[i].name + "<br><br>" + json[i].author);
				$("#books_stock_section [id^=stock_book_] [id=stock_book_genre_" + (i + 1) + "]").html("<b>" + json[i].genre + "</b>");
				$("#books_stock_section [id^=stock_book_] [id=stock_book_img_" + (i + 1) + "]").html("<img src=\"" + contextPath + "/thumb/small_" + json[i].bookImg + "\">");
				$("#books_stock_section [id^=stock_book_] [id=stock_book_limit_" + (i + 1) + "]").html("<b>" + json[i].limit + "</b>");
				$("#books_stock_section [id^=stock_book_] [id=stock_book_quantity_" + (i + 1) + "]").html("<b>" + json[i].quantity + "</b>");
			}
		}, //  success

		error: function (xhr, status) {

			console.log(xhr + " : " + status); //  에러 코드
		} // 

	}); //  $.ajax
	*/

});

$(function () {
	// 도서 전체 현황 조회
	$("#books_state_btn").click(function () {
		$.ajax({

			url: contextPath + '/getAllBooks.do',
			type: 'get',
			contentType: "application/json",
			dataType: 'text',
			success: function (data) {

				// console.log("data : "+data);
				var json = JSON.parse(data);

				// console.log(json['10:30 ~ 11:30']);
				// console.log("---------------------------------------")
				// console.log(json['10:30 ~ 11:30'][0]);
				// console.log(json['10:30 ~ 11:30'][0].entranceNum);
				/* if (json['10:30 ~ 11:30'][0].books.length > 0){
					console.log(json['10:30 ~ 11:30'][0].books[0]);	                	
				} else {
					console.log("책 없음");
				} */
				// console.log("길이:"+json.length);
				/*  console.log(json[0]);
				 $("#books_section [id^=book_] [id=book_id_1]").text(json[0].id);
				 $("#books_section [id^=book_] [id=book_name_1]").text(json[0].name);
				 $("#books_section [id^=book_] [id=book_genre_1]").text(json[0].genre);
				 $("#books_section [id^=book_] [id=book_img_1]").html("<img src=\""+contextPath+"/thumb/small_"+json[0].bookImg+"\">");
				 $("#books_section [id^=book_] [id=book_author_1]").text(json[0].author);
				 $("#books_section [id^=book_] [id=book_pubs_1]").text(json[0].pubs);
				 $("#books_section [id^=book_] [id=book_price_1]").text(json[0].price); */

				///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

				for (var i = 0; i < json.length; i++) {
					$("#books_section [id^=book_] [id=book_id_" + (i + 1) + "]").text(json[i].id);
					$("#books_section [id^=book_] [id=book_name_" + (i + 1) + "]").text(json[i].name);
					$("#books_section [id^=book_] [id=book_genre_" + (i + 1) + "]").html("<b>" + json[i].genre + "</b>");
					$("#books_section [id^=book_] [id=book_img_" + (i + 1) + "]").html("<img src=\"" + contextPath + "/thumb/small_" + json[i].bookImg + "\">");
					$("#books_section [id^=book_] [id=book_author_" + (i + 1) + "]").html(json[i].author + "<br><br>" + json[i].pubs + "<br><br>" + numberWithCommas(json[i].price) + " 원");
					$("#books_section [id^=book_] [id=book_eval_point_" + (i + 1) + "]").html("<b>" + json[i].evalPoint + "</b>");
					//  $("#books_section [id^=book_] [id=book_price_"+i+"]").text(json[i].price);
				}
			}, //  success

			error: function (xhr, status) {

				console.log(xhr + " : " + status); //  에러 코드
			} // 

		}); //  $.ajax
	});

	// 내방 고객 조회
	$("#customer_entrances_btn").click(function () {

		console.log("날짜:" + $("#entrance_date").val());
		console.log("시간:" + $("#entrance_time").val());
		
		// 날짜 유효성 점검(정규표현식(regex)) : /^2020-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$/
		// ex) 2020-05-01
		var date = "2020-5-1"
		var regex = /^2020-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$/
		
		if (regex.test(date)) {
			
			// 날짜 점검 2020-05-01부터 오늘까지가 유효 날짜 -> 미만이거나 초과하는지 유효성 점검
			var thisTime = Date.now();
			var inputTime = Date.parse($("#entrance_date").val());
			var pastLimitTime = Date.parse("2020-05-01 00:00:00");
			
			console.log("thisTime : "+thisTime);
			console.log("Date.parse : "+Date.parse($("#entrance_date").val()));
			
			if (inputTime > thisTime) {
				
				alert("미래 날짜 선택 불가능!")
				
			} else if (inputTime < pastLimitTime) {
				
				alert("2020-05-01 이전 날짜 선택 불가능!")
				
			} else { // 유효한 날짜(정상) : 2020-05-01부터 오늘까지
				
				$.ajax({
		
					url: contextPath + '/makeRandomEntrancesByTimeBand.do',
					type: 'get',
					contentType: "application/json",
					dataType: 'text',
					data: {
						entranceDate: $("#entrance_date").val(),
						entranceTime: $("#entrance_time").val()
					},
					success: function (data) {
					
						// alert("OK")
						// console.log(data)
						
						var json = JSON.parse(data);
						// console.log("------------------json-------------");
						// console.log(json);
						
						// 기존 데이터 초기화(삭제)
						for (var i = 0; i < 11; i++) {
							for (var j = 0; j < 17; j++) {
								$("td#man_" + (i + 1) + "_" + (j + 1)).text(" ")
							}
						}
						
						// console.log("데이터 초기화");
		
						// 시간대 출력
						var timeLines = Object.keys(json); // 시간대
						
						// 시간대별 인원 출력
						for (var i = 0; i < timeLines.length; i++) {
							for (var j = 0; j < json[timeLines[i]].length; j++) {
								
								// 20:30 ~ 21:30(마지막 시간대) 11번째 이후로 0을 공백으로 처리
								if (i == 10 && j >= 10) {
									$("td#man_" + (i + 1) + "_" + (j + 1)).text("");
								} else {
									$("td#man_" + (i + 1) + "_" + (j + 1)).text(json[timeLines[i]][j].entranceNum);
								}
							}
						}//for-end
						
						/////////////////////////////////////////////////////////////////
						
						// 우측 도서 재고 현황 수정
						updateBookStock();
						
		
					}, //  success
		
					error: function (xhr, status) {
						console.log(xhr + " : " + status); //  에러 코드
					} // 
				}); //  $.ajax
 
			} // if (inputTime > thisTime) { 끝
				
		} else { // 정규표현식 else
		
			alert("2020-05-01 형식으로 입력하십시오")
			
		} // 정규표현식 else 끝
		
	});

	// 도서 재고 현황 조회
	$("#books_stock_state_btn").click(function () {
		$.ajax({

			url: contextPath + '/getAllBookStock.do',
			type: 'get',
			contentType: "application/json",
			dataType: 'text',
			success: function (data) {
				// alert("재고현황")
				// console.log("data : " + data);

				var json = JSON.parse(data);

				// console.log("JSON : "+json);
				// console.log("json[0]: "+json[0]["id"]);

				///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

				for (var i = 0; i < json.length; i++) {
					$("#books_stock_section [id^=stock_book_] [id=stock_book_id_" + (i + 1) + "]").text(json[i].id);
					$("#books_stock_section [id^=stock_book_] [id=stock_book_name_" + (i + 1) + "]").html(json[i].name + "<br><br>" + json[i].author);
					$("#books_stock_section [id^=stock_book_] [id=stock_book_genre_" + (i + 1) + "]").html("<b>" + json[i].genre + "</b>");
					$("#books_stock_section [id^=stock_book_] [id=stock_book_img_" + (i + 1) + "]").html("<img src=\"" + contextPath + "/thumb/small_" + json[i].bookImg + "\">");
					$("#books_stock_section [id^=stock_book_] [id=stock_book_limit_" + (i + 1) + "]").html("<b>" + json[i].limit + "</b>");
					$("#books_stock_section [id^=stock_book_] [id=stock_book_quantity_" + (i + 1) + "]").html("<b>" + json[i].quantity + "</b>");
				}
			}, //  success

			error: function (xhr, status) {

				console.log(xhr + " : " + status); //  에러 코드
			} // 

		}); //  $.ajax
	});
	
	// 도서 입고 버튼
	$("#stock_recovery_btn").click(function(){
		
		$.ajax({

			url: contextPath + '/updateAllStock.do',
			type: 'get',
			contentType: "application/json",
			dataType: 'text',
			success: function (data) {
			
				alert(data);
				location.reload();
				
			}, //  success

			error: function (xhr, status) {

				console.log(xhr + " : " + status); //  에러 코드
			} // 

		}); //  $.ajax
		
	});

});

/////////////////////////////////////////////////////////////////////////////////


// 개별 도서 현황 조회 팝업 닫기 버튼
$(function() {

	popup_close_btn.onclick = function(e) {    
		$("div#one_book_detail_popup").hide();
	}

});

$(function(){

	// 개별 도서 현황 조회(IT 모바일)
	$("[id^=it_book_num]").click(function(e){
		console.log("개별 도서 현황 조회")

		// 개별 도서 현황 조회 팝업 보이기
		$("div#one_book_detail_popup").show();
		
		var id=e.currentTarget.id;
		var num=id.substring("it_book_num".length);

		console.log("id : "+id);
		console.log("num : "+num);

		// 개별 도서 현황(console)
		console.log("도서 번호 : "+$("#books_section #book_num_"+num).text());
		console.log("고유 번호 : "+$("#book_id_"+num).text());

		// 팝업 출력
		var popup_content="";
		popup_content+="<b>1. 도서 번호&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>"+$("#book_id_"+num).text()+"<br>";
		popup_content+="<b>2. 도서 제목&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>"+$("#book_name_"+num).text()+"<br>";
		popup_content+="<b>3. 도서 장르&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>"+$("#book_genre_"+num).text()+"<br>";

		var author=$("#book_author_"+num).html().trim().split("<br><br>")[0];
		popup_content+="<b>4. 도서 저자&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>"+author+"<br>";

		popup_content+="<b>현재 도서 재고&nbsp;&nbsp;&nbsp;</b>"+$("#stock_book_quantity_"+num).text();

		var popup_content2="";
		popup_content2+=$("#book_img_"+num).html();

		$("div#one_book_detail_popup #popup1").html(popup_content);
		$("div#one_book_detail_popup #popup2").html(popup_content2);
		
	});

	// 개별 도서 현황 조회(소설)
	$("[id^=novel_book_num]").click(function(e){
		console.log("개별 도서 현황 조회")

		$("div#one_book_detail_popup").show();
		
		var id=e.currentTarget.id;
		var num=id.substring("novel_book_num".length);

		console.log("id : "+id);
		console.log("num : "+num);

		// 개별 도서 현황(console)
		console.log("도서 번호 : "+$("#books_section #book_num_"+num).text());
		console.log("고유 번호 : "+$("#book_id_"+num).text());

		// 팝업 출력
		var popup_content="";
		popup_content+="<b>1. 도서 번호&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>"+$("#book_id_"+num).text()+"<br>";
		popup_content+="<b>2. 도서 제목&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>"+$("#book_name_"+num).text()+"<br>";
		popup_content+="<b>3. 도서 장르&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>"+$("#book_genre_"+num).text()+"<br>";

		var author=$("#book_author_"+num).html().trim().split("<br><br>")[0];
		popup_content+="<b>4. 도서 저자&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>"+author+"<br>";

		popup_content+="<b>현재 도서 재고&nbsp;&nbsp;&nbsp;</b>"+$("#stock_book_quantity_"+num).text();

		var popup_content2="";
		popup_content2+=$("#book_img_"+num).html();

		$("div#one_book_detail_popup #popup1").html(popup_content);
		$("div#one_book_detail_popup #popup2").html(popup_content2);
		
	});

	// 개별 도서 현황 조회(여행)
	$("[id^=trip_book_num]").click(function(e){
		console.log("개별 도서 현황 조회")

		$("div#one_book_detail_popup").show();
		
		var id=e.currentTarget.id;
		var num=id.substring("trip_book_num".length);

		console.log("id : "+id);
		console.log("num : "+num);

		// 개별 도서 현황(console)
		console.log("도서 번호 : "+$("#books_section #book_num_"+num).text());
		console.log("고유 번호 : "+$("#book_id_"+num).text());

		// 팝업 출력
		var popup_content="";
		popup_content+="<b>1. 도서 번호&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>"+$("#book_id_"+num).text()+"<br>";
		popup_content+="<b>2. 도서 제목&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>"+$("#book_name_"+num).text()+"<br>";
		popup_content+="<b>3. 도서 장르&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>"+$("#book_genre_"+num).text()+"<br>";

		var author=$("#book_author_"+num).html().trim().split("<br><br>")[0];
		popup_content+="<b>4. 도서 저자&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>"+author+"<br>";

		popup_content+="<b>현재 도서 재고&nbsp;&nbsp;&nbsp;</b>"+$("#stock_book_quantity_"+num).text();

		var popup_content2="";
		popup_content2+=$("#book_img_"+num).html();

		$("div#one_book_detail_popup #popup1").html(popup_content);
		$("div#one_book_detail_popup #popup2").html(popup_content2);
		
	});

	// 개별 도서 현황 조회(자기계발)
	$("[id^=self_book_num]").click(function(e){
		console.log("개별 도서 현황 조회")

		$("div#one_book_detail_popup").show();
		
		var id=e.currentTarget.id;
		var num=id.substring("self_book_num".length);

		console.log("id : "+id);
		console.log("num : "+num);

		// 개별 도서 현황(console)
		console.log("도서 번호 : "+$("#books_section #book_num_"+num).text());
		console.log("고유 번호 : "+$("#book_id_"+num).text());

		// 팝업 출력
		var popup_content="";
		popup_content+="<b>1. 도서 번호&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>"+$("#book_id_"+num).text()+"<br>";
		popup_content+="<b>2. 도서 제목&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>"+$("#book_name_"+num).text()+"<br>";
		popup_content+="<b>3. 도서 장르&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>"+$("#book_genre_"+num).text()+"<br>";

		var author=$("#book_author_"+num).html().trim().split("<br><br>")[0];
		popup_content+="<b>4. 도서 저자&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>"+author+"<br>";

		popup_content+="<b>현재 도서 재고&nbsp;&nbsp;&nbsp;</b>"+$("#stock_book_quantity_"+num).text();

		var popup_content2="";
		popup_content2+=$("#book_img_"+num).html();

		$("div#one_book_detail_popup #popup1").html(popup_content);
		$("div#one_book_detail_popup #popup2").html(popup_content2);
		
	});

	// 도서 검색
	$("#search_btn").click(function(e){
		
		console.log("도서 검색");

		if ($("[name=search_word]").val().trim()==""){ // 검색어 없을 때

			alert("검색어를 입력하세요.");
			$("[name=search_word]").focus();

		} else{ // 검색어 있을 때

			// 도서 검색 결과 팝업 보기
			$("div#books_search_section").show();

			$.ajax({

				url: contextPath + '/bookListBySearch.do',
				type: 'get',
				contentType: "application/json",
				dataType: 'text',
				data: {
					search_kind: $("[name=search_kind]").val(),
					search_word: $("[name=search_word]").val().trim()
				},
				success: function (data) {
					
					//console.log("data : "+data);
					//도서 검색 결과 패널 초기화
					$("#books_search_section_content").html("");

					var json=JSON.parse(data);

					console.log("검색 도서 수 : "+json.length);
					//console.log("도서명 : "+json[0].name);

					$("#search_books_len").val(json.length);

					var content="";

					for (var i = 0; i < json.length; i++) {

						console.log("도서명 : "+json[i].name);

						//$("#books_search_section [id^=book_search_] [id=book_search_id_" + (i + 1) + "]").text(json[i].id);
						//$("#books_search_section [id^=book_search_] [id=book_search_name_" + (i + 1) + "]").text(json[i].name);
						//$("#books_search_section [id^=book_search_] [id=book_search_genre_" + (i + 1) + "]").html("<b>" + json[i].genre + "</b>");
						//$("#books_search_section [id^=book_search_] [id=book_search_img_" + (i + 1) + "]").html("<img src=\"" + contextPath + "/thumb/small_" + json[i].bookImg + "\">");
						//$("#books_search_section [id^=book_search_] [id=book_search_author_" + (i + 1) + "]").html(json[i].author + "<br><br>" + json[i].pubs + "<br><br>" + numberWithCommas(json[i].price) + " 원");
						//$("#books_search_section [id^=book_search_] [id=book_search_eval_point_" + (i + 1) + "]").html("<b>" + json[i].evalPoint + "</b>");

						content +='<div class="book_search_section" id="book_search_'+(i+1)+'">'
								+'	<div id="book_search_num_'+(i+1)+'"'
								+'			class="book_cell">'+(i+1)+'</div>'
								+'	<div id="book_search_id_'+(i+1)+'" class="book_cell">'+json[i].id+'</div>'
								+'	<div id="book_search_img_'+(i+1)+'" class="book_cell">'
								+'		<img src="' + contextPath + '/thumb/small_' + json[i].bookImg + '">'
								+'	</div>'
								+'	<div id="book_search_genre_'+(i+1)+'" class="book_cell"><b>' + json[i].genre + '</b></div>'
								+'	<div id="book_search_name_'+(i+1)+'" class="book_cell">'+json[i].name+'</div>'
								+'	<div id="book_search_author_'+(i+1)+'" class="book_cell">'
								+		json[i].author + '<br><br>' + json[i].pubs + '<br><br>' + numberWithCommas(json[i].price) + ' 원'
								+'	</div>'
								+'	<div id="book_search_eval_point_'+(i+1)+'" class="book_cell"><b>' + json[i].evalPoint + '</b></div>'
								+'</div>';

					}

					$("#books_search_section_content").html(content);

				}, //  success
		
				error: function (xhr, status) {
		
					console.log(xhr + " : " + status); //  에러 코드
				} // 
	
			}); //  $.ajax

		} //if
		
	});
	
	// 도서 검색 결과 팝업 닫기 버튼
	$(function() {
	
		search_close_btn.onclick = function(e) {    
			$("div#books_search_section").hide();
		}
	
	});

});