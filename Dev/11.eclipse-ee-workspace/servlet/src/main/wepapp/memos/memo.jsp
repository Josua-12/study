<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
    @taglib 지시자 : 외부 태그 라이브러리 선언
        - JSTL Core 라이브러리 : 조건문, 반복문 등 기본 제어 구조 제공
--%>

<%@ page import="hello.servlet.basic.domain.memo.Memo" %>
<%@ page import="hello.servlet.basic.domain.memo.MemoRepository" %>

<%
    // 한글 깨짐 방지 : POST 요청 시 request body의 인코딩 설정



    request.setCharacterEncoding("UTF-8");

%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
   메모 리스트
</body>
</html>