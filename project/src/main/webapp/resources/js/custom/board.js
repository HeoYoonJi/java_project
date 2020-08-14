/**
 *
 */

// jQuery
$(document).ready(function(){

	// 게시글 보기
	$("a[id^=boardNum_]").click(function(e){

		var boardNum = e.target.id.substring(9);

		$.ajax ({

			url : contextPath + "/board/boardDetailREST.do/boardNum/"+boardNum,
			contentType : "application/json",
			type : "POST",
			success : function (article) {

				// 게시글 내용 : modal
				$("div#viewModal #boardNum").val(article.boardNum);
				$("div#viewModal #boardName").val(article.boardName);
				$("div#viewModal #boardSubject").val(article.boardSubject);
				$("div#viewModal #boardContent").val(article.boardContent);

				// 글 조회수 갱신
				$("div#viewModal #boardReadCount_"+article.boardNum).text(article.boardReadCount);

				var boardFile = article.boardFile==null ?
						        "파일없음": article.boardFile;

				console.log(boardFile);

				if (boardFile == '파일없음')
					$("div#viewModal #boardFile").text(boardFile);
				else {

				  // 파일 중복 방지위해 접미사 부착 파일 처리
				  var originalFile = boardFile;

				  $("div#viewModal #boardFile").html("<a href=\""
				   		  + contextPath + "/upload/" + article.boardNum + "/"+boardFile+"/\">"+boardFile+"</a>");
				} //

				// 버튼 아이디 변경 : ex) "updateContentBtn_"+article.boardNum
				$("button").attr("id", "updateContentBtn_"+article.boardNum);

				// 글 수정 전송 버튼 아이디 변경 : ex) "update_btn_"+article.boardNum
				$("#update_btn_").attr("id", "update_submit_btn_"+article.boardNum);

				// 추가 : 글 삭제 버튼 아이디 변경
				$("button").attr("id", "deleteContentBtn_"+article.boardNum);

				// 글 삭제폼 패쓰워드 초기화
				$("form#boardDeleteForm input[name=boardPass]").val("");

			} //success

		}); // ajax

	});
	// 게시글 보기 끝

	// 게시글 팝업(modal) 수정
	$("button[id^=updateContentBtn_]").click(function (e) {

		//alert("수정 팝업 열기");

		var boardNum = e.target.id.substring(17); // "updateContentBtn_" 뒤부분 "글번호" 취득

		$.ajax ({

			url : contextPath + "/board/boardDetailREST.do/boardNum/"+boardNum,
			contentType : "application/json",
			type : "POST",
			success : function (article) {

				// 기존 게시글 내용
				// modal
				$("form#updateForm #update_boardNum").val(article.boardNum);
				$("form#updateForm #update_boardName").val(article.boardName);
				$("form#updateForm #update_boardSubject").val(article.boardSubject);
				$("form#updateForm #update_boardContent").val(article.boardContent);

				// 댓글 여부 점검위한 필드값 할당
				$("form#updateForm #update_boardReLev").val(article.boardReLev);

				// 표제 추가 ex) 게시글 수정 : "답글"
				var article_title = article.boardReLev == 1 ? ": 답글" : "";
				$("span#article_title").text(article_title);

				if (article.boardReLev == 0) { // 원글일 경우(답글이 아닐 경우)

					// 중복 첨가 방지를 위해 기존 필드 있는지 여부 점검
					// alert("첨부 파일 필드 여부 : "+$("#updateForm").find("label[for=update_boardFile_new]").text());
					var fileFldFlag =$("#updateForm").find("span label[for=update_boardFile_new]").text().trim();

					if (fileFldFlag == '') { // 파일 필드가 첨부되어 있지 않으면 ...

						var fileFldContent  = "<div name=\"update_file_section\" class=\"input-group\">"
											+ "<div class=\"input-group-prepend\">"
										    + "<label for=\"update_boardFile\" class=\"control-label input-group-text ml-2\">"
										    + "첨부파일 :</label>"
											+ "</div>"
											+ "<div class=\"custom-file\">"
											+ "		<span class=\"btn btn-default btn-file\">"
											+ "			<label for=\"update_boardFile_new\" class=\"custom-file-label\">파일 선택</label>"
											+ "			<input type=\"file\" class=\"custom-file-input\" id=\"update_boardFile_new\" name=\"update_boardFile_new\" />"
											+ "		</span>"
											+ "</div>"
											+ "<div class=\"input-group\">"
											+ "		<div class=\"input-group-prepend\">"
											+ "			<div class=\"ml-3 mt-2\" id=\"update_boardFile\"></div>"
											+ "		</div>"
											+ "</div>";

						// 파일 필드 추가
						$("form[id=updateForm] #article_content").append(fileFldContent);	// fileFldContent

					} //

					var boardFile = article.boardFile == null ?
					        "파일없음": article.boardFile;

					console.log("# 첨부 파일 : "+boardFile);

					if (boardFile == '파일없음')
						$("#update_boardFile").text(boardFile);
					else {

					  // 이 부분도 절대 경로로 변경
					  $("#update_boardFile").html("<a href="
					  	    + contextPath + "/upload/" + article.boardNum + "/"+boardFile+"/\">"+boardFile+"</a>");

					  console.log("original file : "+originalFile);

					  // 파일 중복 방지위해 접미사 부착 파일 처리
					  var originalFile = boardFile.split(".")[0];
					  var ext = boardFile.split(".")[1];

					  originalFile = originalFile + "." + ext;

					  $("#update_boardFile").html("<a href="
							  + contextPath + "/upload/" + article.boardNum + "/"+boardFile+"/\">"+boardFile+"</a>");

					} //

				} else { // 답글일 경우

					// 기존에 파일 필드가 추가되어 있다면 제거
					$("form[id=updateForm] #article_content [name=update_file_section]").remove();
				}

				// 조회수 업데이트
				$tempId = "#boardReadCount_"+article.boardNum;
				$($tempId).text(article.boardReadCount);
			}

		}); // ajax

	}); // 게시글 팝업(modal) 수정 끝

	// 게시글 팝업(modal) 수정 : 전송
	$("button[id^=update_btn_]").click(function (e) {

		//alert("글수정 요청");

		var boardNum = e.target.id.substring(17); // "updateContentBtn_" 뒤부분 "글번호" 취득

		var form = $('form#updateForm')[0];
        var formData = new FormData(form);

     	// 파일 필드 초기화 : 버그 패치(초기화하지 않으면 이전 업로드 파일이 반영됨)
        // 수정할 글이 원글이 아닌 댓글이면... : 댓글은 파일 업로드 없음
		if ($("[name=update_boardReLev]").val() == 0) {
        	$("#update_boardFile_new").val("");
		}

        $.ajax ({

			url : contextPath + "/board/updateBoard.do/"+boardNum,

			cache: false,
            async: false,
            cache: false,
            contentType: false,
            processData: false,

			type : "POST",
			data : formData,

			success : function (result) {

				alert(result);
				location.reload(); // 페이지 리프레시(재설정)
			},

			error : function(xhr, status) {

                console.log(xhr+" : "+status); // 에러 코드
            }
		}); // ajax

	}); // 게시글 팝업(modal) 수정 : 전송 끝

	//////////////////////////////////////////////////////////

	// 게시글 삭제
	$("button[id^=deleteContentBtn_]").click(function (e) {

		var boardNum = e.target.id.substring(17); // "deleteContentBtn_" 뒤부분 "글번호" 취득
		// alert(boardNum);

		// 삭제폼에 글번호 표시(입력)
		$("form#boardDeleteForm input[name=boardNum]").val(boardNum);

	});	// 게시글 삭제  끝

	// 게시글 삭제 : 전송
	$("button[id^=delete_submit_btn_]").click(function (e) {

		//alert("글삭제 요청");

		var boardNum = e.target.id.substring(17); // "delete_subumit_btn_" 뒤부분 "글번호" 취득

		//alert(boardNum);

        $.ajax ({

			url : contextPath + "/board/deleteBoardProcREST.do/"+boardNum,
			type : "POST",

			data : { boardPass : $("input#boardPass").val() },

			success : function (result) {

				alert(result);
				location.reload(); // 페이지 리프레시(재설정)
			},

			error : function(xhr, status) {

                console.log(xhr+" : "+status); // 에러 코드
            }
		}); // ajax

	}); // 게시글 삭제 : 전송 끝

	// 게시글 답글 쓰기(Modal)
	$("button[id^=replyContentBtn_]").click(function (e) {

		var boardNum = e.target.id.substring(17); // "updateContentBtn_" 뒤부분 "글번호" 취득

		$.ajax ({

			url : contextPath + "/board/boardDetailREST.do/boardNum/"+boardNum,
			contentType : "application/json",
			type : "POST",
			success : function (article) {

				// 기존 게시글 내용
				$("[name=boardNum]").val(article.boardNum);

				// 답글 관련 필드값 설정
				$("[name=boardReRef]").val(article.boardReRef);
				$("[name=boardReLev]").val(article.boardReLev);
				$("[name=boardReSeq]").val(article.boardReSeq);

				// 이중 답글 방지 : level=1 만 가능하도록 설정함
				if (article.boardReLev==1) {

					alert("이중 답글을 쓸 수 없습니다.");
					$("#replyModal").hide();
					location.reload();

				} //
			},

			error : function(xhr, status) {

                console.log(xhr+" : "+status); // 에러 코드
            }

		}); // ajax

	}); // 게시글 답글 쓰기(modal) 끝

	// 게시글 답글 처리
	$("button[id^=replySubmitBtn]").click(function (e) {

		//alert("답글 쓰기 요청");

		var boardNum = e.target.id.substring(17); // "updateContentBtn_" 뒤부분 "글번호" 취득
		$("#page").val(page);

		var form = $('form#boardReplyForm')[0];
        var formData = new FormData(form);

        $.ajax ({

			url : contextPath + "/board/replyWriteBoardProc.do",
			cache: false,
            async: false,
            cache: false,
            contentType: false,
            processData: false,

			type : "POST",
			data : formData,

			success : function (result) {

				if (result.trim() == "true") {
					alert("답글 저장 성공");
				} else {
					alert("답글 저장 실패");
				}

				location.reload(); // 페이지 리프레시(재설정)
			}
		}); // ajax

	}); // 게시글 팝업(modal) 수정 : 전송 끝

});

//angularJS
var app=angular.module('boardFormApp', []);

// 글쓰기 제어 : 폼점검을 위해서는 형식적으로  Controller 부분을 기입해야 됨.
app.controller('boardFormController', function($scope) {

	// 게시글 쓰기폼 초기화 : 초기화하지 않을 경우 이전의 글들(쓰다가 빠져나갈 경우)이 남아 있을 수 있음.
	$scope.initWriteForm = function($event) {

		$scope.boardName = "";
		$scope.boardPass = "";
		$scope.boardSubject = "";
		$scope.boardContent = "";
		$scope.boardFile = "";

	} // 게시글 쓰기폼 초기화 끝

	// 게시글 수정폼 초기화 : 초기화하지 않을 경우 이전의 글들이 남아 있을 수 있음.
	$scope.initUpdateForm = function($event) {

		$scope.update_boardPass = ""; // 패쓰워드 초기화

		//alert("수정");

		// 폼점검 플래그 초기화 : 답글일 경우
		$scope.updateForm.update_boardPass.$valid = "false"; // 비밀번호 미입력 상태 (초기 상태)
		$scope.updateForm.update_boardSubject.$valid = "true";
		$scope.updateForm.update_boardContent.$valid = "true";

	} // 게시글 수정폼 초기화 끝

	// 게시글 댓글 쓰기폼 초기화 : 초기화하지 않을 경우 이전의 글들이 남아 있을 수 있음.
	$scope.initReplyWriteForm = function($event) {

		$scope.boardName = "";
		$scope.boardPass = "";
		$scope.boardSubject = "";
		$scope.boardContent = "";

	} // 게시글 댓글 쓰기폼 초기화 끝

	// 글쓰기  폼점검
	$scope.writeFormCheck = function($event) {

		console.log("글쓰기 폼점검");

		if ($scope.boardForm.boardName.$valid == true &&
			$scope.boardForm.boardPass.$valid == true &&
			$scope.boardForm.boardSubject.$valid == true &&
			$scope.boardForm.boardContent.$valid == true)
		{
	  		console.log("글쓰기 요청 전송");

			var form = $('form#boardForm')[0];
	        var formData = new FormData(form);

	        $.ajax ({

				url : contextPath + "/board/writeBoardProcREST.do",

				cache: false,
	            async: false,
	            cache: false,
	            contentType: false,
	            processData: false,

				type : "POST",
				data : formData,

				success : function (result) {

					alert(result);
					location.reload(); // 페이지 리프레시(재설정)
				},

				error : function(xhr, status) {

	                console.log(xhr+" : "+status); // 에러 코드
	            }
			}); // ajax

		} else {

			alert("폼점검 미완료")
			document.boardForm.boardName.focus();
		} //

	} // 글쓰기  폼점검 끝


	// 목록 버튼 시작
	$("#list_btn").click(function(e) {

		location.href = contextPath + "/board/boardList.do/1";

	});
	// 목록 끝

});