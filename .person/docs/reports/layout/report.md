# 작업 보고서: 공통 화면 레이아웃 구성 (T-1.5)

## 1. 작업 개요
- **작업명**: 공통 화면 레이아웃 구성 및 디자인 시스템 초기화
- **완료 일자**: 2026-03-16
- **주요 목적**: Mustache partials를 활용한 Header/Footer 공통화 및 Bootstrap 기반의 디자인 시스템 구축

## 2. 변경 사항

### 2.1 디자인 규칙 정의
- `.ai/rules/design-system.md` 파일을 생성하여 프로젝트 전반의 디자인 토큰(색상, 간격, 그림자 등)을 정의함.
- **브랜드 컬러**: `#197fe6` (Primary)
- **폰트**: `Pretendard`

### 2.2 템플릿 구조화
- `src/main/resources/templates/layout/` 디렉토리를 생성하고 공통 파일을 분리함.
  - `header.mustache`: Bootstrap 5.3 CDN, Custom CSS Tokens, Navigation Bar 포함
  - `footer.mustache`: Bootstrap JS, Footer 정보, Container 닫기 태그 포함

### 2.3 기존 페이지 리팩토링
- `home.mustache`: 기존의 독립적인 HTML 구조를 제거하고 `{{> layout/header}}`, `{{> layout/footer}}`를 적용하여 레이아웃을 통합함.
- Bootstrap Card 컴포넌트를 활용하여 메인 화면 UI 개선.

## 3. 향후 계획
- Phase 2 진행 시 회원가입(`join-form.mustache`) 및 로그인(`login-form.mustache`) 화면 생성 시 본 레이아웃을 재사용 예정.
- 디자인 토큰을 활용한 추가 컴포넌트(버튼, 입력창 등) 스타일 확장.
