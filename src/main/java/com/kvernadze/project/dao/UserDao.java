package com.kvernadze.project.dao;

import com.kvernadze.project.model.User;

import java.util.Collection;

public interface UserDao {
    public User get(int id);
    public User get(String username);
    public Collection<User> getAll();
    public boolean create(User user);
    public boolean checkPassword(String username,String password);
    public void update(String username, int userType);
    public void delete(String username);
}
