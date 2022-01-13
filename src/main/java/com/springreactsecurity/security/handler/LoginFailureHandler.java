package com.springreactsecurity.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class LoginFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        ErrorResponseDto errorDto = new ErrorResponseDto();

        if (exception instanceof BadCredentialsException) {
            errorDto.setErrorMessage("LoginBadCredential");
        } else if (exception instanceof DisabledException) {
            errorDto.setErrorMessage("LoginDisable");
        } else if (exception instanceof CredentialsExpiredException) {
            errorDto.setErrorMessage("LoginExpired");
        } else if (exception instanceof UsernameNotFoundException || exception instanceof InternalAuthenticationServiceException) {
            errorDto.setErrorMessage("LoginUsernameNotFound");
        } else {
            errorDto.setErrorMessage("ServerError");
        }
        errorDto.setMessage("로그인에 실패했습니다. id, password 를 확인해주세요.");

        objectMapper.writeValue(response.getWriter(), errorDto);
    }

}
