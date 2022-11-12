package com.umcewha.board.user;

import com.umcewha.board.user.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    public User insertUser(User user) {
        return userDao.insertUser(user);
    }

    public void updateUser(String username, User user) {
        userDao.updateUser(username, user);
    }

    public void deleteUser(String username) {
        userDao.deleteUser(username);
    }

}
