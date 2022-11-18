package com.example.demo.src.board;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.board.model.PatchPostReq;
import com.example.demo.src.board.model.PostBoardReq;
import com.example.demo.src.board.model.PostBoardRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;
import static com.example.demo.config.BaseResponseStatus.MODIFY_FAIL_CONTENT;

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
        String writer;
        String content;

        title = postBoardReq.getTitle();
        postBoardReq.setTitle(title);

        writer = postBoardReq.getWriter();
        postBoardReq.setWriter(writer);

        content = postBoardReq.getContent();
        postBoardReq.setContent(content);

        int boardIdx = boardDao.uploadBoard(postBoardReq);
        return new PostBoardRes(boardIdx);
    }

    //게시글 내용 수정
    public void modifyPostContent(PatchPostReq patchPostReq) throws BaseException {
        try {
            int result = boardDao.modifyPostContent(patchPostReq);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_CONTENT);
            }
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
