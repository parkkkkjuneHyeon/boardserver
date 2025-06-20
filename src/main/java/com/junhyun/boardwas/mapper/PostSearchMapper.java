package com.junhyun.boardwas.mapper;

import com.junhyun.boardwas.dto.PostDto;
import com.junhyun.boardwas.dto.request.PostSearchRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostSearchMapper {

    List<PostDto> selectPosts(PostSearchRequest postSearchRequest);

    List<PostDto> getPostByTag(String tagName);

}
