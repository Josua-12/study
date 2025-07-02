#   📝 개인 포트폴리오 웹사이트 제작 가이드

## 🎯 프로젝트 개요
**제한시간** : 4주 (6.30 ~ 7.25)  
**사용 기술** : HTML5, CSS, JavaScript  
**목표** : 전문적인 개인 포트폴리오 웹사이트 제작  

---

## 🗒️ 기본 요구사항
### ✅ 필수 구현 기능
#### 1. **네비게이션 바** 
    - 로고 / 이름 표시
    - 메뉴 링크 (홈, 소개, 기술, 프로젝트, 연락처)
    - 팔로우 버튼
    - 반응형 햄버거 메뉴(모바일)
    - 스크롤 시 배경 변화 효과(반투명, blur)

#### 2. **메인 화면** 
    - 대형 프로필 이미지
    - 이름과 직업 타이틀
    - 매력적인 자기소개
    - CTA 버튼들 (프로젝트 보기, 이력서 다운로드)
    - 소셜 미디어 링크 

#### 3. **소개 섹션**
    - 상세한 자기소개 텍스트
    - 완성 프로젝트 수, 팔로워, Github 커밋수
      연속 학습일 수 

#### 4. **기술 스킬 섹션**
    - 보유 기술을 태그 형태로 표시
    - 클릭 시 선택/해제 기능
    - 선택된 스킬 색상 변경, 알림(alert)

#### 5. **프로젝트 섹션**
    - 최소 3개의 프로젝트 카드
    - 각 프로젝트 별 이미지, 제목, 설명, 기술 스택
    - Github / Live Demo 링크 버튼

#### 6. **연락처 섹션**
    - 연락처 정보 표시 (이메일, 전화, 위치, Github)
    - 메시지 전송 폼 (이름, 이메일, 메시지)

#### 7. **푸터**
    - 저작권 정보
    - 소셜 링크

### 🎨 디자인 요구사항
- **반응형 디자인** : 모바일, 태블릿, 데스크톱 완벽 지원
- **모던 디자인** : CSS Grid, Flexbox, 그라디언트 활용
- **애니메이션** : 스크롤 애니메이션, 호버 효과 
- **일관성** : 통일된 색상 팔레트와 타이포그래피

---

## ✍️ 단계별 전개사항

### 1주차 : HTML 구조 설계
**⏰ 예상 소요시간** : 8-9시간

#### 내용
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    
</body>
</html>
```
#### ✅ 체크리스트
- [ ] 시멘틱 HTML5 태그 사용 (`<nav>`, `<section>`, `<header>`, `<footer>`)

- [ ] 적절한 ID와 클래스명 작성
- [ ] 메타 태그

### 2주차 : CSS 스타일링
**⏰ 예상 소요시간** : 15-20시간

#### 내용

1. **css 변수 설정**
    ```CSS
    element {
    --main-bg-color: brown;
    }
    element {
    --main-bg-color: brown;
    }
    ```

2. **레이아웃 구성**
    - CSS Grid와 Flexbox 조합
    - 반응형 네비게이션 바
    - 풀스크린
    - 섹션별 최적화된 레이아웃

3. **스타일링**
    ```CSS
        .container {
            display: grid; 
            grid-template-columns: 100px 200px 300px;   
            grid-template-rows: 50px 100px;    
        }
        .items {
            border: 1px solid #222;
            padding: 10px;
            font-size: 10px;
            text-align: center;
        }
    ```


4. **반응형 디자인**
    ```CSS
    @media screen and (min-width: 900px) {
        article {
            padding: 1rem 3rem;
        }
    }
    @supports (display: flex) {
        @media screen and (min-width: 900px) {
            article {
            display: flex;
            }
        }   
    }
    ```
#### ✅ 체크리스트
- [ ] CSS Grid와 Flexbox 적절히 활용
- [ ] 모바일 반응형 디자인
- [ ] hover 효과
- [ ] CSS 변수로 색상 통일성 유지
- [ ] 부드러운 트랜지션 효과
- [ ] 크로스 브라우징 호환성 확인(브라우저마다 잘 실행되는지)

### 3주차 : JavaScript 기능 구현
**⏰ 예상 소요시간** : 13-16시간

#### 내용

1. **네비게이션 기능**
    ```javascript
    function getGrade(score) {
        if(score >= 90) return 'A';
        else if(score >= 80) return 'B';
        else if(score >= 70) return 'C';
        else if(score >= 60) return 'D';
        else return 'F';
    }
    ```

2. **인터렉티브 기능**
    ```javascript
    function clearDisplay() {
        const display = document.getElementById("display");
        currentInput = ''; 
        display.value = '';

        console.log('계산기가 초기화되었습니다.');
    }
    ```

3. **애니메이션 구현**
    ```javascript
    function backspace() {
        const display = document.getElementById("display");
        currentInput = currentInput.slice(0, -1); 
        display.value = currentInput;
        console.log('백스페이스, 현재식 : ', currentInput);
    }
    ```

4. **폼 처리**
    ```javascript

    ```

#### ✅ 체크리스트
- [ ] 모든 인터렉티브 요소 정상 작동
- [ ] 부드러운 스크롤 애니메이션 구현
- [ ] 폼 유효성 검사 및 에러 처리
- [ ] 코드 주석 충분히 작성

### 4주차 : 완성 및 최적화
**⏰ 예상 소요시간** : 10-12시간

#### 내용

1. **성능 최적화**
    - 이미지 최적화(적절한 사이즈, 포맷 등)
    - 불필요한 코드 제거
    - 로딩 속도 개선 

2. **SEO 최적화**
    ```html

    ```

3. **브라우저 호환성 테스트**
    - Chrome, Firefox, Safari, Edge 테스트
    - 모바일 브라우저 테스트
    - 다양한 해상도에서 테스트

4. **접근성 개선**
    - 키보드만으로 모든 기능 사용 가능한지 확인

#### ✅ 체크리스트
- [ ] 모든 브라우저에서 정상 작동
- [ ] 모바일에서 완벽한 사용자 경험
- [ ] 로딩 시간 3초 이내
- [ ] 접근성 가이드라인 준수 

## 🏆 보너스 기능
- [ ] **다크 모드 토글**
- [ ] **다국어 지원**
- [ ] **음성 소개**
- [ ] **AI 챗봇**

## ✅ 제출 가이드
### 📁 **폴더 구조**
```
portfolio/
|-- index.html
|-- css/
|    |-- style.css
|    |-- response.css
|-- js/
|    |-- script.js
|    |-- animation.js
|-- images/
|    |-- profile.jpg
|    |-- project.png
|-- README.md
```

### ✍️ **제출 사항**
1. **소스 코드** : 모든 HTML, CSS, JS 파일
2. **README.md** : 프로젝트 소개, 기능 설명, 설치 및 실행방법
3. **개발 일지** : 주차별 진행 사항, 내용, 트러블 슈팅
4. **스크린샷** 


### **주차별 진행상황**
1. **1주차** : 네비게이션 바, 팔로우 버튼, 메뉴 버튼(nav, button) 등 Header파트 완료