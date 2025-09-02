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
			background-color: #f5f5f5;
		}
		.form-container {
			background-color: white;
			padding: 30px;
			border-radius: 10px;
			max-width: 500px;
			margin: 0 auto;		/* 가운데 정렬 */
		}
		h1 {
			color: #4CAF50;
			text-align: center;
		}
		.form-group {
			margin-bottom: 20px;
		}
		label {
			display: block;		/* 블록 요소로 변경 (줄바꿈) */
			margin-bottom: 5px;
			font-weight: bold;
		}
		input[type="text"], input[type="email"] {
			width: 100%;
			padding: 10px;
			border: 1px solid #ddd;
			border-radius: 5px;
			box-sizing: border-box;
		}
		.button-group {
			display: flex;
			gap: 10px;
		}
		button .btn {
			flex: 1;		/* 동일한 너비로 확장 */
			padding: 12px;
			border: none;
			border-radius:5px;
			cursor: pointer;
			text-align: center;
			text-decoration: none;
			display: inline-block;
		}
		button[type="submit"] {
			background-color: #4CAF50;
			color: white;
		}
		.btn-cancle {
			background-color: #f44336;
			color: white;
		}
		.required {
			color: red;
		}
	</style>
</head>
	<body>
		<div class="form-container">
			<h1>새 회원 추가</h1>
			
			<form action="/members" 		<%-- 전송 대상 URL --%>
				  method="post"				<%-- HTTP 메소드 (데이터 생성은 POST) --%>
				  accept-charset="UTF-8"	
			>  
				<div class="form-group">
					<label for="name">이름<span class="required">*</span></label>
					<input type="text"
						   id="name"
						   name="name"
						   placeholder="이름을 입력하세요"
						   required
						   autofocus>
				</div>
				<div class="form-group">
					<label for="email">이메일<span class="required">*</span></label>
					<input type="email"
						   id="email"
						   name="email"
						   placeholder="sample@gmail.com"
						   required>
				</div>
				<div class="button-group">
					 <!-- submit 버튼 : 클릭 시 form 데이터를 action URL로 전송 -->
					 <button type="submit">회원 추가</button>
					 <!-- 취소 링크 : 단순 페이지 이동 (form 제출 아님) -->
					 <a href="/members" class="btn btn-cancle">취소</a>
				</div>
			</form>
		</div>
	</body>
</html>