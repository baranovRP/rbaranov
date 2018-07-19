package ru.job4j.annotationbased;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.job4j.xmlbased.Storage;
import ru.job4j.xmlbased.User;

/**
 * Class UserStorage.
 */
@Component
public class UserStorage {

    final Storage storage;

    @Autowired
    public UserStorage(final Storage storage) {
        this.storage = storage;
    }

    public Long add(final User entity) {
        return (Long) this.storage.create(entity);
    }
}
