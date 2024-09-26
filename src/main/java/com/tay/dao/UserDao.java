package com.tay.dao;

import com.tay.model.User;

import java.util.List;

public interface UserDao {
    void saveUser(User user);
    void updateUser(int id, User user);
    void deleteUser(int userId);
    User getUserById(int userId);
    List<User> getAllUsers();
}