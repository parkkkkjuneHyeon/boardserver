package com.junhyun.boardwas.service.impl;

import com.junhyun.boardwas.dto.CategoryDto;
import com.junhyun.boardwas.mapper.CategoryMapper;
import com.junhyun.boardwas.service.CategoryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public void register(String accountEmail, CategoryDto categoryDto) {
        if (accountEmail == null) {
            log.error("register ERROR: accountEmail is null");
            throw new RuntimeException("게시글 카테고리 등록 메소드를 확인해주세요. "
                    + categoryDto);
        }
        categoryMapper.register(categoryDto);
    }

    @Override
    public void update(CategoryDto categoryDto) {
        if (categoryDto == null) {
            log.error("update ERROR: categoryDto is null");
            throw new RuntimeException("게시글 카테고리 수정 메소드를 확인해주세요. "
                    + categoryDto);
        }
        categoryMapper.updateCategory(categoryDto);
    }

    @Override
    public void delete(int categoryId) {
        if (categoryId <= 0) {
            log.error("delete ERROR: categoryId is null");
            throw new RuntimeException("게시글 카테고리 삭제 메소드를 확인해주세요. "
                    + categoryId);
        }
        categoryMapper.deleteCategory(categoryId);
    }
}

