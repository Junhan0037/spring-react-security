package com.springreactsecurity.domain.admin.service;

import com.springreactsecurity.domain.admin.dto.AdminDto;
import com.springreactsecurity.domain.member.dto.MemberDto;

import java.util.List;

public interface AdminService {

    MemberDto.MemberForm getUserInfo(AdminDto.UserInfoForm userInfoForm);

    List<MemberDto.MemberForm> getUserInfoAll();

}
