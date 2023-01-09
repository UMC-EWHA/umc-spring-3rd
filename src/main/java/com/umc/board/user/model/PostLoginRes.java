package com.umc.board.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
// 로그인의 결과
public class PostLoginRes {
    private int userIdx;
    private String jwt;
}
