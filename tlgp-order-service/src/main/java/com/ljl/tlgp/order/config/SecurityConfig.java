package com.ljl.tlgp.order.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF 配置方式改变
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/**").permitAll() // 替代 antMatchers
                        .anyRequest().authenticated()
                )
                .httpBasic(httpBasic -> {}); // HTTP Basic 认证配置
        
        return http.build();
    }
}
