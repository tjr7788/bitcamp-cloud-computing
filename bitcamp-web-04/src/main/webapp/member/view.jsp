<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>멤버 뷰</title>
</head>
<body>
<h1>멤버보기2</h1>
<c:choose>
<c:when test="${member == null}">
    <p>해당 아이디가 없습니다</p>
</c:when>
<c:otherwise>
	<table border='1'>
	<form action='update' method='post'>
	<tr><th>아이디</th><td>
	    <input readonly type='text' name='id' value='${member.id}' readonly></td></tr>
	<tr><th>이메일</th>
	    <td><input type='email' name='email' value='${member.email}'></td></tr>
	<tr><th>암호</th>
	    <td><input type='password' name='password'></td></tr>
	
	<p><button>변경하기</button><a href='delete?id=${member.id}'>삭제하기</a></p>
	</form>
</c:otherwise>
</c:choose>
</table>
</body>
</html>
