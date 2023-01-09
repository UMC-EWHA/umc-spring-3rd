package com.example.demo.src.board;

import com.example.demo.src.board.model.GetPostRes;
import com.example.demo.src.board.model.PatchPostReq;
import com.example.demo.src.board.model.PostBoardReq;
import com.example.demo.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class BoardDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int uploadBoard(PostBoardReq postBoardReq) {
        String uploadBoardQuery = "insert into Posts (title, writer, content) VALUES (?, ?, ?)"; // 실행될 동적 쿼리문
        Object[] uploadBoardParams = new Object[]{postBoardReq.getTitle(), postBoardReq.getWriter(), postBoardReq.getContent()}; // 동적 쿼리의 ?부분에 주입될 값
        this.jdbcTemplate.update(uploadBoardQuery, uploadBoardParams);

        String lastInsertIdQuery = "select last_insert_id()"; // 가장 마지막에 삽입된(생성된) id값은 가져온다.
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class); // 해당 쿼리문의 결과 마지막으로 삽인된 유저의 userIdx번호를 반환한다.
    }

    /*public List<GetPostRes> getPosts(Pagination pagination) {
        // select * from [table] limit ? offset ?
        // offset으로부터 limit개 만큼 데이터 출력
        String getPostsQuery = "select * from Posts limit ? offset ?";
        Object[] getPostsParams = new Object[]{pagination.getLimit(), pagination.getOffSet()};
        return this.jdbcTemplate.query(getPostsQuery,
                (rs, rowNum) -> new GetPostRes(
                        rs.getInt("postIdx"),
                        rs.getString("title"),
                        rs.getString("writer"),
                        rs.getString("content")),
                getPostsParams);
    }*/
    public List<GetPostRes> getPosts() {
        String getPostsQuery = "select * from Posts";
        //Object[] getPostsParams = new Object[]{pagination.getLimit(), pagination.getOffSet()};
        return this.jdbcTemplate.query(getPostsQuery,
                (rs, rowNum) -> new GetPostRes(
                        rs.getInt("postIdx"),
                        rs.getString("title"),
                        rs.getString("writer"),
                        rs.getString("content")
                )
        );
    }

    public List<GetPostRes> getPostsByWriter(String writer) {
        String getPostsByWriterQuery = "select * from Posts where writer = ?";
        String getPostsByWriterParams = writer;

        return this.jdbcTemplate.query(getPostsByWriterQuery,
                (rs, rowNum) -> new GetPostRes(
                        rs.getInt("postIdx"),
                        rs.getString("title"),
                        rs.getString("writer"),
                        rs.getString("content")),
                getPostsByWriterParams
        );
    }

    public int modifyPostContent(PatchPostReq patchPostReq){
        String modifyPostContentQuery = "update Posts set content = ? where postIdx = ? ";
        Object[] modifyPostContentParams = new Object[]{patchPostReq.getContent(), patchPostReq.getPostIdx()};

        return this.jdbcTemplate.update(modifyPostContentQuery, modifyPostContentParams);
    }
}
