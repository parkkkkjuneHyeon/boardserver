package com.junhyun.boardwas.dto.request;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class UserLoginRequest {
    @NonNull
    private String email;
    @NonNull
    private String password;
}
