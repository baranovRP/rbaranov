package ru.job4j.userapp;

import java.util.Optional;
import java.util.Set;

public class UserDaoImpl implements UserDao {

    @Override
    public User getUser(final Integer id) {
        return Optional.ofNullable(
            DbStore.getInstance().getUser(id)).orElse(new User());
    }

    @Override
    public Set<User> getAllUsers() {
        return DbStore.getInstance().getAllUsers();
    }

    @Override
    public User getUserByUserLoginAndPassw(final String login, final String passw) {
        return DbStore.getInstance().findUserByLoginAndPassw(login, passw);
    }

    @Override
    public boolean insertUser(final User user) {
        return DbStore.getInstance().addUser(user);
    }

    @Override
    public boolean updateUser(final User user) {
        return DbStore.getInstance().replace(user.getId(), user);
    }

    @Override
    public boolean deleteUser(final Integer id) {
        return DbStore.getInstance().delete(id);
    }

    /**
     * Check matching of credentials
     *
     * @param login login
     * @param passw passw
     * @return result
     */
    public boolean isCredential(final String login, final String passw) {
        return DbStore.getInstance()
            .findUserByLoginAndPassw(login, passw) != null;
    }

    /**
     * Get role by login and password
     *
     * @param login login
     * @return role
     */
    public Role getUserRole(final String login, final String passw) {
        return DbStore.getInstance()
            .findUserByLoginAndPassw(login, passw).getRole();
    }
}
