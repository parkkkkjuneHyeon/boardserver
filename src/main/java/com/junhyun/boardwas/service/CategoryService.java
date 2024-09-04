package com.junhyun.boardwas.service;

import com.junhyun.boardwas.dto.CategoryDto;

public interface CategoryService {
    void register(String accountEmail, CategoryDto categoryDto);
    void update(CategoryDto categoryDto);
    void delete(int categoryId);
}
