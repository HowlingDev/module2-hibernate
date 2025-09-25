package com.example.DAOs;

import com.example.model.User;
import java.util.List;

public interface UserDao {
    void save(User user);
    User getById(Long id);
    List<User> getAllUsers();
    void update(User user);
    void delete(Long id);
}
