package com.junhyun.boardwas.service;

import com.junhyun.boardwas.dto.PostDto;

import java.util.List;

public interface PostService {
    void register(String email, PostDto postDto);
    List<PostDto> getMyPosts(int userId);
    void updatePosts(PostDto postDto);
    void deletePosts(int userId, int postId);
}
