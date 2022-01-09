package com.springreactsecurity.domain.member;

import com.springreactsecurity.domain.member.dto.MemberRequestDto;
import com.springreactsecurity.domain.member.dto.MemberResponseDto;

public interface MemberService {

    MemberResponseDto.signUpForm signUp(MemberRequestDto.signUpForm signUpForm);

}
