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
    public List<GetBoardRes> getAllBoards(@RequestBody GetBoardReq getBoardReq) {

        return boardService.getAllBoards(getBoardReq);
    }

    /**
     * 특정 글 조회 API
     * GET /boards/{writer}
     */
    @GetMapping("/{writer}")
    public BaseResponse<GetBoardRes> getBoardByWriter(@PathVariable String writer) {
        GetBoardRes getBoardRes = boardService.getBoardByWriter(writer);
        return new BaseResponse<>(getBoardRes);
    }

    /**
     * 글 수정 API
     * PUT /boards/{writer}
     */
    @PutMapping("/{writer}")
    public BaseResponse<PutBoardRes> modifyBoard(@PathVariable String writer, @RequestBody PutBoardReq putBoardReq) {
        try {
            if(putBoardReq.getWriter() != null) {
                PutBoardRes putBoardRes;
                putBoardRes = boardService.modifyBoard(writer, putBoardReq);
                return new BaseResponse<>(putBoardRes);
            } else {
                throw new BaseException(RESPONSE_ERROR);
            }
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 글 삭제 API
     * DELETE /boards/{writer}
     */
    @DeleteMapping("/{writer}")
    public void removeBoard(@PathVariable String writer) {

        boardService.removeBoard(writer);
    }
}
