<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>	
<%@ taglib prefix="fmt" uri=" http://java.sun.com/jsp/jstl/fmt " %>	

<div calss="d-flex justify-content-center" >
	<div class="w-50 bg-info">
		<h1>글 목록</h1>
		
		<table calss = "table">
			<thead>
				<tr>
					<th>No.</th>
					<th>제목</th>
					<th>작성날짜</th>
					<th>수정날짜</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${postList}" var="post">
					<tr>
						<td>${post.id}</td>
						<td><a href = "/post/post_detail_view?postId=${post.id}">${post.subject}</a></td>
						<%--ZonedDateTime->Date->String --%>
						<td>
						   <fmt:parseDate value="${post.createdAt}" pattern="yyyy-MM-dd 'T'HH:mm:ss" var = "parsedsCteateAt"/>
						   <fmt:formatDate value="${parsedsCteateAt}" pattern="yyyy년 M월 d일 HH:mm:ss"/>
						</td>						
			        	<td>
			        	   <fmt:parseDate value="${post.updatedAt}" pattern="yyyy-MM-dd 'T'HH:mm:ss" var = "parsedsupdatedAt"/>
						   <fmt:formatDate value="${parsedsupdatedAt}" pattern="yyyy년 M월 d일 HH:mm:ss"/>
						</td>   
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<div class = "d-flex justify-content-left">
			<a href="" class="btn btn-warning">글쓰기</a>
		</div>
	</div>
</div>