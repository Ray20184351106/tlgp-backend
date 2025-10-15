package com.ljl.tlgp.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
public class LoginResponse {
//    private String username;
//    private String token;
//
//    public LoginResponse(String username, String token) {
//        this.username = username;
//        this.token = token;
//    }
    private String accessToken;
    private String refreshToken;
    private long expiresIn; // 令牌有效期（秒）
    private  String tokenType = "Bearer";
    private String code;

    public LoginResponse(String accessToken, String refreshToken, long expiresIn, String code) {
        this(accessToken, refreshToken, expiresIn, "Bearer",code);
    }


}
