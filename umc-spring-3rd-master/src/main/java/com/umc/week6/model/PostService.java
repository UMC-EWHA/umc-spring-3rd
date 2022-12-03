package com.umc.week6.model;
import com.umc.week6.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import com.umc.week6.model.config.BaseException;
import static com.umc.week6.model.config.BaseResponseStatus.*;

@Transactional
@Service
public class PostService {
    @Autowired
    PostDao postDao;
    @Autowired
    PostProvider postProvider;
    
    public Post registerPost(Post post) throws BaseException {
        if (postProvider.checkPostnum(post.getPostnum()) == 1) {  //논리적 validation : 중복 검사
            throw new BaseException(POST_USERS_EXISTS_POSTNUM);
        }
        return postDao.insertPost(post);
    }
 
    public void modifyPost(String userid, Post post) {
        postDao.updatePostTitle(userid, post);
    }
 
    public void removePostByUserId(String userid) {
        postDao.deletePostByUserId(userid);
    }
 
}
 