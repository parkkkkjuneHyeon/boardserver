package com.junhyun.boardwas.service.impl;

import com.junhyun.boardwas.dto.CommentDto;
import com.junhyun.boardwas.dto.PostDto;
import com.junhyun.boardwas.dto.TagDto;
import com.junhyun.boardwas.dto.UserDto;
import com.junhyun.boardwas.mapper.CommentMapper;
import com.junhyun.boardwas.mapper.PostMapper;
import com.junhyun.boardwas.mapper.TagMapper;
import com.junhyun.boardwas.mapper.UserProfileMapper;
import com.junhyun.boardwas.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Log4j2
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostMapper postMapper;
    private final UserProfileMapper userProfileMapper;
    private final CommentMapper commentMapper;
    private final TagMapper tagMapper;

    @Transactional
    @Override
    public void register(String email, PostDto postDto) {
        UserDto userInfo = userProfileMapper.getUserProfile(email);
        if (userInfo != null) {
            postDto.setUserId(userInfo.getId());
            postDto.setCreateTime(new Date());
            Integer postId = postDto.getId();

            postMapper.register(postDto);

            List<TagDto> tagDtoList = postDto.getTagDtoList();
            if(Objects.nonNull(tagDtoList))
                tagDtoList.forEach(tagDto -> {
                    Integer tagId = tagDto.getTagId();

                    tagMapper.registerTag(tagDto);
                    tagMapper.createPostTag(tagId, postId);
                });

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

    @Override
    public void registerComment(CommentDto commentDto) {
        if(commentDto.getPostId() != 0) {
            commentMapper.registerComment(commentDto);
        }
        else {
            log.info("registerComment {} ", commentDto);
            throw new RuntimeException("registerComment" + commentDto);
        }
    }

    @Override
    public void updateComment(CommentDto commentDto) {
        if(commentDto != null) {
            commentMapper.registerComment(commentDto);
        }
        else {
            log.info("updateComment error");
            throw new RuntimeException("updateComment" + commentDto);
        }
    }

    @Override
    public void deleteComment(int userId, int commentId) {
        if (userId > 0 && commentId > 0) {
            commentMapper.deleteComment(commentId);
        }else {
            log.error("deleteComment ERROR!");
            throw new RuntimeException("deleteComment ERROR!"
                    + commentId
            );
        }
    }

    @Override
    public void registerTag(TagDto tagDto) {
        if(tagDto != null) {
            tagMapper.registerTag(tagDto);
        }
        else {
            log.info("registerTag error");
            throw new RuntimeException("registerTag" + tagDto);
        }
    }

    @Override
    public void updateTag(TagDto tagDto) {
        if(tagDto != null) {
            tagMapper.updateTag(tagDto);
        }
        else {
            log.info("updateTag error");
            throw new RuntimeException("updateTag" + tagDto);
        }
    }

    @Override
    public void deletePostTag(int userId, int tagId) {
        if (userId > 0 && tagId > 0) {
            tagMapper.deletePostTag(tagId);
        }else {
            log.error("deletePostTag ERROR!");
            throw new RuntimeException("deletePostTag ERROR!"
                    + tagId
            );
        }
    }
}
