package com.umc.board.user;

import com.umc.board.user.model.*;
import com.umc.board.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final JwtService jwtService;

    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
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
            int userIdxByJwt = jwtService.getUserIdx();
            if (userIdx != userIdxByJwt) {
                throw new Exception("권한이 없는 유저의 접근입니다.");
            }
            userService.modifyUser(new PatchUserReq(userIdx, user.getName()));
            return "회원 정보가 수정되었습니다.";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
