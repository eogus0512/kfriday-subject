package kfriday.subject.daehyeon.common.error;

import jakarta.servlet.http.HttpServletRequest;
import kfriday.subject.daehyeon.common.error.dto.ErrorResponse;
import kfriday.subject.daehyeon.common.error.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(BusinessException e) {
        ErrorCode errorCode = e.getErrorCode();
        log.warn("code = {} message = {}", errorCode.getCode(), errorCode.getMessage());

        return ResponseEntity.status(e.getStatus())
                .body(ErrorResponse.from(errorCode));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleInternalServerException(Exception e, HttpServletRequest request) {

        log.warn(
                "{} \n {}",
                request.getMethod(), request.getRequestURI(),
                e
        );

        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .build();
    }
}
