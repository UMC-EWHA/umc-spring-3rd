package com.example.demo.src.board;

import com.example.demo.config.BaseException;
import com.example.demo.src.board.model.PostBoardReq;
import com.example.demo.src.board.model.PostBoardRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Clob;

@Service
public class BoardService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final BoardDao boardDao;
    private final BoardProvider boardProvider;
    private final JwtService jwtService;

    @Autowired
    public BoardService(BoardDao boardDao, BoardProvider boardProvider, JwtService jwtService) {
        this.boardDao = boardDao;
        this.boardProvider = boardProvider;
        this.jwtService = jwtService;
    }

    // 게시글 업로드(POST)
    public PostBoardRes uploadBoard(PostBoardReq postBoardReq) {
        String title;
        String content;

        title = postBoardReq.getTitle();
        postBoardReq.setTitle(title);

        content = postBoardReq.getContent();
        postBoardReq.setContent(content);

        int boardIdx = boardDao.uploadBoard(postBoardReq);
        return new PostBoardRes(boardIdx);
    }
}
