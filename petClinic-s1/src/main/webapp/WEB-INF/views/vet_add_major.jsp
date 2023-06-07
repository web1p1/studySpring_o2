<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


<form action="vet_add_major_mod" method="get">

<!-- 의사정보(vets - VetsDao) 화면 노출 & 선택 -->
<select name="vet_id">
  <c:forEach items="${vetslist}" var="dto">
    <option value="${dto.id}"> ${dto.first_name}, ${dto.last_name} </option>
  </c:forEach>
</select>

<!-- 전공정보(vet_specialties - VetspecialitiesDao ) 화면 노출 & 선택 -->
<c:forEach items="${specialtieslist}" var="dto" >
  <input type="checkbox" id="major" name="major" value="${dto.id}" >
  <label for="specialty_id"> ${dto.name} </label>
</c:forEach>

<button type="submit">Subscribe</button>

</form>

</body>
</html>