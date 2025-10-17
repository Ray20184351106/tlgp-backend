package com.ljl.tlgp.order.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    //用于解决跨域问题

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Enable CORS and set configuration source
                .csrf(csrf -> csrf.disable()) // CSRF 配置方式改变
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/**").permitAll() // 替代 antMatchers
                        .requestMatchers("/api/firms/**").permitAll() // Actuator 监控接口
                        .requestMatchers("/firms/**").permitAll() // Actuator 监控接口
                        .anyRequest().authenticated()
                )
                .httpBasic(httpBasic -> {}); // HTTP Basic 认证配置

        return http.build();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("http://localhost:5173")); // Allow your frontend origin
        // 或者使用 configuration.setAllowedOrigins(List.of("http://localhost:5173")); 如果你确定前端地址是固定的
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allow common methods
        configuration.setAllowedHeaders(List.of("*")); // Allow all headers
        configuration.setAllowCredentials(true); // Allow credentials (e.g., cookies, authorization headers)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Apply to all paths
        return source;
    }
}
