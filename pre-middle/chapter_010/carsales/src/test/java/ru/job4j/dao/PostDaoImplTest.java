package ru.job4j.dao;

import org.junit.Test;
import ru.job4j.dao.car.CarDaoImpl;
import ru.job4j.models.Post;

import java.sql.Timestamp;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;

public class PostDaoImplTest {

    @Test
    public void findOne() {
        Post post = new PostDaoImpl().findOne(1L);
        assertThat(post.getCar().getFuel().getType(), is("DIESEL"));
    }

    @Test
    public void findAll() {
        assertThat(new PostDaoImpl().findAll().size(), greaterThanOrEqualTo(1));
    }

    @Test
    public void create() {
        Post post = new Post().setContent("content")
            .setPrice(5000.0)
            .setIsActive(true)
            .setCar(new CarDaoImpl().findOne(1L))
            .setUser(new UserDaoImpl().findOne(1L))
            .setPublishDate(new Timestamp(System.currentTimeMillis()))
            .setPictures(new ArrayList<>());
        Long id = new PostDaoImpl().create(post);
        post.setId(id);
        Post savedPost = new PostDaoImpl().findOne(id);
        assertThat(savedPost, is(post));
    }

    @Test
    public void update() {
    }

    public void updateAll() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void deleteById() {
    }
}