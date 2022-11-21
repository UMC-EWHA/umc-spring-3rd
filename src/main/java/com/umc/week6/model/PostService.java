package com.umc.week6.model;
import com.umc.week6.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
@Service
public class PostService {
    @Autowired
    PostDao postDao;
    
    public Post registerPost(Post post) {
        return postDao.insertPost(post);
    }
 
    public void modifyPost(String userid, Post post) {
        postDao.updatePostTitle(userid, post);
    }
 
    public void removePostByUserId(String userid) {
        postDao.deletePostByUserId(userid);
    }
 
}
 