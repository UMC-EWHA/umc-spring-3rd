package umc.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import umc.crud.model.BoardDTO;
import umc.crud.service.BoardService;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("")
    public List<BoardDTO> getAllBoards() {
        return boardService.getAllBoards();
    }

    @GetMapping("/{writer}")
    public BoardDTO getBoardByWriter(@PathVariable String writer) {
        return boardService.getBoardByWriter(writer);
    }

    @PostMapping("")
    public BoardDTO writePost(@RequestBody BoardDTO board) {
        return boardService.writeBoard(board);
    }

    @PutMapping("/{writer}")
    public void modifyBoard(@PathVariable String writer, @RequestBody BoardDTO board) {
        boardService.modifyBoard(writer, board);
    }

    @DeleteMapping("{writer}")
    public void removeBoard(@PathVariable String writer) {
        boardService.removeBoard(writer);
    }
}
