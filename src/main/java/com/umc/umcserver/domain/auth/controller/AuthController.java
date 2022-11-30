package com.umc.umcserver.domain.auth.controller;

import com.umc.umcserver.domain.auth.dto.LoginRequestDto;
import com.umc.umcserver.domain.auth.dto.LoginResponseDto;
import com.umc.umcserver.domain.auth.service.AuthService;
import com.umc.umcserver.global.dto.DtoMetaData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {
    private final AuthService authService;

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto requestDto) {
        DtoMetaData dtoMetaData;

        try {
            String token = authService.login(requestDto);
            dtoMetaData = new DtoMetaData("로그인 성공");
            return ResponseEntity.ok(new LoginResponseDto(dtoMetaData, token));
        } catch (Exception e) {
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new LoginResponseDto(dtoMetaData));
        }
    }

}
