package com.springreactsecurity.domain.member;

import com.springreactsecurity.common.BaseTimeEntity;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long id;

    @Column
    private String email;

    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

}
