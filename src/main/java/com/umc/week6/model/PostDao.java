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
        //posts.add(post);
        //return post;
        String createPostQuery = "insert into post (postnum, title, content, userid) VALUES (?,?,?,?)"; // 실행될 동적 쿼리문
        //getPostNum() 대소문자 구분...? 자꾸 값이 0으로 들어갔었음
        Object[] createPostParams = new Object[]{post.getPostnum(), post.getTitle(), post.getContent(), post.getUserid()}; // 동적 쿼리의 ?부분에 주입될 값
        this.jdbcTemplate.update(createPostQuery, createPostParams);
        System.out.println(post.getPostnum());

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
 
    // Modify Post
    public void updatePost(String userid,Post post) {
        posts.stream()
                .filter(curUser -> curUser.getUserid().equals(userid))
                .findAny()
                .orElse(new Post(-1, "", "", ""))
                .setTitle(post.getTitle());
    }
 
    // Delete Post
    public void deletePost(String userid) {
        posts.removeIf(post -> post.getUserid().equals(userid));
    }
}