package com.springreactsecurity.domain.member;

import com.springreactsecurity.core.common.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long id;

    private String userId;

    private String userPassword;

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean emailVerified = false;

    private String emailCheckToken;

    private LocalDateTime emailCheckTokenGeneratedAt;

    public void generateEmailCheckToken() {
        this.emailCheckToken = UUID.randomUUID().toString();
        this.emailCheckTokenGeneratedAt = LocalDateTime.now();
    }

    public void completeSignUp() {
        this.emailVerified = true;
    }

    public boolean isValidToken(String token) {
        return this.emailCheckToken.equals(token);
    }

}
