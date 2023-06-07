<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>


<P><a href="${pageContext.request.contextPath}/list">FreeBoard</a></P>
<P><a href="${pageContext.request.contextPath}/vetslist">Pet-Clinic</a></P>


</body>
</html>
