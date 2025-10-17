package com.ljl.tlgp.user.controller;

import com.ljl.tlgp.user.JwtProperties;
import com.ljl.tlgp.user.dto.LoginResponse;
import com.ljl.tlgp.user.dto.RefreshRequest;
import com.ljl.tlgp.user.entity.User;
import com.ljl.tlgp.user.repository.UserRepository;
import com.ljl.tlgp.user.security.JwtTokenProvider;
import com.ljl.tlgp.user.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtProperties jwtProperties;

    // 注册接口
    @PostMapping("/register")
    public String register(
            @RequestParam("username") String username,  // 明确指定参数名
            @RequestParam("password") String password
    ) {
        return authService.register(username, password);
    }

    // 登录接口
    @PostMapping("/login")
    public ResponseEntity login(@RequestParam String username,
                                   @RequestParam String password) {
        return authService.login(username, password);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshRequest request) {
        try {
            // 验证刷新令牌有效性

            if (!jwtTokenProvider.validateToken(request.getRefreshToken())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("无效的刷新令牌");
            }

            // 获取用户信息
            String username = jwtTokenProvider.getUsername(request.getRefreshToken());
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("用户不存在"));

            // 校验数据库中的令牌
            if (!user.getRefreshToken().equals(request.getRefreshToken()) ||
                    user.getRefreshTokenExpiry().isBefore(LocalDateTime.now())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("令牌已过期");
            }
            // 生成新令牌
            String newAccessToken = jwtTokenProvider.createToken(username);
            String newRefreshToken = jwtTokenProvider.createRefreshToken(username);
            Long expiration = jwtProperties.getExpiration();

            // 更新数据库
            user.setRefreshToken(newRefreshToken);
            user.setRefreshTokenExpiry(
                    LocalDateTime.now().plusSeconds(jwtProperties.getRefreshTokenExpiration() / 1000)
            );
            userRepository.save(user);

            return ResponseEntity.ok(new LoginResponse(newAccessToken, newRefreshToken,expiration,"200"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("服务器错误");
        }
    }
}