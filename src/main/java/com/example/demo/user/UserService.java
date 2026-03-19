package com.example.demo.user;

import com.example.demo.user.User;
import com.example.demo.user.UserRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입
     * 
     * @param joinDTO 가입 정보
     * @return 저장된 유저 엔티티
     */
    @Transactional
    public User join(UserRequest.JoinDTO joinDTO) {
        // 1. 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(joinDTO.password());

        // 2. 엔티티 변환
        User user = User.builder()
                .username(joinDTO.username())
                .password(encodedPassword)
                .email(joinDTO.email())
                .build();

        // 3. DB 저장
        return userRepository.save(user);
    }

    /**
     * 아이디 중복 체크
     * 
     * @param username 중복 확인 대상 아이디
     * @return 중복이면 true, 사용 가능하면 false 반환
     */
    public boolean checkUsername(String username) {
        var userOP = userRepository.findByUsername(username);
        return userOP.isPresent();
    }
}
