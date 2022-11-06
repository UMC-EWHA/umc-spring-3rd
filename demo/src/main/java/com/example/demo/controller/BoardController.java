package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {
    @GetMapping("/")
    public String list() {
        return "/board/list.html";
    }

    @GetMapping("/post")
    public String post() {
        return "/board/post.html";
    }
}
