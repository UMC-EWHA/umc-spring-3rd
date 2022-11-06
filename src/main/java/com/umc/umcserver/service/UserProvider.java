package com.umc.umcserver.service;

import com.umc.umcserver.dao.UserDao;
import com.umc.umcserver.model.GetUserRes;
import com.umc.umcserver.model.PostUserReq;
import com.umc.umcserver.model.PostUserRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProvider {

    private final UserDao userDao;

    @Autowired
    public UserProvider(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<GetUserRes> getUser(){
        List<GetUserRes> userRes = userDao.userRes();

        return userRes;
    }

    public PostUserRes postUser(PostUserReq postUserReq){
        int userIndex= userDao.addUser(postUserReq);
        PostUserRes postUserRes = new PostUserRes(userIndex);
        return postUserRes;
    }

}
