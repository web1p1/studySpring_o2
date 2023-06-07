<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>


<P>	서비스를 이용하기 위해서는 로그인이 필요합니다.
<P>	사용자 등록을 원하시면 xxx-xxx-xxx 로 연락주시거나
<P>	xxx@xxx.com 으로 메모를 남겨주시기 바랍니다. 
	

<P>
<a href="${pageContext.request.contextPath}/login.html">유저로그인</a>
<a href="${pageContext.request.contextPath}/welcome.html">관리자로그인</a>

</body>
</html>
