package com.example;

import com.example.DAOs.UserDao;
import com.example.DAOs.UserDaoImpl;
import com.example.model.User;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Collections;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDaoImplIntegrationTest {
    private static final DockerImageName myImage = DockerImageName.parse("postgres:latest")
            .asCompatibleSubstituteFor("postgres");
    private PostgreSQLContainer<?> postgreSQLContainer;
    private UserDao userDao;

    @BeforeEach
    void startPostgreSQL() {
        postgreSQLContainer = new PostgreSQLContainer<>(myImage)
                .withUsername("user")
                .withPassword("password")
                .withDatabaseName("testUsersDB");
        postgreSQLContainer.start();
        System.setProperty("hibernate.connection.url", postgreSQLContainer.getJdbcUrl());
        System.setProperty("hibernate.connection.username", postgreSQLContainer.getUsername());
        System.setProperty("hibernate.connection.password", postgreSQLContainer.getPassword());
        userDao = new UserDaoImpl();
    }

    @AfterEach
    void stopPostgreSQL() {
        postgreSQLContainer.stop();
    }

    @Test
    void saveTest_shouldInsertUser() {
        User newUser = new User("ivan", "ivan@gmail.com", 19);

        userDao.save(newUser);

        User createdUser = userDao.getById(newUser.getId());
        assertNotNull(createdUser);
        assertEquals(newUser, createdUser);
    }

    @Test
    void deleteTest_shouldDeleteUser() {
        User user = new User("deletedivan", "deletedivan@gmail.com", 19);
        userDao.save(user);

        boolean deleted = userDao.delete(user.getId());

        assertTrue(deleted);
        User deletedUser = userDao.getById(user.getId());
        assertNull(deletedUser);
    }

    @Test
    void updateTest_shouldUpdateUser() {
        User user = new User("ivan", "newivan@gmail.com", 19);
        userDao.save(user);

        user.setName("ivan123");
        userDao.update(user);

        User updatedUser = userDao.getById(user.getId());
        assertEquals("ivan123", updatedUser.getName());
        assertEquals(updatedUser, user);
    }

    @Test @Order(2)
    void getAllUsersTest_shouldReturnListOfUsers() {
        User user1 = new User("vlad", "vlad@gmail.com", 19);
        User user2 = new User("dima", "dima@gmail.com", 26);
        userDao.save(user1);
        userDao.save(user2);

        List<User> users = userDao.getAllUsers();

        assertEquals(2, users.size());
        assertEquals(List.of(user1, user2), users);
    }

    @Test @Order(1)
    void getAllUsersTest_shouldReturnEmptyListWhenNoUsers() {
        List<User> users = userDao.getAllUsers();
        assertEquals(Collections.emptyList(), users);
    }
}