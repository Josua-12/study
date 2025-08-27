<%@ page contentType="text/html;charset=UTF-8" language="java"  %>

<%@ page import="hello.servlet.basic.domain.member.MemberRepository" %>
<%@ page import="hello.servlet.basic.domain.member.Member" %>
<%@ page import="java.util.List" %>

<%
    MemberRepository memberRepository = MemberRepository.getInstance();     // 싱글톤 인스턴스 획득
    List<Member> members = memberRepository.findAll();      // 모든 회원 목록 조회
%>


<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <a href="/index.html">메인</a>
    <table>
        <thead>
            <th>id</th>
            <th>username</th>
            <th>age</th>
        </thead>
        <tbody>
            <%
                for(Member member : members) {
                    out.write("  <tr>");
                    out.write("     <td>" + member.getId() + "</td>");
                    out.write("     <td>" + member.getUsername() + "</td>");
                    out.write("     <td>" + member.getAge() + "</td>");
                    out.write("  </tr>");
                }
            %>
        </tbody>
    </table>
</body>
</html>