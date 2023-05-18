<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Insert title here</title>
    </head>

    <body>

        이름 : <input type="text" name="name"> <br>

        나이 : <input type="text" name="age"> <br>

        취미 :
        <input type="checkbox" name="hobby" value="soccer"> 축구
        <input type="checkbox" name="hobby" value="music"> 음악 감상
        <input type="checkbox" name="hobby" value="game"> 게임

        <button id="send">요청 보내기!</button>

        <script>

            const $sendBtn = document.getElementById('send');

            $sendBtn.onclick = (e => {

                const name = document.querySelector('input[name=name]').value;
                const age = document.querySelector('input[name=age]').value;
                const hobby = document.querySelectorAll('input[name=hobby]').value;

                console.log(name);
                console.log(age);
                console.log(hobby);

                // # http 요청을 서버로 보내는 방법
                // 1. XMLHttpRequest 객체를 생성
                const xhr = new XMLHttpRequest();

                /*
                    2. http 요청 설정 (요청 방식, 요청 URL)

                    - 요청 방식
                    a. GET      - 조회
                    b. POST     - 등록
                    c. PUT      - 수정
                    d. DELETE   - 삭제
                */
                xhr.open('GET', '${pageContext.request.contextPath}/rest/getObject');
            });

        </script>

    </body>

    </html>