package com.umc.umcserver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FetchPostsReqDto {
    private Long id;
    private Integer pageIndex;

}
