package com.junhyun.boardwas.controller;

import com.junhyun.boardwas.dto.UserDto;
import com.junhyun.boardwas.dto.request.UserDeleteEmail;
import com.junhyun.boardwas.dto.request.UserLoginRequest;
import com.junhyun.boardwas.dto.request.UserUpdatePasswordRequest;
import com.junhyun.boardwas.dto.response.LoginResponse;
import com.junhyun.boardwas.dto.response.UserInfoResponse;
import com.junhyun.boardwas.service.UserService;
import com.junhyun.boardwas.utils.SessionUtil;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Log4j2
public class UserController {

    private final UserService userServiceImpl;

    public UserController(UserService userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody UserDto userDto) {
        if (UserDto.hasNullDataBeforeRegister(userDto)) {
            throw new RuntimeException("회원가입 정보를 확인해 주세요.");
        }
        userServiceImpl.register(userDto);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<LoginResponse> signIn(
            @RequestBody UserLoginRequest userLoginRequest,
            HttpSession session) {
        String email = userLoginRequest.getEmail();
        String password = userLoginRequest.getPassword();
        LoginResponse loginResponse;
        ResponseEntity<LoginResponse> responseEntity;
        UserDto userInfo = userServiceImpl.login(email, password);
        if (userInfo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else if(userInfo != null){
            loginResponse = LoginResponse.success(userInfo);
            if(userInfo.getStatus() == UserDto.Status.ADMIN)
                SessionUtil.setLoginAdminEmail(session, email);
            else
                SessionUtil.setLoginMemberEmail(session, email);

            responseEntity =
                    new ResponseEntity<>(loginResponse, HttpStatus.OK);
        }else {
            throw new RuntimeException("Login Error ! 유저 정보가 없거나 지원되지 않는 유저입니다.");
        }

        return responseEntity;
    }

    @GetMapping("/my-info")
    public ResponseEntity<UserInfoResponse> memberInfo(
            HttpSession session) {
        String email = SessionUtil.getLoginEmail(session);
        UserDto userInfo = userServiceImpl.getUserInfo(email);
        return ResponseEntity.ok(new UserInfoResponse(userInfo));
    }

    @PutMapping("/logout")
    public void logout(HttpSession session) {
        SessionUtil.clear(session);
    }

    @PatchMapping("/password")
    public ResponseEntity<HttpStatus> updateUserPassword(
            @RequestBody
            UserUpdatePasswordRequest userUpdatePasswordRequest,
            HttpSession session
    ) {
        String email = SessionUtil.getLoginEmail(session);
        ResponseEntity<HttpStatus> responseEntity;

        try {
            userServiceImpl.updatePassword(
                    email,
                    userUpdatePasswordRequest.getBeforePassword(),
                    userUpdatePasswordRequest.getAfterPassword());
            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        }catch (RuntimeException e) {
            log.error("updatePassword 실패", e);
            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteUser(
            @RequestBody UserDeleteEmail userDeleteEmail,
            HttpSession session) {
        String email = SessionUtil.getLoginEmail(session);
        String password = userDeleteEmail.getPassword();
        ResponseEntity<HttpStatus> responseEntity;
        try {
            userServiceImpl.deleteUser(email, password);
            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        }catch (RuntimeException e) {
            log.error("deleteEmail 실패", e);
            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
}