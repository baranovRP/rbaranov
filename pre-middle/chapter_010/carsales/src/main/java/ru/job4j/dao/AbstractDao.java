package ru.job4j.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.job4j.HibernateUtil;

import java.util.List;
import java.util.function.Function;

public interface AbstractDao<T> {

    T findOne(Long key);

    List<T> findAll();

    default  <T> T fetchTx(final Function<Session, T> command) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        final Transaction tx = session.beginTransaction();
        try {
            return command.apply(session);
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            tx.commit();
            session.close();
        }
    }
}
