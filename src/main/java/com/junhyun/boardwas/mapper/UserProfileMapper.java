package com.junhyun.boardwas.mapper;

import com.junhyun.boardwas.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

@Mapper
public interface UserProfileMapper {
    UserDto getUserProfile(String email);

    int register(UserDto userProfile);

    int insertUserProfile(
            @Param("id") Long id,
            @Param("email") String email,
            @Param("nickname") String nickname,
            @Param("password") String password,
            @Param("isAdmin") Boolean isAdmin,
            @Param("createTime") Date createTime,
            @Param("isWitheDraw") Boolean isWithDraw,
            @Param("Status") String status,
            @Param("updateTime") Date updateTime);

    int deleteUserProfile(@Param("id") int id);

    UserDto findByEmailAndPassword(
            @Param("email") String email,
            @Param("password") String password);

    int idCheck(@Param("email") String email);

    int updatePassword(UserDto user);
    int updateAddress(UserDto user);

}
