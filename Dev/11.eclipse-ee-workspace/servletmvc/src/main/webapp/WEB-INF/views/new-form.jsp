<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- 
		* 현재 URL : /servlet-mvc/members/new-form ==> /servlet-mvc/members/save
			- 상대경로 사용[현재 URL이 속한 계층 경로 + /save]
	 --%>
	
	<form method="post" action="save">		<!-- action : 데이터를 전송할 목적지 -->
        username: <input type="text" name="username">
        age: <input type="text" name="age">
        <button type="submit">전송</button>
    </form>
    
</body>
</html>