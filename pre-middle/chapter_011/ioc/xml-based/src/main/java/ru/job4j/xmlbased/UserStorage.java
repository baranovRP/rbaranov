package ru.job4j.xmlbased;

public class UserStorage {

    final Storage storage;

    public UserStorage(final Storage storage) {
        this.storage = storage;
    }

    public Long add(final User entity) {
        return (Long) this.storage.create(entity);
    }
}
