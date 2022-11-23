package com.umcewha.board.src.user.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PatchUserReq {
    private int userIdx;
    private String nickname;
}