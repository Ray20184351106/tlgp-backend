package com.ljl.tlgp.user.service;

import com.ljl.tlgp.user.JwtProperties;
import com.ljl.tlgp.user.entity.User;
import com.ljl.tlgp.user.repository.UserRepository;
import com.ljl.tlgp.user.security.JwtTokenProvider;
import com.ljl.tlgp.user.dto.LoginResponse;
import com.ljl.tlgp.user.util.PasswordEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public interface AuthService {

    public String register(String username, String password);

    public ResponseEntity<?> login(String username, String password);

}
