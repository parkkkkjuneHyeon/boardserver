package com.junhyun.boardwas.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class UserDto {
    private int id;
    private String email;
    private String password;
    private String nickname;
    private Boolean isAdmin;
    private Boolean isWithDraw;
    private Status status;
    private Date createTime;
    private Date updateTime;

    public static boolean hasNullDataBeforeRegister(UserDto userDto) {
        return userDto == null
                || userDto.getPassword() == null
                || userDto.getEmail() == null
                || userDto.getNickname() == null;
    }

    public enum Status {
        DEFAULT, ADMIN, DELETED
    }
}
