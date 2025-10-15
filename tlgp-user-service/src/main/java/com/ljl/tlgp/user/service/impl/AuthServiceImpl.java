package com.ljl.tlgp.user.service.impl;

import com.ljl.tlgp.user.JwtProperties;
import com.ljl.tlgp.user.dto.LoginResponse;
import com.ljl.tlgp.user.entity.User;
import com.ljl.tlgp.user.repository.UserRepository;
import com.ljl.tlgp.user.security.JwtTokenProvider;
import com.ljl.tlgp.user.service.AuthService;
import com.ljl.tlgp.user.util.PasswordEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    // 注册
    @Autowired
    private PasswordEncoderUtil passwordEncoder;

    // 登录
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public String register(String username, String password) {
        if (userRepository.existsByUsername(username)) {
            return "Username exists";
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password)); // 加密存储
        userRepository.save(newUser);
        return "Registration success";
    }

    @Override
public ResponseEntity<?> login(String username, String password) {
    // 1. 验证用户凭证
    Optional<User> userOpt = userRepository.findByUsername(username);
    if (userOpt.isEmpty() || !passwordEncoder.matches(password, userOpt.get().getPassword())) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户名或密码错误");
    }
    // 2.生成双令牌
    User user = userOpt.get();
    String accessToken = jwtTokenProvider.createToken(username);
    String refreshToken = jwtTokenProvider.createRefreshToken(username);

    // 存储刷新令牌到数据库
    user.setRefreshToken(refreshToken);
    user.setRefreshTokenExpiry(
            LocalDateTime.now().plusSeconds(jwtProperties.getRefreshTokenExpiration()/1000)
    );
    userRepository.save(user);

    // 4. 返回响应
    return ResponseEntity.ok(
            new LoginResponse(
                    accessToken,
                    refreshToken,
                    jwtProperties.getAccessTokenExpiration() / 1000,
                    "200"// 前端需要的秒数
            )
    );
}
}
