<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		* {
			margin: 0;
			padding: 0;
			box-sizing: border-box;	
		}
		body {
			font-family: sans-serif, Tahoma, Geneva, Verdana, 'Segoe UI';
			background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
			min-height: 100vh;
			display: flex;
			justify-content: center;
			align-items: center;
		}
		.container {
			background: white;
			border-radius: 20px;
			padding: 50px;
			box-shadow: 0 20px 60px rgba(0,0,0,0.3);
			text-align: center;
			max-width: 600px;
		}
		h1 {
			color: #333;
			margin-bottom: 20px;
			font-size: 2.5em;
		}
		.subtitle {
			color: #666;
			margin-bottom: 40px;
			font-size: 1.1em;
		}
		.menu-grid {
			display: grid;
			grid-template-columns: 1fr 1fr;
			gap: 20px;
			margin-top: 30px;
		}
		.menu-card {
			background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);
			border-radius: 15px;
			padding: 30px 20px;
			color: white;
			text-decoration: none;
			transition: transform 0.3s, box-shadow 0.3s;
		}
		
		.menu-card:hover {
			transform: translateY(-5px);
			box-shadow: 0 10px 30px rgba(0,0,0,0.2);
		}
		
		.menu-card.param {
			background: linear-gradient(135deg, #fa709a 0%, #fee140 100%);
		}
		
		
	</style>
</head>
<body>
	<div class="container">
		<h1>도서 검색 시스템</h1>
		<p class="subtitle">Spring Boot @RequestParam Train</p>
		
		<div class="menu-grid">
			<a href="/request-param-test" class="menu-card param">
				<h2>@RequestParam</h2>
				<p>URL 파라미터 처리 train</p>
				<p class="badge">JSP 응답</p>
			</a>
		</div>
	</div>
</body>
</html>






















