package com.junhyun.boardwas.controller;

import com.junhyun.boardwas.aop.LoginCheck;
import com.junhyun.boardwas.dto.CategoryDto;
import com.junhyun.boardwas.service.CategoryService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@Log4j2
public class CategoryController {
    private final CategoryService categoryServiceImpl;

    public CategoryController(
            CategoryService categoryServiceImpl) {
        this.categoryServiceImpl =  categoryServiceImpl;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @LoginCheck(type = LoginCheck.UserType.ADMIN)
    public void registerCategory(
            String accountEmail,
            @RequestBody
            CategoryDto categoryDto) {
        categoryServiceImpl.register(accountEmail, categoryDto);
    }
    @PatchMapping("/{categoryId}")
    @LoginCheck(type = LoginCheck.UserType.ADMIN)
    public void updateCategory(
            String accountEmail,
            @PathVariable(name="categoryId")
            int categoryId,
            @RequestBody
            CategoryRequest  categoryRequest) {
        CategoryDto categoryDto = new CategoryDto(
                categoryId,
                categoryRequest.name,
                CategoryDto.SortStatus.NEWEST,
                10,
                1);
        categoryServiceImpl.update(categoryDto);
    }

    @DeleteMapping("/{categoryId}")
    @LoginCheck(type = LoginCheck.UserType.ADMIN)
    public void deleteCategory(
            String accountEmail,
            @PathVariable(name = "categoryId")
            int categoryId) {
        categoryServiceImpl.delete(categoryId);
    }

    // --- request 객체 ---
    @Getter
    @Setter
    private static class CategoryRequest {
        private int id;
        private String name;
    }
}
