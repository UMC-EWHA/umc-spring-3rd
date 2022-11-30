package com.umc.umcserver.domain.board.dto;

import com.umc.umcserver.domain.board.repository.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdatePostsResDto {
    private Long id;
    private String title;
    private String content;
    private String writer;

    public UpdatePostsResDto(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.writer = entity.getWriter();
    }
}
