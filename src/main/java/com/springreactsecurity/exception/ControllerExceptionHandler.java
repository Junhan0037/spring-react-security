package com.springreactsecurity.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {AccountException.class})
    public ResponseEntity<ErrorResponseDto> handleAccountException(AccountException e) {
        log.error(ExceptionUtils.getStackTrace(e));
        return new ResponseEntity<>(builderErrorResponse(e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(ExceptionUtils.getStackTrace(e));

        ErrorResponseDto errorDto = new ErrorResponseDto();
        errorDto.setMessage(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        errorDto.setErrorMessage(MsgType.UnknownParameter.getMsgCode());
        errorDto.setErrorDetailMessage(ExceptionUtils.getStackTrace(e));

        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    private ErrorResponseDto builderErrorResponse(BaseException baseException) {
        ErrorResponseDto errorDto = new ErrorResponseDto();
        errorDto.setMessage(baseException.getMsgArgs()[0]);
        errorDto.setErrorMessage(baseException.getMsgType().getMsgCode());
        errorDto.setErrorDetailMessage(ExceptionUtils.getStackTrace(baseException));

        return errorDto;
    }

}
