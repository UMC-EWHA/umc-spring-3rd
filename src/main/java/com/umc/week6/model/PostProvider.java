package com.umc.week6.model;
import com.umc.week6.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
 
@Service
public class PostProvider {
    @Autowired
    PostDao postDao;
 
    public List<Post> getAllPosts() {
        return postDao.getAllPosts();
    }
 
    public Post getPostByUserId(String userid) {
        return postDao.getPostByUserId(userid);
    }
 
}
 