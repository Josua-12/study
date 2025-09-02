<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원 목록</title>
	<style type="text/css">
		body {
			font-family: sans-serif, Arial;
			margin: 40px;
		}
		table {
			border-collapse: collapse;
			width: 100%;
			max-width: 600px;
		}
		th, td {
			border: 1px solid #ddd;
			padding: 12px;
			text-align: left;
		}
		th {
			background-color: #4CAF50;
			color: white;
		}
		tr:hover {
			background-color: #f5f5f5;
		}
		a {
			text-decoration: none;
			color: #2196F3;
		}
		a:hover {
			text-decoration: underline;
		}
		.flow-info {
			background-color: #f0f0f0;
			padding: 15px;
			margin-bottom: 20px;
			border-radius: 5px;
		}
		.header-section {
			display: flex;
			justify-content: space-between;
			align-items: center;
			margin-bottom: 20px;
		}
		.add-button {
			display: inline-block;
			padding: 10px 20px;
			background-color: #4CAF50;
			color: white;
			text-decoration: none;
			border-radius: 5px;
			margin-bottom: 20px;
		}
		.add-button:hover {
			background-color: #45a049;
			text-decoration: none;
		}
	</style>
</head>
	<body>
		<div class="flow-info">
			<h3>Spring MVC 처리 흐름</h3>
			<ol>
				<li>클라이언트가 /members 요청</li>
				<li>DispatcherServlet이 요청 수신</li>
				<li>HandlerMapping이 적절한 Controller 찾음</li>
				<li>Handler(Controller)가 비즈니스 로직 실행</li>
				<li>Model에 데이터 저장</li>
				<li>ViewResolver가 JSP 경로 결정</li>
				<li>JSP가 HTML로 랜더링되어 응답</li>
			</ol>
		</div>
		
		<div class="header-section">
			<h1>회원 목록</h1>
			<!-- 회원 추가 페이지로 이동하는 링크 버튼 -->
			<!-- GET /members/new 요청 (Controller의 @GetMapping("/new")와 매칭) -->
			<a href="/members/new" class="add-button">+새 회원 추가</a>
		</div>
		
		
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>이름</th>
					<th>이메일</th>
					<th>상세보기</th>
				</tr>			
			</thead>
			<tbody>
				<c:forEach var="member" items="${members }">
					<tr>
						<td>${member.id }</td>		<!-- getId() 호출 -->
						<td>${member.name }</td>	<!-- getName() 호출 -->
						<td>${member.email }</td>	<!-- getEmail() 호출 -->
						<td>
							<a href="/members/${member.id}">상세보기</a>
						</td>
					</tr>
					
				</c:forEach>
			</tbody>

		</table>
		
	</body>
</html>

















