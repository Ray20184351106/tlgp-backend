package com.ljl.tlgp.order;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "app.jwt")
@Component // 将这个类注册为 Spring Bean
@Data
public class JwtProperties {
    private String secret;
    private long expiration;
    private long accessTokenExpiration;
    private long refreshTokenExpiration;

}
