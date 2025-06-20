package com.junhyun.boardwas.mapper;

import com.junhyun.boardwas.dto.TagDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TagMapper {

    void registerTag(TagDto tagDto);

    void updateTag(TagDto tagDto);

    void deletePostTag(int tagId);

    void createPostTag(Integer tagId, Integer postId);
}
