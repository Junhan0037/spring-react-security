package com.springreactsecurity.domain.member;

import com.springreactsecurity.domain.member.dto.MemberDto;

public interface MemberService {

    boolean signUp(MemberDto.signUpForm signUpForm);

}
