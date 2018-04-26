package ru.job4j.hibernate.dao;

import org.hibernate.Session;
import ru.job4j.hibernate.HibernateUtil;
import ru.job4j.hibernate.models.Item;

import java.io.Serializable;
import java.util.List;

/**
 * DAO class for Item.
 */
public class ItemDaoImpl implements ItemDao {

    @Override
    public Item findOne(final Long key) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(Item.class, key);
        }
    }

    @Override
    public List<Item> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Item").list();
        }
    }

    @Override
    public Long create(final Item entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Serializable id = session.save(entity);
            session.getTransaction().commit();
            return (Long) id;
        }
    }

    @Override
    public boolean update(final Item entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
            return findOne(entity.getId()).equals(entity);
        }
    }

    public void updateAll(final List<Item> entities) {
        entities.forEach(this::update);
    }

    @Override
    public boolean delete(final Item entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.remove(entity);
            session.getTransaction().commit();
            return findOne(entity.getId()) == null;
        }
    }

    @Override
    public boolean deleteById(final Long key) {
        return delete(findOne(key));
    }
}
