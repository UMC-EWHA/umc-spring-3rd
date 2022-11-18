package umc.crud.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umc.crud.board.BoardDao;
import umc.crud.board.model.BoardDto;
import java.util.List;

@Service
public class BoardService {
    @Autowired
    BoardDao boardDao;

    public List<BoardDto> getAllBoards() {
        return boardDao.getAllBoards();
    }

    public BoardDto getBoardByWriter(String writer) {
        return boardDao.getBoardByWriter(writer);
    }

    public BoardDto writeBoard(BoardDto board) {
        return boardDao.insertBoard(board);
    }

    public void modifyBoard(String writer, BoardDto board) {
        boardDao.updateBoard(writer, board);
    }

    public void removeBoard(String writer) {
        boardDao.deleteBoard(writer);
    }

}