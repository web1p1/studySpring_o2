<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="vet_add_major_mod" method="get">
  
    <select name="vet_id" >  
      <c:forEach items="${vetslist}" var="dto">
        <option value="${dto.id}"> ${dto.first_name}, ${dto.last_name} </option>
      </c:forEach>
    </select>

		<fieldset>
		  <legend>Choose your interests</legend>
			  <div>
		      <c:forEach items="${specialtieslist}" var="dto" varStatus="status" >
		        <input type="checkbox" id="major" name="major" value="${dto.id}" unchecked>
		        <label for="specialty_id">${dto.name}</label>
		      </c:forEach>
			  </div>
			  <div>
			    <button type="submit">Subscribe</button>
			  </div>
		</fieldset>
</form>

</body>
</html>