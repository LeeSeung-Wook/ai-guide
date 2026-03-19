package com.example.demo.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

public class UserRequest {

    public record JoinDTO(
            @NotBlank(message = "아이디는 필수 입력값입니다.")
            @Size(min = 4, max = 20, message = "아이디는 4~20자 이내여야 합니다.")
            String username,

            @NotBlank(message = "비밀번호는 필수 입력값입니다.")
            @Size(min = 4, max = 20, message = "비밀번호는 4~20자 이내여야 합니다.")
            String password,

            @NotBlank(message = "이메일은 필수 입력값입니다.")
            @Email(message = "올바른 이메일 형식이 아닙니다.")
            String email
    ) {}

    @Data
    public static class Login {
        private String username;
        private String password;
    }

}
