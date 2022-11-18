package umc.crud.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import umc.crud.board.model.BoardDto;
import umc.crud.board.model.PostBoardReq;
import umc.crud.board.model.PostBoardRes;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BoardDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public static List<BoardDto> boards;

    static {
        boards = new ArrayList<>();
        boards.add(new BoardDto(1,"content1","UserA"));
        boards.add(new BoardDto(2,"content2","UserB"));
        boards.add(new BoardDto(3,"content3","UserC"));
        boards.add(new BoardDto(4,"content4","UserD"));
        boards.add(new BoardDto(5,"content5","UserE"));
    }


    // Board 생성
    public int writeBoard(BoardDto boardDto) {
        String writeBoardQuery = "insert into Board(boardId, content, writer) VALUES (?, ?, ?)";
        Object[] writeBoardParams = new Object[]{boardDto.getBoardId(), boardDto.getContent(), boardDto.getWriter()};
        this.jdbcTemplate.update(writeBoardQuery, writeBoardParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
    }


    public List<BoardDto> getAllBoards() {
        return boards;
    }

    public BoardDto getBoardByWriter(String writer) {
        return boards
                .stream()
                .filter(board -> board.getWriter().equals(writer))
                .findAny()
                .orElse(new BoardDto(-1,"", ""));
    }

    public BoardDto insertBoard(BoardDto board) {
        boards.add(board);

        return board;
    }


    public void updateBoard(String writer, BoardDto newBoard) {
        boards.stream()
                .filter(curWriter -> curWriter.getWriter().equals(writer))
                .findAny()
                .orElse(new BoardDto(-1, "", ""))
                .setContent(newBoard.getContent());
    }

    // Delete User
    public void deleteBoard(String writer) {

        boards.removeIf(board -> board.getWriter().equals(writer));
    }
}