package com.umc.umcserver.dto;

import lombok.Getter;

@Getter
public class DeletePostsResDto {
    private Long id;
    private String title;
    private String content;
    private String writer;
}
