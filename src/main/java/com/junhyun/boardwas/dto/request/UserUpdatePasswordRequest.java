package com.junhyun.boardwas.dto.request;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class UserUpdatePasswordRequest {
    @NonNull
    private String beforePassword;
    @NonNull
    private String afterPassword;
}
