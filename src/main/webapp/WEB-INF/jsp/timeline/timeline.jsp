<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="d-flex justify-content-center">
	<div class="contents-box">
		<%-- 글쓰기 영역 --%>
		<div class="write-box border rounded m-3">
			<textarea id="writeTextArea" placeholder="내용을 입력해주세요" class="w-100 border-0"></textarea>
			
			<%-- 이미지 업로드를 위한 아이콘과 업로드 버튼을 한 행에 멀리 떨어뜨리기 위한 div --%>
			<div class="d-flex justify-content-between">
				<div class="file-upload d-flex">
					<%-- file 태그를 숨겨두고 이미지를 클릭하면 file 태그를 클릭한 것처럼 효과를 준다 --%>
					<input type="file" id="file" accept=".jpg, .jpeg, .png, .gif" class="d-none">
					
					<%-- 이미지에 마우스 올리면 마우스커서가 링크 커서가 변하도록 a 태그 사용 --%>
					<a href="#" id="fileUploadBtn"><img width="35" src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-image-512.png"></a>
					
					<%-- 업로드 된 임시 파일 이름 저장되는 곳 --%>
					<div id="fileName" class="ml-2"></div>
				</div>
				<button id="writeBtn" class="btn btn-info">게시</button>
			</div>
		</div>
		
		<%--// 글쓰기 영역 끝 --%>
		<%-- 타임라인 영역 --%>
		<div class="timeline-box my-5">
			<c:forEach items="${cardViewList}" var="cardview">
			<%-- 카드1 --%>
			<div class="card border rounded mt-3">
				<%-- 글쓴이, 더보기(삭제) --%>
				<div class="p-2 d-flex justify-content-between">
				    <span class="font-weight-bold">글쓴이 ${cardview.user.loginId}</span> 
					<%-- 더보기 ... --%>
					
					<a href="#" class="more-btn">
						<img src="https://www.iconninja.com/files/860/824/939/more-icon.png" width="30">
					</a>
				</div>
				
				<%-- 카드 이미지 --%>
				<div class="card-img">
					<img src="${cardview.post.imagePath}" class="w-100" alt="본문 이미지">
				</div>
				
				<%-- 좋아요 --%>
				<div class="card-like m-3">
					<%-- 하트를 누를때 좋아요가 눌러지고 좋아요 테이블에 등록, 취소하면 테이블에서 삭제 --%>
					<%-- 좋아요가 눌러져 있을때 => 채워진 하트 --%>
					<c:if test = "${cardview.filledLike eq true}">
						<a href="#" class="like-btn" data-post-id="${cardview.post.id}">
							<img src="https://www.iconninja.com/files/527/809/128/heart-icon.png" width="18" height="18" alt="filled heart">
						</a>
					</c:if>
					<%-- 좋아요가 눌러져 있지 않을때 or 비로그인 => 빈하트 --%>
					<c:if test = "${cardview.filledLike eq false}">
						<a href="#" class="like-btn" data-post-id="${cardview.post.id}">
							<img src="https://www.iconninja.com/files/214/518/441/heart-icon.png" width="18" height="18" alt="unfilled heart">
						</a>
					</c:if>
						좋아요 ${cardview.likeCount}개
				</div>
				
				<%-- 글 --%>
				<div class="card-post m-3">
					<span class="font-weight-bold">글쓴이${cardview.user.name}</span>
					<span>${cardview.post.content}</span>
				</div>
				
				<%-- 댓글 제목 --%>
				<div class="card-comment-desc border-bottom">
					<div class="ml-3 mb-1 font-weight-bold">댓글</div>
				</div>
				
				<%-- 댓글 목록 --%>
				<div class="card-comment-list m-2">
					<%-- 댓글 내용들 --%>
					<c:forEach items="${cardview.commentList}" var="list">				
						<div class="card-comment m-1">
					        <c:if test = "${list.comment.postId eq cardview.post.id}">
								<span class="font-weight-bold">${list.user.loginId}</span>
								<span>${list.comment.content}</span>								
								<%-- 댓글 삭제 버튼 --%>
								<c:if test = "${userId eq list.comment.userId}"> 
									<a href="#" class="comment-del-btn" data-user-id="${list.comment.userId}" data-comment-id="${list.comment.id}">
										<img src="https://www.iconninja.com/files/603/22/506/x-icon.png" width="10px" height="10px">
									</a>
								</c:if>
							</c:if>	
						</div>
					</c:forEach>
					
					<%-- 댓글 쓰기 --%>
					<div class="comment-write d-flex border-top mt-2">
						<input type="text" class="form-control border-0 mr-2 comment-input" placeholder="댓글 달기"/> 
						<button id="commentBtn" type="button" class="comment-btn btn btn-light" data-post-id="${cardview.post.id}">게시</button>
					</div>
				</div> <%--// 댓글 목록 끝 --%>
			</div> <%--// 카드1 끝 --%>
	     </c:forEach>
	</div> <%--// 타임라인 영역 끝  --%>
  </div> <%--// contents-box 끝  --%>
</div>

<script>
$(document).ready(function() {
	// 파일이미지 클릭 => 숨겨져 있는 type="file"을 동작시킨다.
	$('#fileUploadBtn').on('click', function(e) {
		e.preventDefault(); // a 태그의 스크롤 올라가는 현상 방지
		$('#file').click(); // input file을 클릭한 것과 같은 효과
	});
	
	// 사용자가 이미지를 선택하는 순간 유효성 확인 및 업로드 된 파일명 노출
	$('#file').on('change', function(e) {
		let fileName = e.target.files[0].name; // path-g6f39ad362_640.png
		console.log(fileName);
		
		// 확장자 유효성 확인
		let ext = fileName.split(".").pop().toLowerCase();
		//alert(ext);
		if (ext != "jpg" && ext != "png" && ext != "gif" && ext != "jpeg") {
			alert("이미지 파일만 업로드 할 수 있습니다.");
			$('#file').val("");  // 파일 태그에 파일 제거(보이지 않지만 업로드 될 수 있으므로 주의)
			$('#fileName').text(''); // 파일 이름 비우기
			return;
		}
		
		// 유효성 통과한 이미지는 상자에 업로드 된 파일 이름 노출
		$('#fileName').text(fileName);
	});
	
	// 글쓰기
	$('#writeBtn').on('click', function() {
		let content = $('#writeTextArea').val();
		console.log(content);
		if (content.length < 1) {
			alert("글 내용을 입력해주세요");
			return;
		}
		
		let file = $('#file').val();
		if (file == '') {
			alert('파일을 업로드 해주세요');
			return;
		}
		
		// 파일이 업로드 된 경우 확장자 체크
		let ext = file.split('.').pop().toLowerCase(); // 파일 경로를 .으로 나누고 확장자가 있는 마지막 문자열을 가져온 후 모두 소문자로 변경
		if ($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
			alert("gif, png, jpg, jpeg 파일만 업로드 할 수 있습니다.");
			$('#file').val(''); // 파일을 비운다.
			return;
		}
		
		// 폼태그를 자바스크립트에서 만든다.
		let formData = new FormData();
		formData.append("content", content);
		formData.append("file", $('#file')[0].files[0]); // $('#file')[0]은 첫번째 input file 태그를 의미, files[0]는 업로드된 첫번째 파일
		
		// AJAX form 데이터 전송
		$.ajax({
			type: "post"
			, url: "/post/create"
			, data: formData
			, enctype: "multipart/form-data"    // 파일 업로드를 위한 필수 설정
			, processData: false    // 파일 업로드를 위한 필수 설정
			, contentType: false    // 파일 업로드를 위한 필수 설정
			, success: function(data) {
				if (data.code == 1) {
					location.reload();
				} else if (data.code == 500) { // 비로그인 일 때
					location.href = "/user/sign_in_view";
				} else {
					alert(data.errorMessage);
				}
			}
			, error: function(e) {
				alert("글 저장에 실패했습니다. 관리자에게 문의해주세요.");
			}
		});  // --- ajax 끝
	});
	
	/*memo
	-글상세 뿌리기
	sns
	- 댓글쓰기 api먼저-get주소로 직접 디비포ㅠ학인
	- 댓글쓰기 javascript
	- 댓글 ajax postid, 댓글내용
	- comment는 mybatis
	- 댓글목록 뿌리기 */
	
	// 클래스로 잡아서 해야한다 댓글쓰기
	$('.comment-btn').on('click', function() {
		
		//console.log("target::", $(event.target))
		let postId = $(this).data('post-id');	// 클릭한 row의 Id
	    var row = $(this).closest('div');
		//console.log("row::", row)
		var comment = row.find("input[type='text']").val();
		console.log("comment::", comment)
	     
		/*
		  <댓글내용가져오기>
		  1.형제태그중에서 input태그를 가져온다
		    let comment = $(this).siblings("input").val().trim();
		  2.클릭한 바로전에 있는 태그
		  let comment = $(this).prev().val().trim();
		*/
		
		//AJAX form 데이터 전송
		$.ajax({
			type: "get"
			, url: "/comment/create"		
			, data : {"postId":postId,
				      "comment":comment}//json으로 구성		
			, success: function(data) {
				if (data.code == 1) {
					location.reload();
				} else {
					alert(data.errorMessage);
				}
			}
			, error: function(e) {
				alert("댓글 저장에 실패했습니다. 관리자에게 문의해주세요.");
			}
		}); // --- ajax 끝
	});
	
	// 댓글삭제
	$('.comment-del-btn').on('click', function(e) {
	    e.preventDefault();// 버튼 클릭시 화면 올라가는 거 방지
	    let userId = $(this).data('user-id');	    // 댓글쓴 아이디
		let commentId = $(this).data('comment-id'); // 삭제할 댓글id
		// 댓글쓴 유저아이디 넘김-서버에서 session의 id와 일치하면 삭제 아니면 삭제 불가
		// AJAX form 데이터 전송
		// 삭제시 삭제조건은 댓글id
		$.ajax({
			type: "delete"
			, url: "/comment/delete"		
			, data : {"userId":userId,
				      "commentId":commentId}//json으로 구성		
			, success: function(data) {
				if (data.code == 1) {
					location.reload(true);
				} else {
					alert(data.errorMessage);
				}
			}
			, error: function(reuqest, status, error) {
				alert("댓글 삭제에 실패했습니다. 관리자에게 문의해주세요.");
			}
		}); // --- ajax 끝
	});
	
	// 하트클릭이벤트
	// class="like-btn"
	$('.like-btn').on('click', function(e) {
		
	    e.preventDefault();// 버튼 클릭시 화면 올라가는 거 방지	   
		let postId = $(this).data('post-id');
		
		$.ajax({
			 type: "Get"
		    , url: "/like/" + postId		
			, success: function(data) {
				if (data.code == 1) {
					location.reload(true);
				} else if (data.code == 300){
					//alert(data.errorMessage);
					//로그인 페이지로 이동
					location.href("/user/sign_in_view");
				}
			}
			, error: function(reuqest, status, error) {
				alert("좋아요 처리시 실패했습니다.");
			}
		}); // --- ajax 끝
	});
});
</script>