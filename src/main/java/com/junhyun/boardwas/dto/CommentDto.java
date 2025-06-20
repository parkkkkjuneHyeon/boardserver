package com.junhyun.boardwas.dto;


import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private int id;
    private int postId;
    private String contents;
    private int subCommentId;
}
