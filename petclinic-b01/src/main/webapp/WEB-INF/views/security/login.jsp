<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>"<s:authentication property="name"/>" 님께서 로그인하셨습니다.</h4>


<s:authorize ifAnyGranted="ROLE_USER">
<p> <b>USER</b> "<s:authentication property="name"/>" is Log-In</p>
</s:authorize>

<s:authorize ifAnyGranted="ROLE_ADMIN">
<p> <b>ADMIN</b> "<s:authentication property="name"/>" is Log-In
</s:authorize>

<a href="${pageContext.request.contextPath}/j_spring_security_logout">로그아웃</a>


<p>
<li><a href="${pageContext.request.contextPath}/freeboard/list">FreeBoard</a>
<li><a href="${pageContext.request.contextPath}/petclinic/vetslist">Pet-Clinic</a>
<li><a href="${pageContext.request.contextPath}/login.html">로그인정보</a>
<li><a href="${pageContext.request.contextPath}/welcome.html">어드민전용페이지</a>

</body>
</html>