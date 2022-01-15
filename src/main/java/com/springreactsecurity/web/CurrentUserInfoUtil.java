package com.springreactsecurity.web;

import com.springreactsecurity.core.exception.AccountException;
import com.springreactsecurity.core.exception.ErrorType;
import com.springreactsecurity.core.security.UserMember;
import com.springreactsecurity.domain.member.Member;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class CurrentUserInfoUtil {

    static Member getCurrentUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new AccountException(ErrorType.UNAUTHENTICATED);
        }

        UserMember userMember = (UserMember) authentication.getPrincipal();
        Member member = userMember.getMember();
        if (member == null) {
            throw new AccountException(ErrorType.UNAUTHENTICATED);
        }

        return member;
    }

}
