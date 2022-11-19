package com.umc.board;

import com.umc.board.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원 가입 API
    @ResponseBody
    @PostMapping("/sign-up")
    public PostUserRes createUser(@RequestBody PostUserReq postUserReq) {
        try {
            return userService.createUser(postUserReq);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 로그인 API
    @ResponseBody
    @PostMapping("/log-in")
    public PostLoginRes logIn(@RequestBody PostLoginReq postLoginReq) {
        try {
            return userService.logIn(postLoginReq);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 전체 유저 정보 조회 API
    @ResponseBody
    @GetMapping("")
    public List<GetUserRes> getUsers() {
        try {
            return userService.getUsers();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 유저 아이디로 정보 조회 API
    @ResponseBody
    @GetMapping("/{userIdx}")
    public GetUserRes getUser(@PathVariable("userIdx") int userIdx) {
        try {
            return userService.getUser(userIdx);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 회원 정보 수정 API
    @ResponseBody
    @PatchMapping("/{userIdx}")
    public String modifyUser(@PathVariable("userIdx") int userIdx, @RequestBody User user) {
        try {
            userService.modifyUser(new PatchUserReq(userIdx, user.getName()));
            return "회원 정보가 수정되었습니다.";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
