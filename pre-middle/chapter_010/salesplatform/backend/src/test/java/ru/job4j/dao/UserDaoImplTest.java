package ru.job4j.dao;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.model.Role;
import ru.job4j.model.User;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserDaoImplTest {

    private User user;

    @Before
    public void setUp() {
        user = new User().setEmail("ivanov@email.com")
            .setPassw("1234")
            .setRole(new Role(1L, "USER"))
            .setPhone("555-55-55");
        user.setId(new UserDaoImpl().create(user));
    }

    @Test
    public void findOne() {
        assertThat(user.getRole().getType(), is("USER"));
        assertThat(user.getEmail(), is("ivanov@email.com"));
    }

    @Test
    public void findAll() {
        List<User> users = new UserDaoImpl().findAll();
        assertThat(users.size(), greaterThanOrEqualTo(1));
        assertThat(users, hasItem(user));
    }

    @Test
    public void create() {
        User newUser = new User().setEmail("miller@email.com")
            .setPassw("4321")
            .setRole(new Role(2L, "GUEST"))
            .setPhone("222-22-22");
        Long id = new UserDaoImpl().create(newUser);
        newUser.setId(id);
        User savedUser = new UserDaoImpl().findOne(id);
        assertThat(savedUser, is(newUser));
        assertThat(savedUser.getRole().getType(), is("GUEST"));
        assertThat(savedUser.getEmail(), is("miller@email.com"));
    }

    @Test
    public void update() {
        User newUser = new User().setEmail("miller@email.com")
            .setPassw("4321")
            .setRole(new Role(1L, "USER"))
            .setPhone("222-22-22");
        Long id = new UserDaoImpl().create(newUser);
        newUser.setId(id);
        new UserDaoImpl().update(newUser.setEmail("moreau@email.com"));
        User savedUser = new UserDaoImpl().findOne(id);
        assertThat(savedUser, is(newUser));
        assertThat(savedUser.getRole().getType(), is("USER"));
        assertThat(savedUser.getEmail(), is("moreau@email.com"));
    }

    @Test
    public void updateAll() {
        User newUser1 = new User().setEmail("fernandez@email.com")
            .setPassw("4321")
            .setRole(new Role(1L, "USER"))
            .setPhone("222-22-22");
        newUser1.setId(new UserDaoImpl().create(newUser1));
        User newUser2 = new User().setEmail("lopez@email.com")
            .setPassw("4321")
            .setRole(new Role(1L, "USER"))
            .setPhone("222-22-22");
        newUser2.setId(new UserDaoImpl().create(newUser2));
        List<User> changedUsers = Arrays.asList(newUser1.setEmail("rodriguez@email.com"),
            newUser2.setRole(new Role(2L, "GUEST")));
        new UserDaoImpl().updateAll(changedUsers);
        assertThat(new UserDaoImpl().findOne(newUser1.getId()).getEmail(),
            is("rodriguez@email.com"));
        assertThat(new UserDaoImpl().findOne(newUser2.getId()).getEmail(),
            is("lopez@email.com"));
        assertThat(new UserDaoImpl().findOne(newUser1.getId()).getRole().getType(),
            is("USER"));
        assertThat(new UserDaoImpl().findOne(newUser2.getId()).getRole().getType(),
            is("GUEST"));
    }

    @Test
    public void delete() {
        User newUser = new User().setEmail("kowalski@email.com")
            .setPassw("4321")
            .setRole(new Role(1L, "USER"))
            .setPhone("333-33-33");
        Long id = new UserDaoImpl().create(newUser);
        newUser.setId(id);
        new UserDaoImpl().delete(newUser);
        User foundUser = new UserDaoImpl().findOne(id);
        assertThat(foundUser, nullValue());
    }

    @Test
    public void deleteById() {
        User newUser = new User().setEmail("ruzicka@email.com")
            .setPassw("4321")
            .setRole(new Role(1L, "USER"))
            .setPhone("333-33-33");
        Long id = new UserDaoImpl().create(newUser);
        newUser.setId(id);
        new UserDaoImpl().deleteById(id);
        User savedUser = new UserDaoImpl().findOne(id);
        assertThat(savedUser, nullValue());
    }
}