package ru.job4j.dao.car.parts;

import ru.job4j.dao.AbstractDao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class CarPartDaoImpl<T> implements AbstractDao<T> {

    private final Class<T> persistentClass =
        (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
            .getActualTypeArguments()[0];

    @Override
    public T findOne(final Long key) {
        return this.fetchTx(session -> session.find(persistentClass, key));
    }

    @Override
    public List<T> findAll() {
        return this.fetchTx(session -> session.createQuery(
            String.format("from %s", persistentClass.getSimpleName())).list());
    }
}
