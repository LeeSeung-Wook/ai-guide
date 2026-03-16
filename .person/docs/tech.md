---
title: 기술 스택 명세서 (Tech Stack) - 심플 다중 사용자 블로그
source_spec: .person/docs/spec.md
last_updated: 2026-03-16
---

# 1. 목표

`.person/docs/spec.md`의 기능(회원/게시글/댓글, 권한, 비노출/탈퇴 처리, 페이징/검색)을 **Spring Boot 기반 REST API**로 구현하기 위한 기술 스택과 구현 선택지를 정의한다.

# 2. 런타임/언어/빌드

- Language: **Java 21**
- Framework: **Spring Boot 3.3.x** (Spring Framework 6.x)
- Build: **Gradle**
- Packaging: `jar`

# 3. API 계층

- API 스타일: **REST + JSON**
- Controller: Spring MVC (`spring-boot-starter-web`)
- 입력 검증: Bean Validation (`spring-boot-starter-validation`)
- 페이징: Spring Data `Pageable` 기반(게시글 목록/검색)

## 3.1 에러 응답(권장)

- 공통 에러 포맷을 일관되게 제공
  - 권장 1: Spring `ProblemDetail`(RFC 7807) 기반
  - 권장 2: 프로젝트 표준 JSON 에러 바디(코드/메시지/필드에러)

# 4. 인증/인가

`spec.md`에서 “로그인 상태 유지 방식”은 TBD이므로, 아래 중 하나를 선택해 **프로젝트 전역에서 단일 방식으로 통일**한다.

## 4.1 권장안: Spring Security + JWT

- 라이브러리:
  - Spring Security (`spring-boot-starter-security`)
  - JWT 라이브러리(택1): `jjwt` 또는 `java-jwt`
- 토큰 전달:
  - 권장: `Authorization: Bearer <access_token>`
  - 대안: HttpOnly Cookie(웹 중심일 때)
- 인가 규칙:
  - Guest: 조회/검색만 허용
  - Member: 글/댓글 작성, 본인 글/댓글 수정·삭제 허용

## 4.2 대안: 세션 기반 인증

- Spring Session(선택) + `HttpSession`
- 장점: 구현 단순
- 유의: 확장(서버 다중화) 시 세션 저장소 필요

# 5. 데이터/영속성

## 5.1 DB

- 개발/테스트: **H2**
- 운영(권장): **PostgreSQL** 또는 **MySQL**
  - (PRD/Spec에 운영 DB 요구가 없으므로 “권장” 수준으로만 정의)

## 5.2 ORM/쿼리

- ORM: Spring Data JPA (`spring-boot-starter-data-jpa`)
- 엔티티 설계(스펙 기반 최소 필드):
  - `User(status: ACTIVE/WITHDRAWN)`
  - `Post(status: ACTIVE/DELETED)`
  - `Comment(status: ACTIVE/DELETED)`
- 검색:
  - 1차: RDB `LIKE %q%` 기반(제목/본문)
  - 확장(선택): DB Full-Text Search 또는 Elasticsearch/OpenSearch (현재 스펙 범위 밖)

## 5.3 비노출(탈퇴/삭제) 처리 방식

스펙(5.1)의 “즉시 비노출” 요구를 만족하기 위해 **소프트 삭제(상태 컬럼)** 를 기본으로 한다.

- 게시글 삭제: `Post.status=DELETED`
- 댓글 삭제: `Comment.status=DELETED`
- 회원 탈퇴: `User.status=WITHDRAWN`
- 조회/목록/검색에서 항상 `status=ACTIVE` 조건을 적용
  - 구현 선택: Repository 쿼리 조건 일괄 적용 또는 전역 필터(@Where 등) 사용(팀 컨벤션에 맞춤)

## 5.4 마이그레이션(선택)

- Flyway 또는 Liquibase (운영 DB를 사용할 경우 권장)

# 6. 보안/비밀번호

- 비밀번호 저장: **BCrypt**(Spring Security 기본 권장)
- 민감 정보: `application.yml`에 직접 하드코딩 금지, 환경변수/시크릿 사용

# 7. 테스트

- Unit/Integration: JUnit 5 + Spring Boot Test
- Web 테스트(권장): MockMvc 또는 WebTestClient(선택)
- DB 테스트: H2(in-memory)

테스트 최소 범위(스펙 대응):
- 권한(Guest vs Member) 차단/허용
- 본인 리소스만 수정·삭제 가능(403/404 규칙)
- 탈퇴 시 작성 콘텐츠 즉시 비노출
- 페이징/정렬/검색 동작

# 8. 운영/관측(선택)

- Actuator: 헬스체크/메트릭(필요 시)
- Logging: SLF4J + Logback (Spring Boot 기본)

# 9. 문서화(선택)

- OpenAPI: springdoc-openapi로 API 스펙 자동화(선택)

# 10. 결정 필요(TBD)

아래는 `.person/docs/spec.md`의 TBD와 연결되는 기술 결정 항목이다.

- 인증 방식 최종 결정: **JWT vs 세션**
- 토큰/세션 저장 위치: Authorization 헤더 vs Cookie vs 서버 세션
- 운영 DB 선택: H2 외 운영 환경 DB 필요 여부
- 검색 규칙 상세(대소문자/공백/다중 키워드) 확정에 따른 쿼리/인덱스 전략
