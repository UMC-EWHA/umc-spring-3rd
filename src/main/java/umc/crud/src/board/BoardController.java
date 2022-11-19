package umc.crud.src.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import umc.crud.config.BaseException;
import umc.crud.config.BaseResponse;
import umc.crud.src.board.model.*;
import static umc.crud.config.BaseResponseStatus.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {
    @Autowired
    private BoardService boardService;

    /**
     * 글 등록 API
     * POST /boards
     */
    @ResponseBody
    @PostMapping("")
    public BaseResponse<PostBoardRes> writeBoard(@RequestBody PostBoardReq postBoardReq) {
        try {
            PostBoardRes postBoardRes;

            if(postBoardReq.getWriter() != null) {
                postBoardRes = boardService.writeBoard(postBoardReq);
                return new BaseResponse<>(postBoardRes);
            } else {
                throw new BaseException(REQUEST_ERROR);
            }
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }

    }

    /**
     * 모든 글 조회 API
     * GET /boards
     */
    @ResponseBody
    @GetMapping("")
    public List<GetBoardRes> getAllBoards() {

        return boardService.getAllBoards();
    }

    /**
     * 특정 글 조회 API
     * GET /boards/{writer}
     */
    @GetMapping("/{writer}")
    public BaseResponse<List<GetBoardRes>> getBoardByWriter(@PathVariable String writer) {
        List<GetBoardRes> getBoardRes = boardService.getBoardByWriter(writer);
        return new BaseResponse<>(getBoardRes);
    }

    /**
     * 글 수정 API
     * PUT /boards/{boardId}
     */
    @PutMapping("/{boardId}")
    public BaseResponse<String> modifyContent(@PathVariable("boardId") int boardId, @RequestBody PutBoardReq putBoardReq) {

        /*
        try {
            if(putBoardReq.getWriter() != null) {
                PutBoardRes putBoardRes;
                putBoardRes = boardService.modifyContent(putBoardReq);
                return new BaseResponse<>(putBoardRes);
            } else {
                throw new BaseException(RESPONSE_ERROR);
            }
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
        */

        try {
            PutBoardReq boardReq = new PutBoardReq(boardId, putBoardReq.getContent());
            boardService.modifyContent(putBoardReq);

            String result = "내용이 수정되었습니다.";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 글 삭제 API
     * DELETE /boards/{boardId}
     */
    @DeleteMapping("/{boardId}")
    public void removeBoard(@PathVariable int boardId) {

        boardService.deleteBoard(boardId);
    }
}
