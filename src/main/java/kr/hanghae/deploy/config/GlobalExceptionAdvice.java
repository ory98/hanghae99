package kr.hanghae.deploy.config;

import kr.hanghae.deploy.dto.ApiResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ApiResponse<Object> handleRuntimeException(RuntimeException e) {
        var message = e.getMessage();
        log.error(e.getMessage()); // error 로그는 여기서 달아주기
        return ApiResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, message,null);
    }
}
