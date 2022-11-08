package com.umc.week6.model;
import com.umc.week6.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
 
@Repository
public class PostDao {
    public static List<Post> posts;
 
    static {
        posts = new ArrayList<>();
        posts.add(new Post(1,"title1","content1", "123"));
        posts.add(new Post(2,"title2","content2", "456"));
        posts.add(new Post(3,"title3","content3", "789"));
        posts.add(new Post(4,"title4","content4", "000"));
    }
 
    public List<Post> getAllPosts() {
        return posts;
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