package com.example;

import com.example.DAOs.UserDao;
import com.example.DAOs.UserDaoImpl;
import com.example.model.User;
import com.example.services.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.AssertionsKt.assertNull;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;

public class UserServiceTest {
    @Mock
    private UserDao mockedUserDao = new UserDaoImpl();

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Disabled
    @Test
    void createNewUserTest() {
        User userToAdd = new User("victor", "victor@gmail.com", 20);
        when(mockedUserDao.save(userToAdd)).thenReturn(userToAdd);

        User addedUser = userService.createNewUser(userToAdd);

        assertEquals(userToAdd, addedUser);
    }

    @Disabled
    @Test
    void readUserTest_shouldGetUserById() {
        User expectedUser = new User("victor", "victor@gmail.com", 20);
        expectedUser.setId(14L);
        expectedUser.setCreated_at(LocalDate.of(2025,10,5));
        when(mockedUserDao.getById(expectedUser.getId())).thenReturn(expectedUser);

        User actualUser = userService.readUser(14L);

        assertEquals(expectedUser, actualUser);
    }

    @Disabled
    @Test
    void readUserTest_returnNull() {
        Long id = 1L;
        when(mockedUserDao.getById(id)).thenReturn(null);

        User actualUser = userService.readUser(1L);

        assertNull(actualUser);
    }

    @Disabled
    @Test
    void updateUserTest_shouldUpdateUser() {
        User updatedUser = new User("victor123", "victor@gmail.com", 25);
        updatedUser.setId(14L);
        updatedUser.setCreated_at(LocalDate.of(2025,10,5));
        when(mockedUserDao.update(updatedUser)).thenReturn(updatedUser);

        User actual = userService.updateUser(updatedUser);

        assertEquals(updatedUser.getId(), actual.getId());
        assertEquals(updatedUser.getName(), actual.getName());
        assertEquals(updatedUser.getEmail(), actual.getEmail());
        assertEquals(updatedUser.getAge(), actual.getAge());
        assertEquals(updatedUser.getCreated_at(), actual.getCreated_at());
    }

    @Disabled
    @Test
    void deleteUser() {
        Long id = 14L;
        when(mockedUserDao.delete(id)).thenReturn(true);

        boolean actual = userService.deleteUser(id);

        assertTrue(actual);
    }

    @Disabled
    @Test
    void AllUsersTest() {
        User user1 = new User("ivan", "ivan@gmail.com", 19);
        user1.setId(15L); user1.setCreated_at(LocalDate.of(2025,10,5));
        User user2 = new User("dima", "dima@gmail.com", 20);
        user2.setId(16L); user2.setCreated_at(LocalDate.of(2025,10,5));
        User user3 = new User("vlad", "vlad@gmail.com", 26);
        user3.setId(17L); user3.setCreated_at(LocalDate.of(2025,10,5));
        List<User> expectedUsers = List.of(user1, user2, user3);
        when(mockedUserDao.getAllUsers()).thenReturn(expectedUsers);

        List<User> actualUsers = userService.AllUsers();

        assertEquals(expectedUsers, actualUsers);
    }
}