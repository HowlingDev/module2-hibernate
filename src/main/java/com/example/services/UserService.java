package com.example.services;

import com.example.DAOs.UserDao;
import com.example.DAOs.UserDaoImpl;
import com.example.model.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserService {
    public static final UserDao userDao = new UserDaoImpl();
    private static final Logger logger = LogManager.getLogger(UserService.class);

    public User createNewUser(User user) {
        return userDao.save(user);
    }

    public User readUser(Long id) {
        User user = userDao.getById(id);
        if (user != null) {
            logger.info("Пользователь с ID {} успешно найден!", id);
        }
        else {
            logger.info("Не удалось найти пользователя с ID {}", id);
        }
        return user;
    }

    public User updateUser(User updatedUser) {
        return userDao.update(updatedUser);
    }

    public boolean deleteUser(Long id) {
        return userDao.delete(id);
    }

    public List<User> AllUsers() {
        return userDao.getAllUsers();
    }
}