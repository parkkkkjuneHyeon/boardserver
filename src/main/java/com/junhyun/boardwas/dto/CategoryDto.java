package com.junhyun.boardwas.dto;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    public enum SortStatus {
        CATEGORIES, NEWEST, OLDEST
    }
    public int id;
    private String name;
    private SortStatus sortStatus;
    private int searchCount;
    private int pagingStartOffset;
}
