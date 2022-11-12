package com.umc.umcserver.dto;

import com.umc.umcserver.repository.Board;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdatePostsReqDto {
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
