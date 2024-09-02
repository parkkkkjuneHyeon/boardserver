package com.junhyun.boardwas.service.impl;

import com.junhyun.boardwas.dto.UserDto;
import com.junhyun.boardwas.exception.DuplicateIdException;
import com.junhyun.boardwas.mapper.UserProfileMapper;
import com.junhyun.boardwas.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.junhyun.boardwas.utils.SHA256Util.encryptSHA256;


@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserProfileMapper userProfileMapper;


    @Override
    public void register(UserDto userProfile) {
        Boolean duplicateResult = isDuplicatedId(userProfile.getEmail());
        if (duplicateResult) {
            throw new DuplicateIdException("중복된 아이디 입니다.");
        }
        userProfile.setCreateTime(new Date());
        userProfile.setPassword(encryptSHA256(userProfile.getPassword()));
        int registerCount = userProfileMapper.register(userProfile);
        if (registerCount != 1) {
            log.error("insertError {}", userProfile);
            throw new RuntimeException(
                    "register ERROR! 다시 회원가입을 해주세요. \n"
                            + "Params : "
                            + userProfile
            );
        }
    }

    @Override
    public UserDto login(String email, String password) {
        String cryptoPassword = encryptSHA256(password);
        return userProfileMapper.findByEmailAndPassword(email, cryptoPassword);
    }

    @Override
    public boolean isDuplicatedId(String email) {
        return userProfileMapper.idCheck(email) == 1;
    }

    @Override
    public UserDto getUserInfo(String email) {
        return null;
    }

    @Override
    public void updatePassword(String email, String beforePassword, String afterPassword) {
        String cryptoPassword = encryptSHA256(beforePassword);
        UserDto memberInfo = userProfileMapper.findByEmailAndPassword(email, cryptoPassword);
        if (memberInfo != null) {
            memberInfo.setPassword(encryptSHA256(afterPassword));
            int insertCount = userProfileMapper.updatePassword(memberInfo);
        }else {
            log.error("updatePasswordError! {}", memberInfo);
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
    }

    @Override
    public void deleteUser(String email, String password) {
        String cryptoPassword = encryptSHA256(password);
        UserDto memberInfo = userProfileMapper.findByEmailAndPassword(email, cryptoPassword);
        if (memberInfo != null) {
            userProfileMapper.deleteUserProfile(memberInfo.getId());
        }else {
            log.error("deleteUserError! {}", memberInfo);
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
    }
}

