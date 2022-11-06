package com.umc.umcserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GetUserRes {
    private int userIndex;
    private String userName;
    private String password;
    private String email;
}
