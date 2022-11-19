package com.umc.board.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// 회원 가입을 위해 전달할 데이터 형태
public class PostUserReq {
    private String email;
    private String password;
    private String name;
}
