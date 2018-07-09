<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>오류 발생!</h1>
<pre>
<%
Throwable error = (Throwable)request.getAttribute("error");
error.printStackTrace(new PrintWriter(out));    //그냥 out을 안쓰고 새로만든 이유는 new로안만들면 jsp에있는 기본 out이 출력이됨
%>
</pre>
</body>
</html>