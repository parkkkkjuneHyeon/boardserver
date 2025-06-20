package com.junhyun.boardwas.service.impl;

import com.junhyun.boardwas.dto.PostDto;
import com.junhyun.boardwas.dto.request.PostSearchRequest;
import com.junhyun.boardwas.mapper.PostSearchMapper;
import com.junhyun.boardwas.service.PostSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostSearchServiceImpl implements PostSearchService {

    private PostSearchMapper postSearchMapper;


    @Cacheable(value = "getPosts", key = "'getPosts' + #postSearchRequest.getName() + postSearchRequest.getCategoryId()")
    @Override
    public List<PostDto> getPosts(PostSearchRequest postSearchRequest) {
        List<PostDto> postDtoList = null;

        try {
            postDtoList = postSearchMapper.selectPosts(postSearchRequest);
        }catch (RuntimeException e) {
            log.error("selectPosts method 실패 : {}",e.getMessage());
        }

        return postDtoList;
    }
}
