package com.umc.umcserver.domain.auth.dto;

import com.umc.umcserver.global.dto.DtoMetaData;
import lombok.Data;

@Data
public class LoginResponseDto {

    private DtoMetaData dtoMetaData;
    private String token;

    public LoginResponseDto(DtoMetaData dtoMetaData, String token) {
        this.dtoMetaData = dtoMetaData;
        this.token = token;
    }

    public LoginResponseDto(DtoMetaData dtoMetaData) {
        this.dtoMetaData = dtoMetaData;
        this.token = null;
    }
}
