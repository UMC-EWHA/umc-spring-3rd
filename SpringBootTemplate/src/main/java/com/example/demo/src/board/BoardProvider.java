package com.example.demo.src.board;

import com.example.demo.utils.JwtService;
import org.springframework.stereotype.Service;

@Service
public class BoardProvider {
    private final BoardDao boardDao;
    private final JwtService jwtService;

    public BoardProvider(BoardDao boardDao, JwtService jwtService) {
        this.boardDao = boardDao;
        this.jwtService = jwtService;
    }
}
