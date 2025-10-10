package com.example;

import com.example.DAOs.UserDao;
import com.example.model.User;
import com.example.services.UserService;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.AssertionsKt.assertNull;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserDao mockedUserDao;

    @InjectMocks
    private UserService userService;

    @Test
    void createNewUserTest() {
        User userToAdd = new User("victor", "victor@gmail.com", 20);
        userToAdd.setId(1L);
        userToAdd.setCreated_at(LocalDate.of(2025,10,10));
        when(mockedUserDao.save(userToAdd)).thenReturn(userToAdd);

        User addedUser = userService.createNewUser(userToAdd);
        
        assertEquals(userToAdd, addedUser);
    }

    @Test
    void readUserTest_shouldGetUserById() {
        User expectedUser = new User("victor", "victor@gmail.com", 20);
        expectedUser.setId(11L);
        expectedUser.setCreated_at(LocalDate.of(2025,10,5));
        when(mockedUserDao.getById(expectedUser.getId())).thenReturn(expectedUser);

        User actualUser = userService.readUser(11L);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    void readUserTest_returnNull() {
        when(mockedUserDao.getById(anyLong())).thenReturn(null);

        User actualUser = userService.readUser(1L);

        assertNull(actualUser);
    }

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

    @Test
    void deleteUser() {
        when(mockedUserDao.delete(anyLong())).thenReturn(true);

        boolean actual = userService.deleteUser(9L);

        assertTrue(actual);
    }

    @Test
    void AllUsersTest() {
        User user1 = new User("ivan", "ivan@gmail.com", 19);
        user1.setId(1L); user1.setCreated_at(LocalDate.of(2025,10,5));
        User user2 = new User("dima", "dima@gmail.com", 20);
        user2.setId(8L); user2.setCreated_at(LocalDate.of(2025,10,9));
        User user3 = new User("vlad", "vlad@gmail.com", 26);
        user3.setId(23L); user3.setCreated_at(LocalDate.of(2025,10,3));
        List<User> expectedUsers = List.of(user1, user2, user3);
        when(mockedUserDao.getAllUsers()).thenReturn(expectedUsers);

        List<User> actualUsers = userService.AllUsers();

        assertEquals(expectedUsers, actualUsers);
    }
}
