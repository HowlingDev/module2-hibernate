package com.example;

import com.example.DAOs.UserDao;
import com.example.DAOs.UserDaoImpl;
import com.example.model.User;
import com.example.services.UserService;

import java.util.Scanner;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    public static final UserDao userDao = new UserDaoImpl();
    public static final UserService userService = new UserService();
    public static Scanner sc = new Scanner(System.in);
    private static final Logger logger = LogManager.getLogger(Main.class);

    private static User user;
    private static String email;
    private static Long id;
    private static int age;

    public static void main(String[] args) {
        boolean exit = false;
        System.out.println("Добро пожаловать в приложение! \uD83D\uDE03 ");
        logger.info("Выполнен вход в приложение");

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
                    user = new User();
                    System.out.print("Введите имя: ");
                    user.setName(sc.next());
                    System.out.print("Введите свою почту: ");
                    email = sc.next();
                    while (!(email.endsWith("@gmail.com") || email.endsWith("@yandex.ru"))) {
                        System.out.println("Почта должна заканчиваться на @gmail.com или @yandex.ru");
                        System.out.print("Введите свою почту: ");
                        email = sc.next();
                    }
                    user.setEmail(email);
                    System.out.print("Введите свой возраст: ");
                    age = sc.nextInt();
                    while (age < 0) {
                        System.out.println("Возраст должен быть положительным");
                        System.out.print("Введите свой возраст: ");
                        age = sc.nextInt();
                    }
                    user.setAge(age);
                    System.out.println(userService.createNewUser(user));
                    break;
                case 2:
                    System.out.print("Введите ID пользователя, которого хотите найти: ");
                    id = sc.nextLong();
                    while (id < 0) {
                        System.out.println("ID должен быть положительным");
                        System.out.print("Введите ID: ");
                        id = sc.nextLong();
                    }
                    System.out.println(userService.readUser(id));
                    break;
                case 3:
                    System.out.print("Введите ID пользователя, информацию о котором хотите обновить: ");
                    id = sc.nextLong();
                    while (id < 0) {
                        System.out.println("ID должен быть положительным");
                        System.out.print("Введите ID: ");
                        id = sc.nextLong();
                    }
                    user = userDao.getById(id);
                    if (user != null) {
                        System.out.print("Введите новое имя: ");
                        user.setName(sc.next());
                        System.out.print("Введите новую почту: ");
                        String email = sc.next();
                        while (!(email.endsWith("@gmail.com") || email.endsWith("@yandex.ru"))) {
                            System.out.println("Почта должна заканчиваться на @gmail.com или @yandex.ru");
                            System.out.print("Введите новую почту: ");
                            email = sc.next();
                        }
                        user.setEmail(email);
                        System.out.print("Введите новый возраст: ");
                        int age = sc.nextInt();
                        while (age < 0) {
                            System.out.println("Возраст должен быть положительным");
                            System.out.print("Введите новый возраст: ");
                            age = sc.nextInt();
                        }
                        user.setAge(age);
                        System.out.println(userService.updateUser(user));
                    }
                    else {
                        logger.info("Не удалось обновить пользователя с ID {}, так как он не найден", id);
                    }
                    break;
                case 4:
                    System.out.print("Введите ID пользователя, которого хотите удалить: ");
                    Long id = sc.nextLong();
                    while (id < 0) {
                        System.out.println("ID должен быть положительным");
                        System.out.print("Введите ID: ");
                        id = sc.nextLong();
                    }
                    System.out.println(userService.deleteUser(id));
                    break;
                case 5:
                    List<User> users = userService.AllUsers();
                    System.out.println("Все пользователи:");
                    for (User user : users) {
                        System.out.println(user);
                    }
                    break;
                case 6:
                    exit = true;
                    logger.info("Приложение закончило работу.");
                    break;
                default:
                    System.out.println("Упс... Придется запустить приложение заново");
                    logger.info("Приложение аварийно закончило работу.");
                    exit = true;
            }
        }
    }
}