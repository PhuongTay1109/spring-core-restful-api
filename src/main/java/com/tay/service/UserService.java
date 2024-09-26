package com.tay.service;

import com.tay.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    void updateUser(int id, User user);
    void deleteUser(int userId);
    User getUserById(int userId);
    List<User> getAllUsers();
}
