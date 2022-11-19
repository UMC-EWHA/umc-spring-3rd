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
    /*
    static {
        posts = new ArrayList<>();
        posts.add(new Post(1,"title1","content1", "123"));
        posts.add(new Post(2,"title2","content2", "456"));
        posts.add(new Post(3,"title3","content3", "789"));
        posts.add(new Post(4,"title4","content4", "000"));
    }
    */
 
    public List<Post> getAllPosts() {
        //return posts;
        String getUsersQuery = "select * from post"; //User 테이블에 존재하는 모든 회원들의 정보를 조회하는 쿼리
        return this.jdbcTemplate.query(getUsersQuery,
                (rs, rowNum) -> new Post(
                        rs.getInt("postnum"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("userid")) // RowMapper(위의 링크 참조): 원하는 결과값 형태로 받기
        );
    }

    public Post getPostByUserId(String userid) {
        return posts
                .stream()
                .filter(post -> post.getUserid().equals(userid))
                .findAny()
                .orElse(new Post(-1, "", "", ""));
    }
 
    // Insert User
    public Post insertPost(Post post) {
        posts.add(post);
 
        return post;
    }
 
    // Modify User
    public void updatePost(String userid,Post post) {
        posts.stream()
                .filter(curUser -> curUser.getUserid().equals(userid))
                .findAny()
                .orElse(new Post(-1, "", "", ""))
                .setTitle(post.getTitle());
    }
 
    public void deletePost(String userid) {
        posts.removeIf(post -> post.getUserid().equals(userid));
    }
}