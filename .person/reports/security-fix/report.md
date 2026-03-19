# 🚩 작업 보고서: Spring Security 설정 오류 해결 (HttpSecurity 빈 누락)

- **작업 일시**: 2026-03-18
- **진행 단계**: 완료

## 1. 🌊 전체 작업 흐름 (Workflow)

1.  **문제 진단**: 애플리케이션 시작 시 `HttpSecurity` 빈을 찾을 수 없다는 오류(`BeanCreationException`)를 확인했습니다.
2.  **원인 분석**:
    - `build.gradle`에서 Spring Security 개별 라이브러리를 직접 사용하고 있어, Spring Boot의 자동 설정(Auto-configuration)이 정상적으로 동작하지 않음을 파악했습니다.
    - `SecurityConfig.java`에 `@EnableWebSecurity` 어노테이션이 누락되어 웹 보안 기능이 활성화되지 않았음을 확인했습니다.
3.  **해결 단계**:
    - `build.gradle`의 개별 시큐리티 의존성을 `spring-boot-starter-security` 통합 스타터로 교체했습니다.
    - `SecurityConfig.java` 클래스에 `@EnableWebSecurity` 어노테이션을 추가하여 보안 설정을 명시적으로 활성화했습니다.
4.  **검증**: 코드 수정을 통해 Spring Boot가 `HttpSecurity` 객체를 정상적으로 생성하고 주입할 수 있는 환경을 조성했습니다.

## 2. 🧩 변경된 모든 코드 포함

### [build.gradle]
- 개별 의존성 대신 `starter`를 사용하여 자동 설정을 지원하도록 수정했습니다.

```gradle
dependencies {
    // ... 기존 의존성 생략 ...
    
    // [변경 전] 개별 라이브러리 직접 명시
    // implementation 'org.springframework.security:spring-security-core:6.4.3'
    // implementation 'org.springframework.security:spring-security-web:6.4.3'
    // implementation 'org.springframework.security:spring-security-config:6.4.3'
    // implementation 'org.springframework.security:spring-security-crypto:6.4.3'

    // [변경 후] 시큐리티 스타터 추가 (자동 설정 지원)
    implementation 'org.springframework.boot:spring-boot-starter-security'
}
```

### [SecurityConfig.java]
- `@EnableWebSecurity`를 추가하여 웹 보안 컨텍스트를 활성화했습니다.

```java
package com.example.demo._core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity; // 추가된 임포트
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity // 핵심! 시큐리티 설정을 활성화합니다.
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // CSRF 비활성화 (H2-Console 및 폼 전송 테스트 편의성)
        http.csrf(csrf -> csrf.disable());

        // 모든 요청 허용 (현재는 테스트를 위해 전체 허용 설정)
        http.authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
        );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

## 3. 🍦 상세비유 쉬운 예시를 들어서 (Easy Analogy)

"이번 작업은 **'조립식 가구(IKEA) 세트'**를 주문하는 것과 같습니다.

기존에는 가구를 만들기 위해 나사 하나, 판자 하나를 따로따로 주문했는데(개별 라이브러리), 정작 가구를 조립하는 설명서(자동 설정)가 없어서 가구를 완성할 수 없었습니다.

이번 수정을 통해 '완성된 가구 조립 키트(Starter)'를 주문하고, 설명서에 따라 조립을 시작하라는 '명령(@EnableWebSecurity)'을 내린 것입니다. 이제 스프링이 알아서 필요한 부품(HttpSecurity)을 준비해 가구를 완성할 수 있게 되었습니다!"

## 4. 📚 기술 딥다이브 (Technical Deep-dive)

- **Spring Boot Starter Security**: 스프링 부트에서 제공하는 통합 패키지입니다. 단순히 라이브러리들을 모아놓은 것을 넘어, `SecurityAutoConfiguration`을 통해 시큐리티에 필요한 핵심 빈(Bean)들을 자동으로 생성해줍니다.
- **@EnableWebSecurity**: Spring Security의 웹 보안 지원을 활성화합니다. 이 어노테이션이 있어야 `SecurityFilterChain` 빈 정의 내에서 `HttpSecurity` 객체를 주입받아 세부적인 보안 정책(인증, 인가 등)을 설정할 수 있습니다.
- **HttpSecurity**: 개별 HTTP 요청에 대한 보안 설정을 담당하는 객체입니다. `authorizeHttpRequests`, `csrf`, `formLogin` 등의 메서드를 통해 애플리케이션의 문지기 역할을 정의합니다.
