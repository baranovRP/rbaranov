package ru.job4j.annotationbased;

import org.springframework.stereotype.Component;
import ru.job4j.xmlbased.Storage;
import ru.job4j.xmlbased.User;

import java.util.List;

/**
 * Class MemoryStorage.
 */
@Component
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
