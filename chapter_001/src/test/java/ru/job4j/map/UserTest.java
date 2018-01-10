package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserTest {

    @Test
    public void createTwoObjectsWithEqualFields() {
        Calendar birthday = new GregorianCalendar(2000, 01, 01);
        User user1 = new User("John", 5, birthday);
        User user2 = new User("John", 5, birthday);

        Map<User, String> userObjectMap = new HashMap<User, String>() {
            {
                put(user1, "user1");
                put(user2, "user2");
            }
        };

        System.out.println(userObjectMap.toString());
    }
}