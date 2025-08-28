<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%--@taglib 지시자 : 외부 태그 라이브러리 선언
       - JSTL Core 라이브러리 : 조건문, 반복문 등 기본 제어 구조 제공
       - JSTL Functions 라이브러리 : 문자열 처리 함수 제공 
     --%>
<%@ page import="hello.servlet.basic.domain.memo.Memo" %>
<%@ page import="hello.servlet.basic.domain.memo.MemoRepository" %>
<%
   // JSP 스크립틀릿(Scriptlet) 영역
   // 한글 깨짐 방지 : POST 요청시 request body의 인코딩 설정
   request.setCharacterEncoding("UTF-8");
   MemoRepository repository = MemoRepository.getInstance();
   
   // 요청 파라미터
   String action = request.getParameter("action");
   
   // POST 요청 처리 (Controller 역할)
   if("save".equals(action)) {
	   String content = request.getParameter("content");
	   
	   // 유효성 검사 : null체크와 공백 제거 후 빈 문자열 체크
	   if(content != null && !content.trim().isEmpty()) {
		   // Builder 패턴으로 Memo 객체 생성 및 저장
		   repository.save(Memo.builder()
				   .content(content.trim())
				   .build());
	   }
	   
	   /*
	       PRG 패턴
	       	- Post - Redirect - Get
	       	- 목적 : F5(새로고침)시 폼 재전송 방지
	       	- 동작 : POST 처리 후 => 302 Redirect => Get(값을 read) 요청으로 페이지 로드
	   */
	   response.sendRedirect("memo.jsp");
	   return;
   } else if ("delete".equals(action)) {
	   String idStr = request.getParameter("id");
	   if(idStr != null) {
		   try {
			   // String => Long 변환 후 삭제
			   repository.delete(Long.parseLong(idStr));
	   	   } catch (NumberFormatException e) {
	   		   // 
	   	   }
	   }
	   response.sendRedirect("memo.jsp");
	   return;
   }
   
   request.setAttribute("memos", repository.findAll());
   request.setAttribute("count", repository.getCount());
   
   
%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <title>메모장</title>
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
   <style type="text/css">
   .memo-container {
      max-width: 600px;   /* 최대 너비 제한 */
      margin: 0 auto;      /* 가운데 정렬 */
   }
   </style>
</head>
<body class="bg-light">      <!-- 밝은 배경색 -->
   <div class="container memo-container py-5">   <!-- py-5 : 상하 패딩 -->
      <h2>📝 간단한 메모장</h2>
      <p class="text-muted">
         JSP - JSTL/EL
         <span class="badge bg-secondary">${count }개</span>
      </p>
      
      <!-- 메모 입력 폼 -->
      <div class="mb-4">
         <div class="card-body">
            <form action="memo.jsp" method="post" onsubmit="return validateForm()">
               <input type="hidden" name="action" value="save">
               <div class="mb-3">
                  <textarea rows="3" name="content" id="content" class="form-control" placeholder="메모를 입력하세요."
                  maxlength="200" <%-- 최대 글자 수 제한 --%> required <%-- 필수 입력 --%>>
                  </textarea>
                  <div class="char-counter mt-1">
                     <span id="charCount">0</span> / 200
                  </div>
               </div>
               <button type="submit" class="btn btn-primary">
                  저장
               </button>
            </form>
         </div>
      </div>
      
      <!-- 메모 목록 영역 -->
      <%-- Java의 if-else if-else --%>
      <c:choose>
         <%-- c:when = if or else if --%>
         <c:when test="${empty memos}">   <%-- EL의 empty 연산자 : null이거나 size가 0 = 메모가 없음 --%>
            <!-- 메모가 없을 때 -->
            <div class="alert alert-info text-center">
               첫번째 메모를 작성해 보세요! 🎉
            </div>
         </c:when>
         <%-- c:otherwise => else --%>
         <c:otherwise>
            <!-- 메모 목록 출력 -->
            <c:forEach items="${memos }" var="memo">
               <div class="card-body">
                  <div class="d-flex justify-content-between align-items-start">
                     <!-- 메모 내용 -->
                     <div class="flex-grow-1"> <%-- 남은 공간 모두 차지 --%>
                        <p class="mb-1">${fn:escapeXml(memo.content) }</p>
                        <small class="text-muted">${memo.formattedDate }</small>
                     </div>
                     <!-- 삭제 버튼 -->
                     <form action="memo.jsp" method="post" style="margin: 0;">
                     	<input type="hidden" name="action" value="delete">
                     	<input type="hidden" name="id" value="${memo.id }">
                     	<button type="submit" 
                     			class="btn btn-sm btn-outline-danger"
                     			onclick="return confirm('삭제하시겠습니까?')">
                     	삭제</button>
                     </form>
                  </div>               
               </div>
            </c:forEach>
         </c:otherwise>
      </c:choose>
   </div>
</body>
</html>