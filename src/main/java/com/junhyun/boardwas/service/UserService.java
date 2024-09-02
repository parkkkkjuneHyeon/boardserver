package com.junhyun.boardwas.service;

import com.junhyun.boardwas.dto.UserDto;

public interface UserService {
    void register(UserDto userProfile);

    UserDto login(String email, String password);

    boolean isDuplicatedId(String email);

    UserDto getUserInfo(String email);

    void updatePassword(String email, String beforePassword, String afterPassword);

    void deleteUser(String email, String password);
}
