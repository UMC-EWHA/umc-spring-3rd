//UserDao

package com.umcewha.board;

import com.umcewha.board.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {

    public static List<User> users;

    // jdbc 연결 실패해서 사용하는 임시 데이터
    static {
        users = new ArrayList<>();
        users.add(new User(1,"testName1","1234"));
        users.add(new User(2,"testName2","1234"));
        users.add(new User(3,"testName3","1234"));
        users.add(new User(4,"testName4","1234"));
        users.add(new User(5,"testName5","1234"));
    }

    // JDBC
//    private JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public void setDataSource(DataSource dataSource) {
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//    }

    // Select all user.
    public List<User> getAllUsers() {
        return users;
    }

    // Select one user by userId
    public User getUserByUsername(String username) {
        return users
                .stream()
                .filter(user -> user.getUsername().equals(username))
                .findAny()
                .orElse(new User(-1, "", ""));
    }

    // Insert User
    public User insertUser(User user) {
        users.add(user);

        return user;
    }

    // Modify User
    public void updateUser(String username,User user) {
        users.stream()
                .filter(curUser -> curUser.getUsername().equals(username))
                .findAny()
                .orElse(new User(-1, "", ""))
                .setPassword(user.getPassword());
    }

    // Delete User
    public void deleteUser(String username) {
        users.removeIf(user -> user.getUsername().equals(username));
    }
}