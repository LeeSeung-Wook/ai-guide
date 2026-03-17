# 🚩 작업 보고서: T-2.1 회원가입 Step 1 (AJAX 중복 체크)

- **작업 일시**: 2026-03-17
- **진행 단계**: 완료

## 1. 🌊 전체 작업 흐름 (Workflow)
1. **백엔드 로직 구현**: `UserService`에 `checkUsername` 메서드를 추가하여 DB에서 아이디 존재 여부를 확인하는 기능을 만들었습니다.
2. **API 엔드포인트 생성**: `UserApiController`에 `/api/username-check` 경로를 생성하여 프론트엔드와 통신할 수 있는 창구를 마련했습니다.
3. **화면 이동 컨트롤러 추가**: `UserController`에 `/join-form` 경로를 추가하여 회원가입 페이지로 접근할 수 있게 했습니다.
4. **프론트엔드 구현**: `join-form.mustache` 파일을 생성하고 Fetch API를 사용하여 실시간으로 아이디 중복을 체크하는 기능을 구현했습니다.

## 2. 🧩 핵심 코드 (Core Logic)

### UserApiController.java (API 응답)
```java
@GetMapping("/api/username-check")
public ResponseEntity<?> usernameCheck(@RequestParam("username") String username) {
    // 서비스에 중복 여부를 물어보고 결과를 Resp.ok()에 담아 보냅니다.
    var isDuplicate = userService.checkUsername(username);
    return Resp.ok(isDuplicate);
}
```

### join-form.mustache (AJAX 통신)
```javascript
async function usernameCheck() {
    const username = document.querySelector("#username").value;
    // 서버의 API를 호출하여 결과를 기다립니다 (비동기 통신)
    const response = await fetch(`/api/username-check?username=${username}`);
    const result = await response.json();

    if (result.body === true) {
        // 중복된 경우 메시지 표시 및 버튼 비활성화
        msgElement.innerHTML = "<span class='text-danger'>이미 존재하는 아이디입니다.</span>";
    } else {
        // 사용 가능한 경우 다음 단계 활성화
        msgElement.innerHTML = "<span class='text-success'>사용 가능한 아이디입니다.</span>";
    }
}
```

## 3. 🍦 상세비유 쉬운 예시를 들어서 (Easy Analogy)
"이번 작업은 **도서관 대출증 만들기**와 같습니다. 
새로 대출증을 만들러 온 사람에게 '이 이름으로 이미 등록된 사람이 있는지' 사서 선생님(서버)에게 물어보는 과정이에요. 
사서 선생님이 장부를 확인해서 '안 돼요, 이미 있어요'라고 하면 다른 이름을 써야 하고, '괜찮아요'라고 하면 다음 칸(비밀번호, 이메일)을 채울 수 있게 해주는 것과 같습니다!"

## 4. 📚 기술 딥다이브 (Technical Deep-dive)
- **AJAX (Asynchronous JavaScript and XML)**: 페이지 전체를 새로고침하지 않고도 배경에서 서버와 데이터를 주고받는 기술입니다. 여기서는 아이디를 입력하고 버튼을 눌렀을 때 화면이 깜빡이지 않고 중복 여부만 확인하는 데 사용되었습니다.
- **Fetch API**: AJAX를 구현하기 위한 현대적인 자바스크립트 방식입니다. `await`와 함께 사용하여 마치 순서대로 실행되는 코드처럼 깔끔하게 작성할 수 있습니다.
- **DTO (Data Transfer Object)**: 계층 간에 데이터를 전달하기 위한 객체입니다. 여기서는 `Resp` 클래스가 그 역할을 하여 성공 여부와 데이터를 일관된 형식으로 전달합니다.
