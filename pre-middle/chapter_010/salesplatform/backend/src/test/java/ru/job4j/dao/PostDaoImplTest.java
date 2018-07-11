package ru.job4j.dao;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.dao.car.CarDaoImpl;
import ru.job4j.model.Post;
import ru.job4j.model.Role;
import ru.job4j.model.User;
import ru.job4j.model.car.Car;
import ru.job4j.model.car.parts.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PostDaoImplTest {

    private User user;
    private Car car;
    private Post post;

    @Before
    public void setUp() {
        user = new User()
            .setEmail("ivanov@email.com")
            .setPassw("1234")
            .setRole(new Role(1L, "USER"))
            .setPhone("555-55-55");
        user.setId(new UserDaoImpl().create(user));
        car = new Car()
            .setBody(new Body().setId(2L))
            .setCategory(new Category().setId(2L))
            .setFuel(new Fuel().setId(2L))
            .setCarModel(new CarModel().setId(2L))
            .setTransmission(new Transmission().setId(2L))
            .setEngineSize(1.2)
            .setMileage(4000L)
            .setYear(2018);
        car.setId(new CarDaoImpl().create(car));
        car = new CarDaoImpl().findOne(car.getId());
        post = new Post()
            .setContent("description")
            .setPrice(35000D)
            .setIsActive(Boolean.TRUE)
            .setCar(car)
            .setUser(user);
        post.setId(new PostDaoImpl().create(post));
        post = new PostDaoImpl().findOne(post.getId());
    }

    @Test
    public void findOne() {
        Post existingPost = new PostDaoImpl().findOne(post.getId());
        assertThat(existingPost.getCar().getFuel().getType(), is("DIESEL"));
        assertThat(existingPost.getUser().getEmail(), is("ivanov@email.com"));
    }

    @Test
    public void findAll() {
        List<Post> posts = new PostDaoImpl().findAll();
        List<String> emails = posts.stream()
            .map(Post::getUser)
            .map(User::getEmail)
            .collect(Collectors.toList());
        assertThat(posts.size(), greaterThanOrEqualTo(1));
        assertThat("ivanov@email.com", isIn(emails));
    }

    @Test
    public void create() {
        Post newPost = new Post()
            .setContent("steep wheelbarrow")
            .setPrice(17000D)
            .setIsActive(Boolean.FALSE)
            .setCar(car)
            .setUser(user);
        newPost.setId(new PostDaoImpl().create(newPost));
        newPost = new PostDaoImpl().findOne(newPost.getId());
        Post savedPost = new PostDaoImpl().findOne(newPost.getId());
        assertThat(savedPost, is(newPost));
        assertThat(savedPost.getCar().getFuel().getType(), is("DIESEL"));
        assertThat(savedPost.getUser().getEmail(), is("ivanov@email.com"));
        assertThat(savedPost.getContent(), is("steep wheelbarrow"));
    }

    @Test
    public void update() {
        Post newPost = new Post()
            .setContent("steep wheelbarrow")
            .setPrice(17000D)
            .setIsActive(Boolean.FALSE)
            .setCar(car)
            .setUser(user);
        newPost.setId(new PostDaoImpl().create(newPost));
        newPost = new PostDaoImpl().findOne(newPost.getId());
        new PostDaoImpl().update(newPost.setPrice(3000D));
        Post savedPost = new PostDaoImpl().findOne(newPost.getId());
        assertThat(savedPost, is(newPost));
        assertThat(savedPost.getCar().getFuel().getType(), is("DIESEL"));
        assertThat(savedPost.getUser().getEmail(), is("ivanov@email.com"));
        assertThat(savedPost.getPrice(), is(3000D));
    }

    public void updateAll() {
    }

    @Test
    public void delete() {
        Post newPost = new Post()
            .setContent("steep wheelbarrow")
            .setPrice(17000D)
            .setIsActive(Boolean.FALSE)
            .setCar(car)
            .setUser(user);
        newPost.setId(new PostDaoImpl().create(newPost));
        assertThat(newPost.getId(), notNullValue());
        new PostDaoImpl().delete(newPost);
        Post foundPost = new PostDaoImpl().findOne(newPost.getId());
        assertThat(foundPost, nullValue());
    }

    @Test
    public void deleteById() {
        Post newPost = new Post()
            .setContent("steep wheelbarrow")
            .setPrice(17000D)
            .setIsActive(Boolean.FALSE)
            .setCar(car)
            .setUser(user);
        newPost.setId(new PostDaoImpl().create(newPost));
        assertThat(newPost.getId(), notNullValue());
        new PostDaoImpl().deleteById(newPost.getId());
        Post foundPost = new PostDaoImpl().findOne(newPost.getId());
        assertThat(foundPost, nullValue());
    }
}