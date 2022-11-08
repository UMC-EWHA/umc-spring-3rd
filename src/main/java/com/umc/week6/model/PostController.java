package com.umc.week6.model;
import com.umc.week6.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;
 
    @GetMapping("")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }
 
    @GetMapping("/{userid}")
    public Post getPostByUserId(@PathVariable  String userid) {
        return postService.getPostByUserId(userid);
    }
 
    @PostMapping("")
    public Post registerPost(@RequestBody Post post) {
 
        return postService.registerPost(post);
    }
 
    @PutMapping("/{userid}")
    public void modifyPost(@PathVariable String userid, @RequestBody Post post) {
      
       postService.modifyPost(userid, post);
    }
 
    @DeleteMapping("/{userid}")
    public void removePost(@PathVariable String userid) {
       postService.removePost(userid);
    }
}
