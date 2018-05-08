package ru.job4j;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Singleton Hibernate instance.
 */
public class HibernateUtil {

    private static final Logger LOG =
        LoggerFactory.getLogger(HibernateUtil.class);
    private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

    private HibernateUtil() {
    }

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new HibernateException(
                "There was an error building the factory");
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    public void closeSessionFactory() {
        try {
            SESSION_FACTORY.close();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new HibernateException(
                "There was an error closing the factory");
        }
    }
}