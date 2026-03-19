package com.example.demo.user;

import com.example.demo.user.UserRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final HttpSession session;

    /**
     * 회원가입 요청 처리 (POST)
     */
    @PostMapping("/join")
    public String join(@Valid UserRequest.JoinDTO joinDTO, BindingResult br) {
        // AOP(ValidationAdvice)에서 BindingResult를 검증하여 에러 시 CustomValidationException을
        // 던짐
        userService.join(joinDTO);
        return "redirect:/login-form"; // 가입 완료 후 로그인 페이지로 리다이렉트 (추후 구현)
    }

    /**
     * 회원가입 페이지 이동
     */
    @GetMapping("/join-form")
    public String joinForm() {
        return "user/join-form";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "user/login-form";
    }

}
