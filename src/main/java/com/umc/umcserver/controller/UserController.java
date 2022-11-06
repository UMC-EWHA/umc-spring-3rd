package com.umc.umcserver.controller;

import com.umc.umcserver.model.GetUserRes;
import com.umc.umcserver.model.PostUserReq;
import com.umc.umcserver.model.PostUserRes;
import com.umc.umcserver.service.UserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private UserProvider userProvider;

    @Autowired
    public UserController(UserProvider userProvider) {
        this.userProvider = userProvider;
    }

    @GetMapping("/users")
    public List<GetUserRes> getUser() {
        List<GetUserRes> userRes = userProvider.getUser();
        return userRes;
    }

    @ResponseBody
    @PostMapping("/users")
    public PostUserRes postUser(@RequestBody PostUserReq postUserReq) {
        PostUserRes postUserRes = userProvider.postUser(postUserReq);
        return postUserRes;
    }
}
