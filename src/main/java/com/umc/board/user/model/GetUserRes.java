package com.umc.board.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
// 회원 정보 조회 요청의 결과
public class GetUserRes {
    private int userIdx;
    private String name;
    private String email;
    private String password;
}
