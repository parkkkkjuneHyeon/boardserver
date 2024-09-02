package com.junhyun.boardwas.dto.response;

import com.junhyun.boardwas.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class LoginResponse {
    enum LoginStatus {
        SUCCESS, FAIL, DELETED
    }
    @NonNull
    private LoginStatus result;
    private UserDto userDto;

    private static final LoginResponse FAIL =
            new LoginResponse(LoginStatus.FAIL, null);

    public static LoginResponse success(UserDto userDto) {
        return new LoginResponse(LoginStatus.SUCCESS, userDto);
    }
}
