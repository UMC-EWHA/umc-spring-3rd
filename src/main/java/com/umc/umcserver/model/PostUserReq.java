package com.umc.umcserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PostUserReq {
    private int userIndex;
    private String username;
    private String password;
}
