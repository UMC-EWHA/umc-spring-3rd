package com.umc.umcserver.domain.board.dto;

import com.umc.umcserver.domain.board.repository.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
@AllArgsConstructor
public class FetchPostsResDto {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private Pageable pageable;


    public FetchPostsResDto(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.writer = entity.getWriter();
    }
}
