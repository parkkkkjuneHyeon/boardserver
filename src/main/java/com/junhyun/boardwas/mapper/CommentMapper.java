package com.junhyun.boardwas.mapper;

import com.junhyun.boardwas.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {


    void registerComment(CommentDto commentDto);

    void updateComment(CommentDto commentDto);

    void deleteComment(int commentId);
}
