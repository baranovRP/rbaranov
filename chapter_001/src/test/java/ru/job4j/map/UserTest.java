package ru.job4j.map;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class UserTest {

    @Test
    public void createUser() {
        User user1 = new User("John");
        assertThat(user1.toString(),
            is("User{name='John, children=0, birthday=null}"));
        User user2 = new User("Jhon");
        assertThat(user1, not(user2));
    }
}