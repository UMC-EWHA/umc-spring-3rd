package com.umc.week6.model;
import com.umc.week6.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;

@Repository
public class PostDao {
    public static List<Post> posts;
    private JdbcTemplate jdbcTemplate;

    @Autowired //readme 참고
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
 
    public List<Post> getAllPosts() {
        String getPostsQuery = "select * from post"; //존재하는 모든 posts 조회하는 쿼리
        return this.jdbcTemplate.query(getPostsQuery,
                (rs, rowNum) -> new Post(
                        rs.getInt("postnum"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("userid"))
        );
    }

    public Post getPostByUserId(String userid) {
        String getPostByIdQuery = "select * from post where userid =?"; // 해당 userid을 만족하는 post를 조회하는 쿼리문
        String getPostByIdParams = userid;
        return this.jdbcTemplate.queryForObject(getPostByIdQuery,    //queryForObject 메소드 사용 List->Post object 하나로 convert하니까
                (rs, rowNum) -> new Post(
                        rs.getInt("postnum"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("userid")),
                getPostByIdParams);

    }
 
    // Insert Post
    public Post insertPost(Post post) {
        String createPostQuery = "insert into post (postnum, title, content, userid) VALUES (?,?,?,?)"; // 동적 쿼리문
        //getPostNum() 대소문자 구분...? 자꾸 값이 0으로 들어갔었음
        Object[] createPostParams = new Object[]{post.getPostnum(), post.getTitle(), post.getContent(), post.getUserid()}; // 동적 쿼리의 ?부분에 주입될 값
        this.jdbcTemplate.update(createPostQuery, createPostParams);
        //System.out.println(post.getPostnum());

        String lastInsertIdQuery = "select * from post where userid =?"; // 해당 userid을 만족하는 post를 조회하는 쿼리문
        String lastInsertIdParams = post.getUserid();
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,    //queryForObject 메소드 사용 List->Post object 하나로 convert하니까
                (rs, rowNum) -> new Post(
                        rs.getInt("postnum"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("userid")),
                    lastInsertIdParams);      //getter쓰니까 get()을 못받아와서 그냥 함수 다 씀 Post.java에서...
    }

    public int checkPostnum(int postnum) {
        String checkPostnumQuery = "select exists(select postnum from post where postnum = ?)"; // User Table에 해당 email 값을 갖는 유저 정보가 존재하는가?
        int checkPostnumParams = postnum; // 해당(확인할) 이메일 값
        return this.jdbcTemplate.queryForObject(checkPostnumQuery,
                int.class,
                checkPostnumParams); // checkEmailQuery, checkEmailParams를 통해 가져온 값(intgud)을 반환한다. -> 쿼리문의 결과(존재하지 않음(False,0),존재함(True, 1))를 int형(0,1)으로 반환됩니다.
    } 
 
    // Modify PostTitle
    public void updatePostTitle(String userid,Post post) {
        String modifyPostTitleQuery = "update post set title = ? where userid = ? "; // 해당 userid를 만족하는 post의 title을 수정한다.
        Object[] modifyPostTitleParams = new Object[]{post.getTitle(), post.getUserid()}; // 주입될 값들(title, userid)
        this.jdbcTemplate.update(modifyPostTitleQuery, modifyPostTitleParams);
    }
 
    // Delete Post
    // delete를 구현하긴 했지만 보통 status를 비활성화로 수정하는 방법을 많이 이용. delete는 거의 안쓰임.
    public void deletePostByUserId(String userid) {
        String deletePostQuery = "delete from post where userid = ? "; // 해당 userid를 만족하는 post를 삭제한다.
        String deletePostParams = userid;
        this.jdbcTemplate.update(deletePostQuery, deletePostParams);
    }
}