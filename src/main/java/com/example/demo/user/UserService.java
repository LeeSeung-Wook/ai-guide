package com.example.demo.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    /**
     * 아이디 중복 체크
     * @param username 중복 확인 대상 아이디
     * @return 중복이면 true, 사용 가능하면 false 반환
     */
    public boolean checkUsername(String username) {
        var userOP = userRepository.findByUsername(username);
        return userOP.isPresent();
    }
}
