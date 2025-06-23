package com.junhyun.boardwas.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalException {


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ClientException> handleClientException(RuntimeException e) {
        ClientException clientException = ClientException.builder()
                .msg(e.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(clientException, clientException.getHttpStatus());
    }

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<ClientException> handleClientException(ClientException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(e, e.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> globalException(Exception e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>("서버에 장애가 생겼습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
