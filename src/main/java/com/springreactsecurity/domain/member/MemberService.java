package com.springreactsecurity.domain.member;

import com.springreactsecurity.domain.member.dto.MemberDto;

public interface MemberService {

    MemberDto.memberForm signUp(MemberDto.signUpForm signUpForm);

    String findId(MemberDto.findIdForm findIdForm);

    String findPassword(MemberDto.findPasswordForm findPasswordForm);

    MemberDto.memberForm editMyInfo(MemberDto.editMyInfoForm editMyInfoForm, String userId);

    MemberDto.memberForm editMyPassword(MemberDto.editMyPasswordForm editMyPasswordForm, String userId);

}
