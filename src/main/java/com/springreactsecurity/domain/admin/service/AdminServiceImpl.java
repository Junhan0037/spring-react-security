package com.springreactsecurity.domain.admin.service;

import com.springreactsecurity.core.exception.AccountException;
import com.springreactsecurity.core.exception.ErrorType;
import com.springreactsecurity.domain.admin.dto.AdminDto;
import com.springreactsecurity.domain.member.Member;
import com.springreactsecurity.domain.member.Role;
import com.springreactsecurity.domain.member.dto.MemberDto;
import com.springreactsecurity.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    @Override
    public MemberDto.MemberForm getUserInfo(AdminDto.UserInfoForm userInfoForm) {
        String userId = userInfoForm.getUserId();
        Member member = memberRepository.findByUserId(userId).orElseThrow(() -> new AccountException(ErrorType.USER_NOT_EXISTS));
        return modelMapper.map(member, MemberDto.MemberForm.class);
    }

    @Override
    public List<MemberDto.MemberForm> getUserInfoAll() {
        List<Member> memberList = memberRepository.findAll();
        return memberList.stream()
                        .filter(member -> member.getRole() != Role.ADMIN)
                        .map(member -> modelMapper.map(member, MemberDto.MemberForm.class))
                        .collect(Collectors.toList());
    }

}
