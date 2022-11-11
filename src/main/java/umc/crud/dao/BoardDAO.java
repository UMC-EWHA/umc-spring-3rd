package umc.crud.dao;

import org.springframework.stereotype.Repository;
import umc.crud.model.BoardDTO;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BoardDAO {
    public static List<BoardDTO> boards;

    static {
        boards = new ArrayList<>();
        boards.add(new BoardDTO(1,"content1","UserA"));
        boards.add(new BoardDTO(2,"content2","UserB"));
        boards.add(new BoardDTO(3,"content3","UserC"));
        boards.add(new BoardDTO(4,"content4","UserD"));
        boards.add(new BoardDTO(5,"content5","UserE"));
    }

    public List<BoardDTO> getAllBoards() {
        return boards;
    }

    public BoardDTO getBoardByWriter(String writer) {
        return boards
                .stream()
                .filter(board -> board.getWriter().equals(writer))
                .findAny()
                .orElse(new BoardDTO(-1,"", ""));
    }

    public BoardDTO insertBoard(BoardDTO board) {
        boards.add(board);

        return board;
    }

    public void updateBoard(String writer, BoardDTO newBoard) {
        boards.stream()
                .filter(curWriter -> curWriter.getWriter().equals(writer))
                .findAny()
                .orElse(new BoardDTO(-1, "", ""))
                .setContent(newBoard.getContent());
    }

    // Delete User
    public void deleteBoard(String writer) {

        boards.removeIf(board -> board.getWriter().equals(writer));
    }
}