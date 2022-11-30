package com.umc.week6.model;
import com.umc.week6.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.umc.week6.model.config.BaseException;
import static com.umc.week6.model.config.BaseResponseStatus.*;

@Service
public class PostProvider {
    @Autowired
    PostDao postDao;
 
    public List<Post> getAllPosts() {
        return postDao.getAllPosts();
    }

    public int checkPostnum(int postnum) throws BaseException{
        try {
            return postDao.checkPostnum(postnum);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
 
    public Post getPostByUserId(String userid) {
        return postDao.getPostByUserId(userid);
    }
 
}
 