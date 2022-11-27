package com.umc.week6.model;

import com.umc.week6.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.umc.week6.model.config.BaseException;
import com.umc.week6.model.config.BaseResponse;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private PostProvider postProvider;   //@Autowired는 각각 붙여줘야 함!**
 
    @GetMapping("")
    public List<Post> getAllPosts() {
        return postProvider.getAllPosts();
    }
 
    @GetMapping("/{userid}")
    public Post getPostByUserId(@PathVariable  String userid) {
        return postProvider.getPostByUserId(userid);
    }

    @ResponseBody
    @PostMapping("")
    public BaseResponse<Post> registerPost(@RequestBody Post post) {
        try{
            Post postt=postService.registerPost(post);
            return new BaseResponse<>(postt);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
        //return postService.registerPost(post);
    }
 
    @PutMapping("/{userid}")
    public void modifyPost(@PathVariable String userid, @RequestBody Post post) {
      
       postService.modifyPost(userid, post);
    }
 
    @DeleteMapping("/{userid}")
    public void removePostByUserId(@PathVariable String userid) {
       postService.removePostByUserId(userid);
    }
}
