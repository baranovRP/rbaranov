package ru.job4j.xmlbased;

import java.util.List;

public class MemoryStorage implements Storage<User, Long> {

    @Override
    public User findOne(final Long key) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Long create(final User entity) {
        return 1L;
    }

    @Override
    public void update(final User entity) {

    }

    @Override
    public void delete(final User entity) {

    }

    @Override
    public void deleteById(final Long key) {

    }
}
