
# Spring Boot CRUD API

 - [1️⃣ 주특기 입문 주차](https://github.com/eunsol-an/spring-boot-crud/tree/1week)
 - [2️⃣ 주특기 숙련 주차](https://github.com/eunsol-an/spring-boot-crud/tree/2week)
 - 3️⃣ 주특기 심화 주차
<br>

## 2️⃣ 주특기 숙련 주차
> 🚩 Goal: 회원가입, 로그인, 댓글 작성/조회/수정/삭제 기능이 추가된 나만의 항해 블로그 백엔드 서버 만들기

## 📝 목차
1. [서비스 완성 요구사항](#1-서비스-완성-요구사항)
2. [ERD](#2-ERD)
3. [API 명세서](#3-API-명세서)
4. [Trouble Shooting](#4-Trouble-Shooting)

## 1. 서비스 완성 요구사항

1.  회원 가입 API
    
    -   username, password를 Client에서 전달받기
    -   username은 `최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)`로 구성되어야 한다.
    -   password는 `최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9), 특수문자`로 구성되어야 한다.
    -   DB에 중복된 username이 없다면 회원을 저장하고 Client 로 성공했다는 메시지, 상태코드 반환하기
    -   회원 권한 부여하기 (ADMIN, USER) - ADMIN 회원은 모든 게시글, 댓글 수정 / 삭제 가능

2.  로그인 API
    
    -   username, password를 Client에서 전달받기
    -   DB에서 username을 사용하여 저장된 회원의 유무를 확인하고 있다면 password 비교하기
    -   로그인 성공 시, 로그인에 성공한 유저의 정보와 JWT를 활용하여 토큰을 발급하고, 발급한 토큰을 Header에 추가하고 성공했다는 메시지, 상태코드 와 함께 Client에 반환하기
3.  댓글 작성 API
    
    -   토큰을 검사하여, 유효한 토큰일 경우에만 댓글 작성 가능
    -   선택한 게시글의 DB 저장 유무를 확인하기
    -   선택한 게시글이 있다면 댓글을 등록하고 등록된 댓글 반환하기
4.  댓글 수정 API
    
    -   토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 댓글만 수정 가능
    -   선택한 댓글의 DB 저장 유무를 확인하기
    -   선택한 댓글이 있다면 댓글 수정하고 수정된 댓글 반환하기
5.  댓글 삭제 API
    
    -   토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 댓글만 삭제 가능
    -   선택한 댓글의 DB 저장 유무를 확인하기
    -   선택한 댓글이 있다면 댓글 삭제하고 Client 로 성공했다는 메시지, 상태코드 반환하기
6.  예외 처리
    
    -   토큰이 필요한 API 요청에서 토큰을 전달하지 않았거나 정상 토큰이 아닐 때는 "토큰이 유효하지 않습니다." 라는 에러메시지와 statusCode: 400을 Client에 반환하기
    -   토큰이 있고, 유효한 토큰이지만 해당 사용자가 작성한 게시글/댓글이 아닌 경우에는 “작성자만 삭제/수정할 수 있습니다.”라는 에러메시지와 statusCode: 400을 Client에 반환하기
    -   DB에 이미 존재하는 username으로 회원가입을 요청한 경우 "중복된 username 입니다." 라는 에러메시지와 statusCode: 400을 Client에 반환하기
    -   로그인 시, 전달된 username과 password 중 맞지 않는 정보가 있다면 "회원을 찾을 수 없습니다."라는 에러메시지와 statusCode: 400을 Client에 반환하기


## 2. ERD
![CRUD_project](https://user-images.githubusercontent.com/87196958/206372026-378bc05c-7d1c-4558-a826-e89f17ea8114.png)


## 3. API 명세서

[👉Postman API 명세서👈](https://documenter.getpostman.com/view/24640515/2s8YzQXPma)

[👉Notion API 명세서👈](https://eunsolan.notion.site/07076902016d4124848a7ef9251567ae?v=943fe4a9488c49c2aa592d10cdf2a803)

## 4. Trouble Shooting

> 진행하면서 겪은 에러와 궁금증들을 정리해놓은 개인 블로그입니다.

[🌱Spring Boot로 CRUD API 만들기 [회원가입/로그인]](https://oneul-losnue.tistory.com/270)
- 인증과 인가는 뭐가 다른 거지?
- @Value의 사용법
- save()와 saveAndFlush()는 어떻게 다를까?

[🌱Spring Boot로 CRUD API 만들기 [댓글 API]](https://oneul-losnue.tistory.com/271)
- 짚고 넘어가자 Optional
- 연관관계 매핑.. 그거 어떻게 하는건데?

[🌱Spring Boot로 CRUD API 만들기 [권한부여/예외처리]](https://oneul-losnue.tistory.com/274)
- 댓글을 작성 순으로 정렬하려면?
- 댓글이 달린 게시글이 삭제가 안되잖아?!
- 회원에게 권한을 부여해보자
- 예외처리를 기깔나게 할 수 없을까?
- ResponseEntity도 적용해봐야지?
