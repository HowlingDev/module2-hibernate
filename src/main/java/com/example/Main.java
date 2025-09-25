package com.example;

import com.example.DAOs.UserDao;
import com.example.DAOs.UserDaoImpl;
import com.example.model.User;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static final UserDao userDao = new UserDaoImpl();
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        System.out.println("Добро пожаловать в приложение! \uD83D\uDE03 ");

        while (!exit) {
            System.out.println("Выберите нужное действие:");
            System.out.println("1 -> Создать нового пользователя");
            System.out.println("2 -> Найти пользователя");
            System.out.println("3 -> Обновить информацию о пользователе");
            System.out.println("4 -> Удалить пользователя");
            System.out.println("5 -> Получить список пользователей");
            System.out.println("6 -> Выход из приложения");
            int num = sc.nextInt();
            switch (num) {
                case 1:
                    createNewUser();
                    break;
                case 2:
                    readUser();
                    break;
                case 3:
                    updateUser();
                    break;
                case 4:
                    deleteUser();
                    break;
                case 5:
                    AllUsers();
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Упс... Придется запустить приложение заново");
                    exit = true;
            }
        }
    }

    private static void createNewUser() {
        User user = new User();
        System.out.print("Введите имя: ");
        user.setName(sc.next());
        System.out.print("Введите свою почту: ");
        user.setEmail(sc.next());
        System.out.print("Введите свой возраст: ");
        user.setAge(sc.nextInt());
        userDao.save(user);
    }

    private static void readUser() {
        System.out.print("Введите ID пользователя, которого хотите найти: ");
        Long id = sc.nextLong();
        User user = userDao.getById(id);
        if (user != null) {
            System.out.println("Найден пользователь: ");
            System.out.println(user);
        }
        else {
            System.out.println("Пользователь не найден");
        }
    }

    private static void updateUser() {
        System.out.print("Введите ID пользователя, информацию о котором хотите обновить: ");
        Long id = sc.nextLong();
        User user = userDao.getById(id);
        if (user != null) {
            System.out.print("Введите новое имя: ");
            user.setName(sc.next());
            System.out.print("Введите новую почту: ");
            user.setEmail(sc.next());
            System.out.print("Введите новый возраст: ");
            user.setAge(sc.nextInt());
            userDao.update(user);
        }
        else {
            System.out.println("Пользователь не найден");
        }
    }

    private static void deleteUser() {
        System.out.print("Введите ID пользователя, которого хотите удалить: ");
        Long id = sc.nextLong();
        userDao.delete(id);
    }

    private static void AllUsers() {
        List<User> users = userDao.getAllUsers();
        System.out.println("Все пользователи:");
        for (User user : users) {
            System.out.println(user);
        }
    }
}