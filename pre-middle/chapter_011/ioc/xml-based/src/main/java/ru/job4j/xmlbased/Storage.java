package ru.job4j.xmlbased;

import java.util.List;

/**
 * Interface Storage.
 */
public interface Storage<T, K> {
    T findOne(K key);

    List<T> findAll();

    K create(T entity);

    void update(T entity);

    void delete(T entity);

    void deleteById(K key);
}
