var heading = document.getElementById("heading");
    /*  -----------   -----------------------------------
       l(left) value|          r(right) value            */
       /* onclick = funtion () : 클릭했을 때 실행할 함수 정의 */
        heading.onclick = function () {
            /* style.color : 글자와 색상을 바꾸는 속성 */
            /* if 조건문 : 현재 색이 'red'이면 파란색으로 바꾸기 */
            if(heading.style.color == "red") {
                heading.style.color = "blue";
            }
            /* 그 외에는 빨간색으로 바꾸기 */
            else {
                heading.style.color = "red";
            }
        };