package com.umcewha.board.model;

public class User {
    private int userIdx;
    private String username;
    private String password;

    public User() {
    }

    public User(int userIdx, String username, String password) {
        this.userIdx = userIdx;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
