<%-- JSP 지시자: 페이지 속성 설정 (HTML 타입, UTF-8 인코딩, Java 언어 사용 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <form method="post" action="/jsp/members/save.jsp">  <!-- action: 데이터를 전송할 목적지 -->
        username: <input type="text" name="username">
        age:      <input type="text" name="age">
        <button type="submit">전송</button>
    </form>
</body>
</html>