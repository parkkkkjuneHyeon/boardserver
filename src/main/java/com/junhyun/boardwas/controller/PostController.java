package com.junhyun.boardwas.controller;

import com.junhyun.boardwas.aop.LoginCheck;
import com.junhyun.boardwas.dto.PostDto;
import com.junhyun.boardwas.dto.UserDto;
import com.junhyun.boardwas.dto.response.CommonResponse;
import com.junhyun.boardwas.service.PostService;
import com.junhyun.boardwas.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/posts")
@Log4j2
public class PostController {
    private final PostService postServiceImpl;
    private final UserService userServiceImpl;

    public PostController(
            PostService postServiceImpl,
            UserService userServiceImpl) {
        this.postServiceImpl = postServiceImpl;
        this.userServiceImpl = userServiceImpl;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @LoginCheck(type = LoginCheck.UserType.USER)
    public ResponseEntity<CommonResponse<PostDto>> registerPost(
            String accountEmail,
            @RequestBody
            PostDto postDto
    ) {
        postServiceImpl.register(accountEmail, postDto);
        CommonResponse<PostDto> response = new CommonResponse<>(
                HttpStatus.OK,
                "SUCCESS",
                "registerPost",
                postDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/my-posts")
    @LoginCheck(type = LoginCheck.UserType.USER)
    public ResponseEntity<CommonResponse<List<PostDto>>> getMyPosts(
            String accountEmail
    ) {
        UserDto userInfo = userServiceImpl.getUserInfo(accountEmail);
        List<PostDto> postDtoList = postServiceImpl.getMyPosts(userInfo.getId());
        CommonResponse<List<PostDto>> response = new CommonResponse<>(
                HttpStatus.OK,
                "SUCCESS",
                "getMyPosts",
                postDtoList);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{postId}")
    @LoginCheck(type = LoginCheck.UserType.USER)
    public ResponseEntity<CommonResponse<PostDto>> updatePost(
            String accountEmail,
            @PathVariable(name = "postId")
            int postId,
            @RequestBody
            PostRequest postRequest
    ) {
        UserDto userInfo = userServiceImpl.getUserInfo(accountEmail);
        PostDto postDto = PostDto.builder()
                .id(postId)
                .userId(userInfo.getId())
                .categoryId(postRequest.getCategoryId())
                .fileId(postRequest.getFileId())
                .name(postRequest.getName())
                .contents(postRequest.getContents())
                .views(postRequest.getViews())
                .updateTime(new Date())
                .build();

        postServiceImpl.updatePosts(postDto);

        CommonResponse<PostDto> response = new CommonResponse<>(
                HttpStatus.OK,
                "SUCCESS",
                "updatePost",
                postDto
        );
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{postId}")
    @LoginCheck(type= LoginCheck.UserType.USER)
    public ResponseEntity<CommonResponse<PostDeleteResponse>> deletePost(
            String accountEmail,
            @PathVariable("postId")
            int postId
    ) {
        UserDto userInfo = userServiceImpl.getUserInfo(accountEmail);
        postServiceImpl.deletePosts(userInfo.getId(), postId);
        CommonResponse<PostDeleteResponse> response = new CommonResponse<>(
                HttpStatus.OK,
                "SUCCESS",
                "deletePosts",
                new PostDeleteResponse(userInfo.getId(), postId)
        );
        return ResponseEntity.ok(response);
    }

    // --- response 객체 ---
    @Getter
    @Setter
    @AllArgsConstructor
    private static class PostResponse {
        private List<PostDto> postDtoList;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    private static class PostDeleteResponse {
        private int postId;
        private int userId;
    }

    //--- request 객체 ---
    @Getter
    @Setter
    private static class PostRequest {
        private String name;
        private String contents;
        private int views;
        private int categoryId;
        private int fileId;
        private Date updateTIme;
    }
}
