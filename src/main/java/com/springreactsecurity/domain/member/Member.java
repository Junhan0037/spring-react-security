package com.springreactsecurity.domain.member;

import com.springreactsecurity.core.common.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long id;

    @Column
    private String userId;

    @Column
    private String userPassword;

    @Column
    private String name;

    @Column
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

}
