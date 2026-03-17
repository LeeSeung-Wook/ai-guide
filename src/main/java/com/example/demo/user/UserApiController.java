package com.example.demo.user;

import com.example.demo._core.utils.Resp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;
    private final HttpSession session;

    /**
     * 아이디 중복 체크 API
     * @param username 중복 확인 대상 아이디
     * @return 중복 여부 (true: 중복, false: 사용 가능)
     */
    @GetMapping("/api/username-check")
    public ResponseEntity<?> usernameCheck(@RequestParam("username") String username) {
        var isDuplicate = userService.checkUsername(username);
        return Resp.ok(isDuplicate);
    }

}
