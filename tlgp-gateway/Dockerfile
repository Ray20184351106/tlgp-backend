# 使用 Java 8 基础镜像
FROM openjdk:17-jre-alpine
# 维护者信息
LABEL maintainer="ljl@example.com"
# 将本地 jar 包复制到容器中
COPY ./target/tlgp-gateway-1.0.0.jar app.jar
# 暴露服务端口 (根据你的应用实际端口修改)
EXPOSE 9000
# 启动命令
ENTRYPOINT ["java", "-jar", "/app.jar"]