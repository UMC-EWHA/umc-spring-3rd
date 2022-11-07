package com.example.crud.controller;

import com.example.crud.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/test")
    public String test() {
        return "hello";
    }

    @GetMapping("/json")
    public Map<String, String> jsonTest() {
        Map<String, String> res = this.testService.getTest();

        return res;
    }

}
