package com.springreactsecurity.core.security;

import com.springreactsecurity.domain.member.Member;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class UserMember extends User {

    private final Member member;

    public UserMember(Member member) {
        super(member.getUserId(), member.getUserPassword(), List.of(new SimpleGrantedAuthority(member.getRole().getKey())));
        this.member = member;
    }

}
