<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		background: linear-gradient(135deg, #ffa726 0%, #ff6b6b 100%);
		min-height: 100vh;
		padding: 20px;
	}
	
	.container {
		background: white;
		border-radius: 15px;
		padding: 40px;
		box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
		max-width: 800px;
		margin: 0 auto;;
	}
	
	h1 {
		color: #ff6b00;
		margin-bottom: 10px;
		display: flex;
		align-items: center;
		gap: 10px;
	}
	
	.description {
		color: #666;
		margin-bottom: 30px;
		padding: 15px;
		background: #fff3e0;
		border-radius: 8px;
		border-left: 4px solid #ff9800;
	}
	
	.annotation-badge {
		background: #ff9800;
		color: white;
		padding: 5px 15px;
		border-radius: 20px;
		font-size: 0.8em;
	}
	.form-section {
		margin-bottom: 40px;
		padding: 20px;
		background: #f9f9f9;
		border-radius: 10px;
	}
	.form-section h2 {
		color: #333;
		margin-bottom: 20px;
		font-size: 1.3em;
	}
	
	.form-group {
		margin-bottom: 15px;
	}
	
	label {
		display: block;
		margin-bottom: 5px;
		color: #555;
		font-weight: bold;
	}
	
	.back-btn {
		display: inline-block;
		margin-top: 20px;
		padding: 10px 20px;
		background: #6c757d;
		color: white;
		text-decoration: none;
		border-radius: 5px;
	}
</style>
</head>
<body>
	<div class="container">
		<h1>
			@RequestParam Train <span class="annotation-badge">URL 파라미터 -> Java 변수 </span>
		</h1>

		<div class="description">
			<strong>@RequestParam이란?</strong> HTTP 요청의 파라미터를 컨트롤러 메서드 파라미터로 자동
			바인딩해주는 애노테이션입니다.<br> url 쿼리 스트링 (?key=value) 또는 Form 데이터를 Java
			변수로 받을 수 있음.
		</div>
		
		<!-- 1. 기본 사용법  -->
		<div class="form-section">
			<h2>1. 필수 파라미터</h2>
			<!-- GET 방식: URL에 파라미터가 노출됨 (?keyword=값) -->
			<form action="/books/search" method="get">
				<div class="form-group">
					<label for="keyword">검색 키워드 (필수)</label>
					<!-- name="keyword"가 @RequestParam의 파라미터명과 매핑됨  -->
					<input type="text" id="keyword" name="keyword"
						placeholder="예: 자바, 스프링" required>
				</div>
				<button type="submit">검색하기</button>
			</form>
		</div>
		
		<!-- 2. 선택적 파라미터  -->
		<div class="form-section">
			<h2>2: 선택적 파라미터 & 기본값</h2>
			<form action="/books/advanced-search" method="get">
				<div class="form-group">
					<label for="adv-keyword">키워드(선택)</label>
					<input type="text" id="adv-keyword" name="keyword"
						placeholder="비워도 됩니다.">
				</div>
				<div class="form-group">
					<label for="category">카테고리 (선택)</label>
					<select id="category" name="category">
						<!-- value=""는 서버에 빈 문자열 전송 -->
						<option value="">전체</option>
						<option value="IT">IT</option>
						<option value="인문">인문</option>
						<option value="역사">역사</option>
					</select>
				</div>
				<div class="form-group">
					<label for="page">페이지 번호 (기본값: 1)</label>
					<input type="number" id="page" name="page" min="1"
					 placeholder="비워두면 1">
				</div>
				<div class="form-group">
					<label for="size">페이지 크기 (기본값: 10)</label>
					<input type="number" id="size" name="size" min="10"
					 max="50" placeholder="비워두면 10">
				</div>
				<button type="submit">고급 검색</button>				
			</form>
		</div>
		
		<a href="/" class="back-btn"><-- 메인으로 돌아가기</a>
		
	<!-- JSTL 조건문: 검색 결과가 있을 때만 표시 -->
	<!-- EL로 모델 속성 접근 -->
	<!-- books는 Controller에서 model.addAttribute("books", ...)로 전달된 값-->
		<c:if test="${!empty books}">
			<div style="margin-top: 30px; padding: 20px; background: #f0f9ff; border-radius: 10px; border: 2px solid #0ea5a9;">
				<h2 style="color: #0284c7; margin-bottom: 15px;">
					<!-- JSTL의 switch-case구문 -->
					<c:choose>
						<c:when test="${searchType == 'basic'}">
							기본 검색 결과 (키워드: ${keyword})
						</c:when>
						<c:when test="${searchType == 'advanced'}">
							고급 검색 결과 
						</c:when>
					</c:choose>
				</h2>
			
				<p style="background: #e0f2fe; padding: 10px; border-radius: 5px; margin-bottom: 15px;">
					<!-- 삼항연산자 -->
					총 <strong>${searchType == 'advanced' ? totalCount : resultCount }</strong>건이 검색되었습니다.
					<c:if test="${searchType == 'advanced'}">
						(페이지: ${currentPage} / ${totalPages})
					</c:if>
				</p>
				<table style="width: 100%; border-collapse:collapse; background: white;">
					<thead>
						<tr style="background: #0284c7; color: white;">
							<th style="padding: 10px; border: 1px solid #ddd;">ID</th>
							<th style="padding: 10px; border: 1px solid #ddd;">제목</th>
							<th style="padding: 10px; border: 1px solid #ddd;">저자</th>
							<th style="padding: 10px; border: 1px solid #ddd;">카테고리</th>
							<th style="padding: 10px; border: 1px solid #ddd;">가격</th>
							<th style="padding: 10px; border: 1px solid #ddd;">제고</th>
						</tr>
					</thead>
					<tbody>
					<!-- JSTL 반복문: books 컬렉션 순회  -->
					<c:forEach var="book" items="${books}">
						<tr>
							<td style="padding: 8px; border: 1px solid #ddd;">${book.id}</td>
							<td style="padding: 8px; border: 1px solid #ddd; font-weight: bold;">${book.title}</td>
							<td style="padding: 8px; border: 1px solid #ddd;">${book.author}</td>
							<td style="padding: 8px; border: 1px solid #ddd;">${book.category}</td>
							<td style="padding: 8px; border: 1px solid #ddd; color: #ea580c; font-weight: bold;">${book.price}원</td>
							<td style="padding: 8px; border: 1px solid #ddd;">${book.stock}</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		
		</c:if>	
	</div>
	
</body>
</html>




















