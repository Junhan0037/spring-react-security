package com.springreactsecurity.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springreactsecurity.exception.ErrorResponse;
import com.springreactsecurity.exception.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class SecurityExceptionHandlerUtil {

    private final ObjectMapper objectMapper;

    public void handleException(HttpServletRequest request, HttpServletResponse response, Exception e, ErrorType errorType) throws IOException, ServletException {
        String className = e.getClass().getName();

        response.setStatus(errorType.getStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .exception(className.substring(className.lastIndexOf(".") + 1))
                .code(errorType.getCode())
                .message(e.getMessage())
                .status(errorType.getStatus().value())
                .error(errorType.getStatus().getReasonPhrase())
                .build();

        objectMapper.writeValue(response.getWriter(), errorResponse);
    }

}
