package umc.crud.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import umc.crud.board.model.BoardDto;

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
    public BoardDto writePost(@RequestBody BoardDto board) {
        return boardService.writeBoard(board);
    }

    /**
     * 모든 글 조회 API
     * GET /boards
     */
    @ResponseBody
    @GetMapping("")
    public List<BoardDto> getAllBoards() {
        return boardService.getAllBoards();
    }

    /**
     * 특정 글 조회 API
     * GET /boards/{writer}
     */
    @GetMapping("/{writer}")
    public BoardDto getBoardByWriter(@PathVariable String writer) {
        return boardService.getBoardByWriter(writer);
    }

    /**
     * 글 수정 API
     * PUT /boards/{writer}
     */
    @PutMapping("/{writer}")
    public void modifyBoard(@PathVariable String writer, @RequestBody BoardDto board) {
        boardService.modifyBoard(writer, board);
    }

    /**
     * 글 삭제 API
     * DELETE /boards/{writer}
     */
    @DeleteMapping("{writer}")
    public void removeBoard(@PathVariable String writer) {
        boardService.removeBoard(writer);
    }
}
