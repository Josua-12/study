<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
		body {
			font-family: sans-serif, Arial;
			margin: 40px;
		}
		
		.member-card {
			border: 2px solid #4CAF50;
			border-radius: 10px;
			padding: 20px;
			max-width: 400px;	
			background-color: #f9f9f9;
			
		}	
		
		.member-card h2 {
			color: #4CAF50;
			margin-top: 0;
		}
		
		.info-row {
			margin: 15px 0;
			padding: 10px;
			background-color: white;
			border-radius: 5px;
		}
		
		.label {
			font-weight: bold;
			color: #666;
		}
		
		.back-link {
			display: inline-block;
			margin-top: 20px;
			padding: 10px 20px;
			background-color: #2196F3;
			color: white;
			text-decoration: none;
			border-radius: 5px;
		}
		
		.back-link:hover {
			background-color: #197620;
		}
		
		.flow-info {
			background-color: #e8f5e9;
			padding: 15px;
			margin-bottom: 20px;
			border-radius: 5px;		
			border-left: 4px solid #4CAF50;
		}
		
</style>
</head>
<body>
	<div class="flow-info">
		<h3>ModelAndView 처리 흐름</h3>
		<p>Controller => ModelAndView 생성 => Model 데이터 + View 이름 설정 => ViewResolver => JSP 렌더링</p>
	</div>
	
	<h1>회원 상세 정보</h1>
	<div class="member-card">
		<h2>회원 정보</h2>
		
		<!-- ID 정보  -->
		<div class="info-row">
			<span class="label">ID: </span>
			${member.id }
		</div>
		
		<!-- 이름 정보 -->
		<div class="info-row">
			<span class="label">이름: </span>
			${member.name }
		</div>
		
		<!-- 이메일 정보 -->
		<div class="info-row">
			<span class="label">이메일: </span>
			${member.email }
		</div>
		
	</div>
	
	<!-- 목록으로 돌아가기 -->
	<a href="/members" class="back-link">목록으로 돌아가기</a>
	
</body>
</html>



















