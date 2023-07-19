<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>	


	<div>
		<h1 class="font-weight-bold">Memo 게시판</h1>
	</div>
	<div>
		<%--로그인이 된 경우에만 로그인 정보와 로그아웃 노출 --%>
		<c:if test = "${not empty userName}">
			<span>${userName} 님 안녕하세요</span>
			<a href="/user/sign_out">로그아웃</a>
		</c:if>	
	</div>
