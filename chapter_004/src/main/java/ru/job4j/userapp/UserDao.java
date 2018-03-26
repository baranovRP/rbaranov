package ru.job4j.userapp;

import java.util.Set;

public interface UserDao {

    User getUser(Integer id);

    Set<User> getAllUsers();

    User getUserByUserLoginAndPassw(String login, String passw);

    boolean insertUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(Integer id);
}
