package com.example.DAOs;

import com.example.model.User;

import java.util.List;

public interface UserDao {
    User save(User user);
    User getById(Long id);
    List<User> getAllUsers();
    User update(User user);
    boolean delete(Long id);
}
