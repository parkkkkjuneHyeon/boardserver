package com.junhyun.boardwas.mapper;

import com.junhyun.boardwas.dto.CategoryDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {

    int register(CategoryDto categoryDto);
    void updateCategory(CategoryDto categoryDto);
    void deleteCategory(int categoryId);
}
