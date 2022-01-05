package com.springreactsecurity.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springreactsecurity.domain.member.Member;
import com.springreactsecurity.security.UserMember;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        UserMember userMember = (UserMember) authentication.getPrincipal();
        Member member = userMember.getMember();
        member.setPassword(null);

        objectMapper.writeValue(response.getWriter(), member);
    }

}