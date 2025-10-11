package com.ljl.tlgp2.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
@RefreshScope // 启用配置动态刷新
public class TestController {

    @Value("${app.name:Default App Name}") // 优先从 Nacos 配置中心加载
    private String appName;

    @Value("${app.version:1.0.0-default}")
    private String appVersion;

    @Value("${database.url:jdbc:h2:mem:testdb}") // 默认值
    private String dbUrl;

    @Value("${server.port:8080}")
    private String port;

    @GetMapping("/info")
    public Map<String, Object> getServiceInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("message", "Hello from tlgp2-service!");
        info.put("serviceName", "tlgp2-service");
        info.put("port", port);
        info.put("configAppName", appName); // 从 Nacos 配置中心加载的值
        info.put("configAppVersion", appVersion); // 从 Nacos 配置中心加载的值
        info.put("configDbUrl", dbUrl); // 从 Nacos 配置中心加载的值
        info.put("timestamp", System.currentTimeMillis());
        return info;
    }
}
