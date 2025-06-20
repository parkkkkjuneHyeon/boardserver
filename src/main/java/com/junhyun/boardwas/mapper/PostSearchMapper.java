package com.junhyun.boardwas.mapper;

import com.junhyun.boardwas.dto.PostDto;
import com.junhyun.boardwas.dto.request.PostSearchRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostSearchMapper {

    public List<PostDto> selectPosts(PostSearchRequest postSearchRequest);

}
