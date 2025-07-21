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

const username = "Josua-12"; // ← 너의 GitHub ID로 바꿔줘!
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

    document.getElementById("commit-count").textContent = commitCount + "회";
} catch (error) {
    console.error("커밋 수 가져오기 실패:", error);
    document.getElementById("commit-count").textContent = "오류";
}
}

getCommitCount();