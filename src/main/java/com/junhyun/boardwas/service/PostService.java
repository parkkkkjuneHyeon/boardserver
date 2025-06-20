package com.junhyun.boardwas.service;

import com.junhyun.boardwas.dto.CommentDto;
import com.junhyun.boardwas.dto.PostDto;
import com.junhyun.boardwas.dto.TagDto;

import java.util.List;

public interface PostService {

    // -- post --
    void register(String email, PostDto postDto);

    List<PostDto> getMyPosts(int userId);

    void updatePosts(PostDto postDto);

    void deletePosts(int userId, int postId);



    // -- comment --
    void registerComment(CommentDto commentDto);

    void updateComment(CommentDto commentDto);

    void deleteComment(int userId, int commentId);


    // -- tag --
    void registerTag(TagDto tagDto);

    void updateTag(TagDto tagDto);

    void deletePostTag(int userId, int tagId);
}
