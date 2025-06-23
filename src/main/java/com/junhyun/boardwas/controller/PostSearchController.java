package com.junhyun.boardwas.controller;

import com.junhyun.boardwas.dto.PostDto;
import com.junhyun.boardwas.dto.request.PostSearchRequest;
import com.junhyun.boardwas.service.PostSearchService;
import com.junhyun.boardwas.service.impl.PostSearchServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
@Slf4j
@RequiredArgsConstructor
public class PostSearchController {
    private final PostSearchService postSearchServiceImpl;


    @PostMapping
    public PostSearchResponse search(@RequestBody PostSearchRequest postSearchRequest) {
        List<PostDto> postDtoList = postSearchServiceImpl.getPosts(postSearchRequest);

        return new PostSearchResponse(postDtoList);
    }

    @GetMapping
    public PostSearchResponse searchByTagName(String tagName) {
        List<PostDto> postDtoList = postSearchServiceImpl.getPostByTag(tagName);

        return new PostSearchResponse(postDtoList);
    }

    @Setter
    @Getter
    @AllArgsConstructor
    public static class PostSearchResponse {
        private List<PostDto> postDtoList;
    }
}
