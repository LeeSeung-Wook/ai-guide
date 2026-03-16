# Code Rules

## 1. Naming Conventions

### 1.1 Templates (Mustache)
- Mustache 템플릿 파일명은 **kebab-case** (하이픈 사용)를 사용한다.
  - 예: `join-form.mustache`, `board-detail.mustache`, `user-list.mustache`
  - `joinForm.mustache`와 같은 camelCase나 concatenated names는 지양한다.

### 1.2 Java Packages & Classes
- 패키지명은 소문자로 구성한다.
- 클래스명은 **PascalCase**를 사용한다.
- 변수 및 메서드명은 **camelCase**를 사용한다.

### 1.3 DTO (Data Transfer Object)
- 요청 DTO는 `Request` 접미사를 사용하거나 내부 클래스(`JoinDTO`, `LoginDTO`)로 관리한다.
- 응답 DTO는 `Response` 접미사를 사용하거나 내부 클래스로 관리한다.
- 구조는 가능한 한 **평탄한 구조(Flat DTO)**를 유지한다.

## 2. Java & Spring Boot Guidelines
- **Java 21** 문법을 적극 활용한다 (`var`, `record`, `Switch Expressions` 등).
- **Spring Boot 3.x** 기능을 활용한다.
- 모든 중요한 로직에는 **한글 주석**을 작성한다.
- 불필요한 임포트 및 중복 코드는 제거한다.
- `Lombok` 어노테이션(`@Getter`, `@Setter`, `@NoArgsConstructor` 등)을 적절히 활용한다.
- 엔티티와 DTO는 엄격히 분리하여 사용한다.

## 3. Architecture
- **Controller-Service-Repository** 계층 구조를 준수한다.
- 공통 응답 규격인 `Resp<T>`를 사용하여 결과를 반환한다.
