package umc.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umc.crud.dao.BoardDAO;
import umc.crud.model.BoardDTO;
import java.util.List;

@Service
public class BoardService {
    @Autowired
    BoardDAO boardDao;

    public List<BoardDTO> getAllBoards() {
        return boardDao.getAllBoards();
    }

    public BoardDTO getBoardByWriter(String writer) {
        return boardDao.getBoardByWriter(writer);
    }

    public BoardDTO writeBoard(BoardDTO board) {
        return boardDao.insertBoard(board);
    }

    public void modifyBoard(String writer, BoardDTO board) {
        boardDao.updateBoard(writer, board);
    }

    public void removeBoard(String writer) {
        boardDao.deleteBoard(writer);
    }

}