package com.springreactsecurity.security.handler;

import com.springreactsecurity.exception.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {

    private final SecurityExceptionHandlerUtil securityExceptionHandlerUtil;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        securityExceptionHandlerUtil.handleException(request, response, accessDeniedException, ErrorType.UNAUTHORIZED);
    }

}
