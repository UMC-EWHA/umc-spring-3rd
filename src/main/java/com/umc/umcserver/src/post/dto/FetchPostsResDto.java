package com.umc.umcserver.src.post.dto;

import com.umc.umcserver.src.post.repository.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FetchPostsResDto {
    private Long id;
    private String title;
    private String content;
    private String writer;


    public FetchPostsResDto(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.writer = entity.getWriter();
    }
}
