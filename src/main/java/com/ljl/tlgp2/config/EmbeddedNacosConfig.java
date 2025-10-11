//package com.ljl.tlgp2.config;
//
//
//import jakarta.annotation.PostConstruct;
//import jakarta.annotation.PreDestroy;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//
//
//@Component
//@Slf4j
//public class EmbeddedNacosConfig {
//
//    private NacosEmbedded nacosEmbedded;
//
//    @PostConstruct
//    public void startEmbeddedNacos() {
//        try {
//            log.info("🚀 Starting embedded Nacos server...");
//
//            // 构建并启动内嵌 Nacos 服务
//            nacosEmbedded = Nacos.builder()
//                    .port(8848)           // Nacos 端口
//                    .build()
//                    .embed();
//
//            log.info("✅ Embedded Nacos Server started successfully on port 8848");
//            log.info("🌐 Access Nacos console at: http://localhost:8848/nacos");
//            log.info("🔑 Default credentials: nacos / nacos");
//
//        } catch (Exception e) {
//            log.error("❌ Failed to start embedded Nacos", e);
//            throw new RuntimeException("Failed to start embedded Nacos", e);
//        }
//    }
//
//    @PreDestroy
//    public void stopEmbeddedNacos() {
//        if (nacosEmbedded != null) {
//            try {
//                nacosEmbedded.close();
//                log.info("✅ Embedded Nacos Server stopped successfully");
//            } catch (Exception e) {
//                log.error("❌ Error stopping embedded Nacos", e);
//            }
//        }
//    }
//}