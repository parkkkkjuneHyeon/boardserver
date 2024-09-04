package com.junhyun.boardwas.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private int id;
    private int userId;
    private int categoryId;
    private int fileId;
    private String name;
    private String contents;
    private int views;
    private int isAdmin;
    private Date createTime;
    private Date updateTime;
}


