package com.umc.board;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public Long create(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/user/{id}")
    public User read(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("/user/{id}/update")
    public Long update(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @PostMapping("/user/{id}/delete")
    public Long delete(@PathVariable Long id) {
        userService.delete(id);
        return id;
    }
}
