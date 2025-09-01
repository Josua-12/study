<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="/index.html">메인</a>
	성공
	<ul>
		<%-- 
			EL 사용
			 ${} : request.getAttrigute("member")로 저장된 객체에 접근
			 실제로는 member.getId() 메서드가 호출됨
		 --%>
		<li>id=${member.id }</li>
		<li>username=${member.username }</li>
		<li>age=${member.age }</li>
	</ul>
</body>
</html>