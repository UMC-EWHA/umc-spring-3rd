package com.example.demo.src.board;

import com.example.demo.config.BaseResponse;
import com.example.demo.src.board.model.PostBoardReq;
import com.example.demo.src.board.model.PostBoardRes;
import com.example.demo.src.user.model.PostUserRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController
@RequestMapping("/app/boards")
public class BoardController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final BoardProvider boardProvider;
    @Autowired
    private final BoardService boardService;
    @Autowired
    private final JwtService jwtService;

    public BoardController(BoardProvider boardProvider, BoardService boardService, JwtService jwtService) {
        this.boardProvider = boardProvider;
        this.boardService = boardService;
        this.jwtService = jwtService;
    }

    /*
    *게시글 작성 API
    * [POST] / boards
    */
    @ResponseBody
    @PostMapping("/upload")
    public BaseResponse<PostBoardRes> uploadBoard(@RequestBody PostBoardReq postBoardReq) {

        if (postBoardReq.getTitle() == null) {
            return new BaseResponse<>(POST_BOARDS_EMPTY_TITLE);
        }
        if (postBoardReq.getContent() == null) {
            return new BaseResponse<>(POST_BOARDS_EMPTY_CONTENT);
        }
        PostBoardRes postBoardRes = boardService.uploadBoard(postBoardReq);

        return new BaseResponse<>(postBoardRes);
    }
}
