<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<%@ page import="hello.servlet.basic.domain.member.MemberRepository" %>
<%@ page import="hello.servlet.basic.domain.member.Member" %>


<%
    MemberRepository memberRepository = MemberRepository.getInstance();     // 싱글톤 인스턴스 획득
    System.out.println("save.jsp - service() ");

    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username, age);
    memberRepository.save(member);
%>

<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <ul>
        <li><%=member.getId() %></li>
        <li><%=member.getUsername() %></li>
        <li><%=member.getAge() %></li>
    </ul>
    <a href="/index.html">메인</a>
</body>
</html>