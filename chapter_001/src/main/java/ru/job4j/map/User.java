package ru.job4j.map;

import java.util.Calendar;
import java.util.Objects;

/**
 * Class represent model User.
 *
 * @author Roman Baranov (baranov.rp@gmail.com)
 * @version 1
 */
public class User {

    String name;
    int children;
    Calendar birthday;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }
}
