package com.springreactsecurity.security.handler;

import com.springreactsecurity.exception.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthenticationEntryPoint implements org.springframework.security.web.AuthenticationEntryPoint {

    private final SecurityExceptionHandlerUtil securityExceptionHandlerUtil;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException, ServletException {
        securityExceptionHandlerUtil.handleException(request, response, authenticationException, ErrorType.UNAUTHENTICATED);
    }

}
