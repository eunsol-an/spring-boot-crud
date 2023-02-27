
# Spring Boot CRUD API

 - [1️⃣ 주특기 입문 주차](https://github.com/eunsol-an/spring-boot-crud/tree/1week)
 - [2️⃣ 주특기 숙련 주차](https://github.com/eunsol-an/spring-boot-crud/tree/2week)
 - 3️⃣ 주특기 심화 주차
<br>

## 3️⃣ 주특기 심화 주차
> 🚩 Goal: "Spring Security를 적용한 나만의 항해 블로그 백엔드 서버 만들기"

## 📝 목차
1. [서비스 완성 요구사항](#1-서비스-완성-요구사항)
2. [ERD](#2-ERD)
3. [API 명세서](#3-API-명세서)
4. [Trouble Shooting](#4-Trouble-Shooting)
5. [Git-flow 전략](#5-Git-flow-전략)


## 1. 서비스 완성 요구사항
1.  숙련주차 개인과제 LV2 프로젝트에 Spring Security 적용하기
2.  게시글 좋아요 API
    -   사용자는 선택한 게시글에 ‘좋아요’를 할 수 있습니다.
    -   사용자가 이미 ‘좋아요’한 게시글에 다시 ‘좋아요’ 요청을 하면 ‘좋아요’를 했던 기록이 취소됩니다.
    -   요청이 성공하면 Client 로 성공했다는 메시지, 상태코드 반환하기
    -   게시글/댓글에 ‘좋아요’ 개수도 함께 반환하기
3.  댓글 좋아요 API
    -   사용자는 선택한 댓글에 ‘좋아요’를 할 수 있습니다.
    -   사용자가 이미 ‘좋아요’한 댓글에 다시 ‘좋아요’ 요청을 하면 ‘좋아요’를 했던 기록이 취소됩니다.
    -   요청이 성공하면 Client 로 성공했다는 메시지, 상태코드 반환하기
    -   댓글에 ‘좋아요’ 개수도 함께 반환하기
4.  예외처리
    -   아래 예외처리를 AOP 를 활용하여 구현하기
    -   토큰이 필요한 API 요청에서 토큰을 전달하지 않았거나 정상 토큰이 아닐 때는 "토큰이 유효하지 않습니다." 라는 에러메시지와 statusCode: 400을 Client에 반환하기
	-   토큰이 있고, 유효한 토큰이지만 해당 사용자가 작성한 게시글/댓글이 아닌 경우에는 “작성자만 삭제/수정할 수 있습니다.”라는 에러메시지와 statusCode: 400을 Client에 반환하기
	-   DB에 이미 존재하는 username으로 회원가입을 요청한 경우 "중복된 username 입니다." 라는 에러메시지와 statusCode: 400을 Client에 반환하기
	-   로그인 시, 전달된 username과 password 중 맞지 않는 정보가 있다면 "회원을 찾을 수 없습니다."라는 에러메시지와 statusCode: 400을 Client에 반환하기
	-   회원가입 시 username과 password의 구성이 알맞지 않으면 에러메시지와 statusCode: 400을 Client에 반환하기

## 2. ERD
### 🤔 프로젝트 계획 단계 ERD
![ERD-before](https://user-images.githubusercontent.com/87196958/221635774-c924efbd-3faa-4f39-940b-ef1b934e4174.png)

### 🧐 프로젝트 구현 ERD

![ERD](https://user-images.githubusercontent.com/87196958/207775521-5484b05c-66fa-4a8e-bf75-326bab3cf256.png)

- post → board로 변경
- 식별/비식별 관계 재정의
- 좋아요 개수 field 삭제(다대일 양방향 매핑으로 필요X)



## 3. API 명세서

![api](https://user-images.githubusercontent.com/87196958/207782113-1c349642-93ca-49ba-bc8d-a9133e306684.png)

[📬 Postman API 명세서](https://documenter.getpostman.com/view/24643348/2s8YzWT1ig)


## 4. Trouble Shooting

> 진행하면서 겪은 에러와 궁금증들을 정리해놓은 개인 블로그입니다.

[🌱Spring Boot로 CRUD API 만들기 [Spring Security]](https://oneul-losnue.tistory.com/277)
- Spring Security 적용 4단계
- CustomSecurityFilter vs jwtAuthFilter
- jwtAuthFilter 흐름

[🌱Spring Boot로 CRUD API 만들기 [게시글 좋아요 API]](https://oneul-losnue.tistory.com/282)
- 게시글 좋아요 개수를 어떻게 출력하면 좋을까?
- AUTO vs IDENTITY

[🌱Spring Boot로 CRUD API 만들기 [댓글 좋아요 API]](https://oneul-losnue.tistory.com/283)
- 댓글 좋아요 API는 어떻게 구현할까?
- 좋아요 기능의 controller를 합치는 게 좋을지?

[🌱Spring Boot로 CRUD API 만들기 [예외처리]](https://oneul-losnue.tistory.com/284)
- @Valid에 대한 예외처리는 어떻게 해야 할까?
- 좋아요 로직 한 번에 insert와 delete 해도 괜찮은지?


## 5. Git-flow 전략
![git-flow](https://velog.velcdn.com/images/ssol_916/post/63a1ec26-777d-4346-91a5-f94ae102bbc2/image.webp)
|                |내용                          |
|----------------|------------------------------|
|main(master)    |제품으로 출시될 수 있는 브랜치   |
|develop         |다음 출시 버전을 개발하는 브랜치 |
|feature         |기능을 개발하는 브랜치          |
