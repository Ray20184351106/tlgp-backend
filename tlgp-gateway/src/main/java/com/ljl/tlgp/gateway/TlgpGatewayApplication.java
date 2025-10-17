package com.ljl.tlgp.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // 启用服务发现，网关需要知道后端服务的地址
public class TlgpGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(TlgpGatewayApplication.class, args);
    }
}
