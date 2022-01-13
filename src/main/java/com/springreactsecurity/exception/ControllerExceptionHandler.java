package com.springreactsecurity.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String code = "NO_CATCH_ERROR";
        String className = e.getClass().getName();

        if (e instanceof BaseException) {
            BaseException be = (BaseException) e;
            status = be.getErrorType().getStatus();
            code = be.getErrorType().getCode();
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            status = HttpStatus.METHOD_NOT_ALLOWED;
        } else {
            log.error(ExceptionUtils.getStackTrace(e));
        }

        ErrorResponse errorResponse = ErrorResponse.builder()
                .exception(className.substring(className.lastIndexOf(".") + 1))
                .code(code)
                .message(e.getMessage())
                .status(status.value())
                .error(status.getReasonPhrase())
                .build();

        return new ResponseEntity<>(errorResponse, status);
    }

//    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class})
//    public ResponseEntity<ErrorResponseDto> handleBindValidationException(Exception e) {
//        log.error(ExceptionUtils.getStackTrace(e));
//
//        ErrorResponseDto errorDto = new ErrorResponseDto();
//        if (e instanceof MethodArgumentNotValidException) {
//            errorDto.setMessage(((MethodArgumentNotValidException) e).getBindingResult().getAllErrors().get(0).getDefaultMessage());
//        } else {
//            errorDto.setMessage(((BindException) e).getBindingResult().getAllErrors().get(0).getDefaultMessage());
//        }
//        errorDto.setErrorMessage(MsgType.UnknownParameter.getMsgCode());
//        errorDto.setErrorDetailMessage(ExceptionUtils.getStackTrace(e));
//
//        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
//    }

}
