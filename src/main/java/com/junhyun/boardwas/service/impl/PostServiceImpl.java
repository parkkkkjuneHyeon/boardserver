package com.junhyun.boardwas.service.impl;

import com.junhyun.boardwas.dto.PostDto;
import com.junhyun.boardwas.dto.UserDto;
import com.junhyun.boardwas.mapper.PostMapper;
import com.junhyun.boardwas.mapper.UserProfileMapper;
import com.junhyun.boardwas.service.PostService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Log4j2
public class PostServiceImpl implements PostService {
    private final PostMapper postMapper;
    private final UserProfileMapper userProfileMapper;

    public PostServiceImpl(
            PostMapper postMapper,
            UserProfileMapper userProfileMapper) {
        this.postMapper = postMapper;
        this.userProfileMapper = userProfileMapper;
    }

    @Override
    public void register(String email, PostDto postDto) {
        UserDto userInfo = userProfileMapper.getUserProfile(email);
        if (userInfo != null) {
            postDto.setUserId(userInfo.getId());
            postDto.setCreateTime(new Date());
            postMapper.register(postDto);
        }else {
            log.error("registerPost ERROR! {}", postDto);
            throw new RuntimeException("registerPost ERROR! 게시글 등록 메소드를 확인해 주세요."
                + postDto
            );
        }
    }

    @Override
    public List<PostDto> getMyPosts(int userId) {
        return postMapper.selectMyPosts(userId);
    }

    @Override
    public void updatePosts(PostDto postDto) {
        if (postDto != null && postDto.getId() > 0) {
            postMapper.updatePosts(postDto);
        }else {
            log.error("updatePost ERROR! {}", postDto);
            throw new RuntimeException("updatePost ERROR! 게시글 수정 메소드를 확인해 주세요."
                    + postDto
            );
        }
    }

    @Override
    public void deletePosts(int userId, int postId) {
        if (userId > 0 && postId > 0) {
            postMapper.deletePosts(postId);
        }else {
            log.error("deletePost ERROR! {}", postId);
            throw new RuntimeException("deletePost ERROR! 게시글 삭제 메소드를 확인해 주세요."
                    + postId
            );
        }
    }
}
