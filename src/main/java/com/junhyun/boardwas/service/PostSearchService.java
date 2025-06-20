package com.junhyun.boardwas.service;

import com.junhyun.boardwas.dto.PostDto;
import com.junhyun.boardwas.dto.request.PostSearchRequest;

import java.util.List;

public interface PostSearchService {


    List<PostDto> getPosts(PostSearchRequest postSearchRequest);
}
