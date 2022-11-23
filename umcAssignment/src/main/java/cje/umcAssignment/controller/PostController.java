package cje.umcAssignment.controller;

import cje.umcAssignment.model.PostRequestDto;
import cje.umcAssignment.model.PostResponseDto;
import cje.umcAssignment.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    /*
    * Post 작성
    * */
    @PostMapping("/post/new")
    public PostResponseDto save(@RequestBody PostRequestDto postRequestDto){
        PostResponseDto postResponseDto = postService.save(postRequestDto);
        return postResponseDto;
    }

    /*
    * Post 조회 - id
    * */
    @GetMapping("/post/{id}")
    public PostResponseDto findPostById(@PathVariable Long id){
        PostResponseDto postResponseDto = postService.findById(id);
        return postResponseDto;
    }

    /*
     * Post 조회 - category
     * */
    @GetMapping("/post/category/{category}")
    public List<PostResponseDto> findPostByCategory(@PathVariable String category){
        List<PostResponseDto> posts = postService.findByCategory(category);
        return posts;
    }

    /*
     * Post 조회 - writer
     * */
    @GetMapping("/post/writer/{writer}")
    public List<PostResponseDto> findPostByWriter(@PathVariable String writer){
        List<PostResponseDto> posts = postService.findByWriter(writer);
        return posts;
    }

    /*
    * 모든 Post 조회
    * */
    @GetMapping("/post")
    public List<PostResponseDto> findPostAll(){
        List<PostResponseDto> posts = postService.findAll();
        return posts;
    }

    /*
    * Post 수정
    * */
    @PostMapping("/post/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto){
        PostResponseDto postResponseDto = postService.updatePost(id, postRequestDto);
        return postResponseDto;
    }

    /*
    * Post 삭제
    * */
    @DeleteMapping("/post/{id}")
    public void deletePost(@PathVariable Long id){
        postService.deleteById(id);
    }
}
