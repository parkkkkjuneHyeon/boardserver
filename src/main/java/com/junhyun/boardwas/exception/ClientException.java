package com.junhyun.boardwas.exception;


import lombok.*;
import org.springframework.http.HttpStatus;


@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ClientException extends RuntimeException {
    private HttpStatus httpStatus;
    private String msg;
}
