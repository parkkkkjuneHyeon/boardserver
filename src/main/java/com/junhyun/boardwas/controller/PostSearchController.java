package com.junhyun.boardwas.controller;

import com.junhyun.boardwas.dto.PostDto;
import com.junhyun.boardwas.dto.request.PostSearchRequest;
import com.junhyun.boardwas.service.impl.PostSearchServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
@Slf4j
@RequiredArgsConstructor
public class PostSearchController {
    private final PostSearchServiceImpl postSearchServiceImpl;


    @PostMapping
    public PostSearchResponse search(@RequestBody PostSearchRequest postSearchRequest) {
        List<PostDto> postDtoList = postSearchServiceImpl.getPosts(postSearchRequest);

        return new PostSearchResponse(postDtoList);
    }



    @Getter
    @AllArgsConstructor
    private static class PostSearchResponse {
        private List<PostDto> postDtoList;
    }
}
