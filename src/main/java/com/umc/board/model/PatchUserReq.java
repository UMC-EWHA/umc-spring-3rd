package com.umc.board.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// 회원 정보 수정을 위해 전달할 데이터
public class PatchUserReq {
    private int userId;
    private String nickname;
}
