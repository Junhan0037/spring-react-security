package com.springreactsecurity.domain.member.service;

import com.springreactsecurity.domain.member.Member;
import com.springreactsecurity.domain.member.dto.MemberDto;

public interface MemberService {

    MemberDto.MemberForm signUp(MemberDto.SignUpForm signUpForm);

    String findId(MemberDto.FindIdForm findIdForm);

    String findPassword(MemberDto.FindPasswordForm findPasswordForm);

    MemberDto.MemberForm editMyInfo(MemberDto.EditMyInfoForm editMyInfoForm, String userId);

    MemberDto.MemberForm editMyPassword(MemberDto.EditMyPasswordForm editMyPasswordForm, String userId);

    String completeSignUp(String token, String email);

    MemberDto.MemberForm userInfo(Member member);

}
