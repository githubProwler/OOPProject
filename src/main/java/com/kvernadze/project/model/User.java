package com.kvernadze.project.model;

public class User {
    private int id;
    private String username;
    private String password;
    private int userType;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, int userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public User(String username, int userType) {
        this.username = username;
        this.userType = userType;
    }

    public User(int id, String username, int userType) {
        this.id = id;
        this.username = username;
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
