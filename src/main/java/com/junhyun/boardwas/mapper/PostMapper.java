package com.junhyun.boardwas.mapper;

import com.junhyun.boardwas.dto.PostDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    void register(PostDto postDto);
    List<PostDto> selectMyPosts(int userId);
    void updatePosts(PostDto postDto);
    void deletePosts(int postId);
}
