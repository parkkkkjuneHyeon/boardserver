package com.junhyun.boardwas.dto.request;


import com.junhyun.boardwas.dto.CategoryDto;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostSearchRequest {
    private int id;
    private String name;
    private String contents;
    private int views;
    private int categoryId;
    private int userId;
    private CategoryDto.SortStatus sortStatus;
}
