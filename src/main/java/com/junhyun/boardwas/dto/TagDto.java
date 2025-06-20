package com.junhyun.boardwas.dto;


import lombok.*;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TagDto {
    private int tagId;
    private String name;
    private String url;
    private int postId;
}
