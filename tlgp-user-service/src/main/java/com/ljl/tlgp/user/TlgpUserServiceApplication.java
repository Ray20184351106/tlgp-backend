package com.ljl.tlgp.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties(JwtProperties.class) // 添加此注解
public class TlgpUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TlgpUserServiceApplication.class, args);
    }

}
