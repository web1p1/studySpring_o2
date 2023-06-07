<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>서비스를 이용하기 위해서 로그인이 필요합니다.</h3>
<c:url value="j_spring_security_check" var="loginUrl"/>
<form action="${loginUrl}" method="post">
	<c:if test="${param.ng != null}">
	<p>
		LogIn NG!! <br />
		<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != NULL}">
			message : <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
		</c:if>
	</p>
	</c:if>
	ID : <input type="text" name="j_username"> 
	PW : <input type="text" name="j_password"> 
	<input type="submit" value="LOGIN"> <br />
</form>
<P></P>
<P> <a href="${pageContext.request.contextPath}/login.html">유저로그인</a> , 
    <a href="${pageContext.request.contextPath}/welcome.html">관리자로그인</a></P>
<P> <a href="${pageContext.request.contextPath}/notice/home">공지사항</a>

</body>
</html>