package ru.job4j.generic;

/**
 * @author Roman Baranov (baranov.rp@gmail.com)
 * @version 1
 */
public class UserStore extends AbstractStore<User> implements Store<User> {


    public UserStore(int size) {
        super(size);
    }

    @Override
    public void add(User user) {
        super.add(user);
    }

    @Override
    public boolean replace(String id, User user) {
        return super.replace(id, user);
    }

    @Override
    public boolean delete(String id) {
        return super.delete(id);
    }

    @Override
    public User findById(String id) {
        return super.findById(id);
    }
}
