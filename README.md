# Spring Boot CRUD API

> 🚩 Goal : 스프링 부트로 로그인 기능이 없는 나만의 항해 블로그 백엔드 서버 만들기

## 📝 목차
1. [서비스 완성 요구사항](#1-서비스-완성-요구사항)
2. [Use Case](#2-Use-Case)
3. [ERD](#3-ERD)
4. [API 명세서](#4-API-명세서)
5. [Trouble Shooting](#5-Trouble-Shooting)

## 1. 서비스 완성 요구사항

1.  아래의 요구사항을 기반으로 Use Case 그려보기
2.  전체 게시글 목록 조회 API
    -   제목, 작성자명, 작성 내용, 작성 날짜를 조회하기
    -   작성 날짜 기준 내림차순으로 정렬하기
3.  게시글 작성 API
    -   제목, 작성자명, 비밀번호, 작성 내용을 저장하고
    -   저장된 게시글을 Client 로 반환하기
4.  선택한 게시글 조회 API
    -   선택한 게시글의 제목, 작성자명, 작성 날짜, 작성 내용을 조회하기
5.  선택한 게시글 수정 API
    -   수정을 요청할 때 수정할 데이터와 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후
    -   제목, 작성자명, 작성 내용을 수정하고 수정된 게시글을 Client 로 반환하기
6.  선택한 게시글 삭제 API
    -   삭제를 요청할 때 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후
    -   선택한 게시글을 삭제하고 Client 로 성공했다는 표시 반환하기

## 2. Use Case

![Hanghae99_CURD_usecase_diagram drawio](https://user-images.githubusercontent.com/87196958/204817775-f64c260a-6b82-4843-aa6b-8d8b04fc6383.png)

## 3. ERD

![Hanghae99_CURD_erd](https://user-images.githubusercontent.com/87196958/204818257-a5b6ab7a-6870-40cf-b6f8-b8a8b1f910bb.png)

## 4. API 명세서

[👉Postman API 명세서👈](https://documenter.getpostman.com/view/24640515/2s8Yt1qomh)

## 5. Trouble Shooting

> 진행하면서 겪은 에러와 궁금증들을 정리해놓은 개인 블로그입니다.

[🌱Spring Boot로 CRUD API 만들기 1편](https://oneul-losnue.tistory.com/259)
- @RequestBody vs @ModelAttribute
- Entity vs VO vs DTO
- Spring Data JPA Query Methods
- 왜 update 할 때는 Repository를 거치지 않는 거지?
- 왜 long이 아닌 Long일까?

[🌱Spring Boot로 CRUD API 만들기 2편](https://oneul-losnue.tistory.com/261)
- 왜 createdAt, modifiedAt 값이 null로 전달될까?
- @Transactional 없이 update 하기.. 가능할까?
- 성공 여부를 어떻게 반환하면 좋을까?
