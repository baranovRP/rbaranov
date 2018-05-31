package ru.job4j;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.job4j.dao.PictureDaoImpl;
import ru.job4j.dao.PostDaoImpl;
import ru.job4j.dao.car.CarDaoImpl;
import ru.job4j.model.Picture;
import ru.job4j.model.Post;
import ru.job4j.model.User;
import ru.job4j.model.car.Car;

import javax.naming.AuthenticationException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

public class PostRepository {

    public List<Picture> findByPostId(final Long postId) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        Post post = new PostDaoImpl().findOne(postId);
        final Transaction tx = session.beginTransaction();
        try {
            Query query = session.createQuery("from Picture where post=:post");
            query.setParameter("post", post);
            return (List<Picture>) Optional.ofNullable(query.list())
                .orElse(new CopyOnWriteArrayList<>());
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            tx.commit();
            session.close();
        }
    }

    public Long addPostAd(final User user, final Post post, final List<byte[]> pics) {
        post.setPublishDate(Timestamp.valueOf(LocalDateTime.now()));
        post.setUser(user);
        Long carId = new CarDaoImpl().create(post.getCar());
        post.setCar(new Car(carId));
        Long id = new PostDaoImpl().create(post);
        Post publishedPost = new PostDaoImpl().findOne(id);
        pics.forEach(pic ->
            new PictureDaoImpl().create(new Picture(pic, publishedPost)));
        return id;
    }

    public void editPostAd(final User user, final Post post) throws AuthenticationException {
        post.setPictures(new PostRepository().findByPostId(post.getId()));
        if (user.getId().equals(post.getUser().getId())) {
            new PostDaoImpl().update(post);
        }
        throw new AuthenticationException(
            String.format("User: %s attempts to edit hiw own post.", user.getEmail()));
    }
}
