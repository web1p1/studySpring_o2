<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

  <form action="vet_add" method="get">
    <table width="600" cellpadding="0" cellspacing="0" border="1">
      <tr>
        <td width="70"> FirstName </td>
        <td> <input type="text" name="first_name" size = "50"> </td>
      </tr>
      <tr>
        <td> LastName </td>
        <td> <input type="text" name="last_name" size = "50"> </td>
      </tr>
      <tr >
        <td colspan="2"> <input type="submit" value="입력"> &nbsp;&nbsp; <a href="list">목록보기</a></td>
      </tr>
    </table>
  </form>

</body>
</html>