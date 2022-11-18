//Controller

package com.umcewha.board.user;

import com.umcewha.board.user.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable  String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping("")
    public User insertUser(@RequestBody User user) {

        return userService.insertUser(user);
    }

    @PutMapping("/{username}")
    public void updateUser(@PathVariable String username, @RequestBody User user) {

        userService.updateUser(username, user);
    }

    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
    }
}