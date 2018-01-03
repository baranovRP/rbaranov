package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test.
 *
 * @author Roman Baranov (baranov.rp@gmail.com)
 * @version 1
 */
public class StoreTest {

    private Store roles;
    private Store users;

    @Test
    public void testInheritance() {
        Base user = new User("0");
        assertThat(user.getId(), is("0"));
        Base role = new Role("1");
        assertThat(role.getId(), is("1"));
    }

    @Test(expected = ClassCastException.class)
    public void givenBaseArraysWhenUseCrudOperationsThenOk() {
        roles = new RoleStore(3);
        users = new UserStore(3);

        Base role = new Role("0");
        Base user = new User("0");

        roles.add(role);
        assertThat(roles.findById("0"), is(role));
        users.add(user);
        assertThat(users.findById("0"), is(user));
        users.add(role);
    }
}