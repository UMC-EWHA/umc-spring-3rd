package com.umc.week6.model;

import com.umc.week6.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.umc.week6.model.config.BaseException;
import com.umc.week6.model.config.BaseResponse;
import static com.umc.week6.model.config.BaseResponseStatus.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;

//@RestController
@Controller //RestContoller로 할 때는 String자체를 return해서 controller로 annotation바꾸니까 html 연결이 됨
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private PostProvider postProvider;   //@Autowired는 각각 붙여줘야 함!**
 
    //html연결 확인용
    @GetMapping("/home")
    public String main(Model model){
        model.addAttribute("data","Hello YounJi");
        return "index";
    }

    @GetMapping("")
    public String list(Model model){
        List<Post> postlist=postProvider.getAllPosts();
        model.addAttribute("list",postlist);
        return "list";
    }
 
    @GetMapping("/{userid}")
    public Post getPostByUserId(@PathVariable String userid) {
        return postProvider.getPostByUserId(userid);
    }

    @ResponseBody
    @PostMapping("")
    public BaseResponse<Post> registerPost(@RequestBody Post post) {
        if (post.getTitle() == null){
            return new BaseResponse<>(POST_USERS_EMPTY_TITLE);
        }
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
