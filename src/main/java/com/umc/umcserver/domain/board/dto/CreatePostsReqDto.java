package com.umc.umcserver.domain.board.dto;

import com.umc.umcserver.domain.board.repository.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreatePostsReqDto {
    private String title;
    private String content;
    private String writer;

    // Dto에서 필요한 부분 entity화 시킴킴
   public Board toEntity(){
        return Board.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .build();
    }
}
