# 회원가입 기능 구현 완료 보고서 (Step 2 ~ Finalize)

## 1. 작업 개요
- **작업명**: 회원가입 기능 및 화면 구현 (Phase 2: T-2.1)
- **완료 단계**: 
  - Step 2: AOP 유효성 검사 및 폼 데이터 처리
  - Step 3: BCrypt 비밀번호 암호화 및 저장
  - Finalize: 프론트엔드 디자인 개선 및 폼 전송 방식 전환

## 2. 주요 변경 사항

### 2.1 백엔드 (Spring Boot)
- **의존성 추가**: `spring-security-crypto`를 추가하여 안전한 암호화 환경 구축.
- **보안 설정**: `SecurityConfig`를 통해 `BCryptPasswordEncoder`를 빈으로 등록.
- **Service 구현**: `UserService.join`에서 비밀번호 해싱(Salting 포함) 로직 적용 및 DB 저장.
- **Controller 구현**: `UserController`에 `POST /join` 핸들러 추가. `@Valid`와 `BindingResult`를 활용하여 유효성 검사 수행.
- **예외 처리**: `GlobalExceptionHandler`를 신규 생성하여 유효성 검사 실패 시 사용자에게 JavaScript `alert`와 `history.back()`으로 응답하도록 공통 로직 구현.

### 2.2 프론트엔드 (Mustache & Bootstrap)
- **디자인 개선**: 부트스트랩 5를 활용하여 깔끔하고 모던한 카드 레이아웃의 회원가입 폼 설계.
- **통신 방식 전환**: 프로젝트 규칙에 따라 전체 가입 프로세스를 AJAX에서 표준 **폼 전송(POST)** 방식으로 전환.
- **중복 체크**: 아이디 중복 확인은 사용자 경험(UX)을 위해 AJAX 방식을 유지하며, 확인 전까지 다른 필드와 가입 버튼을 비활성화하는 방어적 로직 적용.

## 3. 학습 포인트 및 문서화
- **BCrypt & Salting**: 해시 암호화의 단방향성과 솔팅의 원리를 학습하고 `.person/docs/notion/bcrypt-salting.md`에 정리 완료.
- **AOP Validation**: `ValidationAdvice`를 통해 컨트롤러의 공통 유효성 검사 로직이 어떻게 동작하는지 실습.

## 4. 향후 계획
- **T-2.2**: 구현된 유저 정보를 바탕으로 세션 방식의 로그인/로그아웃 기능 구현 예정.
