package com.umc.board.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
// 회원 가입의 결과
public class PostUserRes {
    private int userIdx;
    private String jwt;
}
