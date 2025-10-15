package com.ljl.tlgp.user.entity;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;


import java.time.LocalDateTime;


@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    // 方案1：使用UUID（推荐）
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(columnDefinition = "CHAR(36)")
    private String user_id;

    @Column(unique = true)
    private String username;

    private String password;


    @Column(name = "refresh_token", length = 500)
    private String refreshToken;

    @Column(name = "refresh_token_expiry")
    private LocalDateTime refreshTokenExpiry;
}