package com.umc.board.user.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// 로그인을 위해 전달할 데이터 형태
public class PostLoginReq {
    private String email;
    private String password;
}
