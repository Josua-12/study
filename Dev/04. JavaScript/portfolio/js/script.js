let now = new Date(); // 특정한 파라미터를 주지 않으면 오늘 날짜로 객체 지정
let firstDay = new Date("2025-05-27"); // 시작 날짜를 객체로 지정

// 1970년 1월 1일 0시 0분 0초부터 지금까지의 밀리 초 경과 시간 (정수)
let toNow = now.getTime(); // 오늘까지 지난 시간 (밀리초)(1970.01.01~)
let tofirst = firstDay.getTime(); // 2025년 05월 27일까지 지난 시간(1970.01.01~2025.05.27)
let passTime = toNow - tofirst; // 5월 27일부터 오늘까지 지난 시간 (밀리초)

// 밀리 초를 일 수로 계산하고 반올림 , round : 소수 첫째자리에서 반올림해서 정수
passTime= Math.round(passTime/(1000*60*60*24)) //1초 * 60(1분에) * 60(1시간에) *24(하루에)
document.querySelector("#result").innerText = passTime;

document.addEventListener("DOMContentLoaded", () => {
const hamburger = document.querySelector(".hamburger");
const navMenu = document.querySelector(".nav-menu");

    hamburger.addEventListener("click", () => {
        navMenu.classList.toggle("active");
        hamburger.classList.toggle("active");
    });
});

window.addEventListener('scroll', () => {
  const navbar = document.querySelector('.navbar');
  
  if(window.scrollY > 50) {
    navbar.classList.add('scrolled');
  } else {
    navbar.classList.remove('scrolled');
  }

});

const username = "Josua-12"; 
const apiUrl = `https://api.github.com/users/Josua-12/events/public`;

async function getCommitCount() {
    try {
        const response = await fetch(apiUrl);
        const events = await response.json();
        let commitCount = 0;

        // 최근 이벤트 중 PushEvent만 골라 커밋 수 계산
        events.forEach(event => {
        if (event.type === "PushEvent") {
            commitCount += event.payload.commits.length;
    }
    });

    document.getElementById("commit-count").textContent = commitCount;
} catch (error) {
    console.error("커밋 수 가져오기 실패:", error);
    document.getElementById("commit-count").textContent = "오류";
}
}

getCommitCount();

const form = document.getElementById('contact-form');

form.addEventListener('submit', function (e) {
  e.preventDefault(); // 기본 제출 방지

  const name = document.getElementById('name').value.trim();
  const email = document.getElementById('email').value.trim();
  const message = document.getElementById('message').value.trim();

  // 입력값이 비었는지 체크
  if (!name || !email || !message) {
    alert("모든 항목을 입력해주세요!");
    return;
  }

  // 확인창 보여주기
  const isConfirmed = confirm(
    `다음 내용으로 전송할까요?\n\n이름: ${name}\n이메일: ${email}\n메시지: ${message}`
  );

  // 사용자가 확인 누르면 실행
  if (isConfirmed) {
    alert("전송 완료! 확인 후 연락드리겠습니다! 감사합니다 :)");

    // 👉 실제 서버 전송 코드가 있다면 여기에 작성
    // fetch(...) 또는 form.submit()

    form.reset(); // 폼 초기화
  } else {
    alert("전송이 취소되었습니다.");
  }

  function isValidEmail(email) {
  return /\S+@\S+\.\S+/.test(email);
  }
});

const skillDescriptions = {
  "HTML5": "웹의 기본 구조와 시멘틱 태그 활용에 능숙하며, 접근성 좋은 마크업 작성이 가능합니다.",
  "CSS": "Flexbox, Grid, 반응형 디자인, 애니메이션 및 트랜지션 효과 구현에 숙련되어 있으며, CSS 변수와 모던 디자인 패턴 활용 가능합니다",
  "JavaScript": "JavaScript는 웹 페이지에 동적인 기능을 추가하는 스크립트 언어입니다.",
  "JAVA": "..",
};

function selectSkill(element, skillName) {
  const existingDetail = element.querySelector('.skill-detail');

  // 같은 태그 클릭 시: 닫기
  if (element.classList.contains('active')) {
    element.classList.remove('active');
    if (existingDetail) {
      existingDetail.classList.remove('show');
      setTimeout(() => {
        existingDetail.remove();
      }, 300); // transition 시간 후 삭제
    }
    return;
  }

  // 다른 열려 있는 태그 닫기
  document.querySelectorAll('.skill-tag.active').forEach(activeTag => {
    activeTag.classList.remove('active');
    const oldDetail = activeTag.querySelector('.skill-detail');
    if (oldDetail) {
      oldDetail.classList.remove('show');
      setTimeout(() => {
        oldDetail.remove();
      }, 300);
    }
  });

  // 새로운 상세 설명 추가
  const detailDiv = document.createElement('div');
  detailDiv.className = 'skill-detail';
  detailDiv.textContent = skillDescriptions[skillName] || "상세 설명이 없습니다.";

  element.appendChild(detailDiv);
  element.classList.add('active');

  // 브라우저 렌더링 이후 show 클래스 추가 → 애니메이션 작동
  setTimeout(() => {
    detailDiv.classList.add('show');
  }, 10);
}
