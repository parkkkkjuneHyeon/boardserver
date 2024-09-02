package com.junhyun.boardwas.dto.request;


import lombok.Getter;
import lombok.NonNull;

@Getter
public class UserDeleteEmail {
    @NonNull
    private String password;
}
