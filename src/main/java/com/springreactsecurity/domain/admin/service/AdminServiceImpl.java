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
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

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

    @Override
    public MemberDto.MemberForm editUserInfo(AdminDto.EditUserForm editUserForm) {
        String userId = editUserForm.getUserId();
        String userPassword = editUserForm.getUserPassword();

        // 회원 정보
        Member member = memberRepository.findByUserId(userId).orElseThrow(() -> new AccountException(ErrorType.USER_NOT_EXISTS));

        // 패스워드 확인
        if (!passwordEncoder.matches(userPassword, member.getUserPassword())) {
            throw new AccountException(ErrorType.ORIGIN_PASSWORD_ERROR);
        }

        // 사용자 정보 수정
        String name = editUserForm.getName();
        if (!name.isEmpty()) {
            member.setName(name);
        }
        String email = editUserForm.getEmail();
        if (!email.isEmpty()) {
            member.setEmail(email);
        }

        return modelMapper.map(member, MemberDto.MemberForm.class);
    }

    @Override
    public MemberDto.MemberForm editUserPassword(AdminDto.EditUserPasswordForm editUserPasswordForm) {
        String userId = editUserPasswordForm.getUserId();
        String userPassword = editUserPasswordForm.getUserPassword();

        // 회원 정보
        Member member = memberRepository.findByUserId(userId).orElseThrow(() -> new AccountException(ErrorType.USER_NOT_EXISTS));

        // 패스워드 확인
        if (!passwordEncoder.matches(userPassword, member.getUserPassword())) {
            throw new AccountException(ErrorType.ORIGIN_PASSWORD_ERROR);
        }

        String newUserPassword = editUserPasswordForm.getNewUserPassword();
        String newUserPasswordConfirm = editUserPasswordForm.getNewUserPasswordConfirm();
        if (!newUserPassword.equals(newUserPasswordConfirm)) {
            throw new AccountException(ErrorType.PASSWORD_CONFIRM_ERROR);
        }

        member.setUserPassword(passwordEncoder.encode(newUserPassword));

        return modelMapper.map(member, MemberDto.MemberForm.class);
    }

}
