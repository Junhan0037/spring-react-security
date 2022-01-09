package com.springreactsecurity.domain.member;

import com.springreactsecurity.domain.member.dto.MemberRequestDto;
import com.springreactsecurity.domain.member.dto.MemberResponseDto;

public interface MemberService {

    MemberResponseDto.memberForm signUp(MemberRequestDto.signUpForm signUpForm);

    String findId(MemberRequestDto.findIdForm findIdForm);

    String findPassword(MemberRequestDto.findPasswordForm findPasswordForm);

}
