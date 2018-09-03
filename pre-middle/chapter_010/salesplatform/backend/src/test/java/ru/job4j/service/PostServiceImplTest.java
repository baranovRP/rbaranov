package ru.job4j.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.job4j.model.Post;
import ru.job4j.model.Role;
import ru.job4j.model.User;
import ru.job4j.model.car.Car;
import ru.job4j.model.car.parts.*;
import ru.job4j.repository.PictureRepository;
import ru.job4j.repository.PostRepository;
import ru.job4j.repository.car.CarRepository;

import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostServiceImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @MockBean
    PostRepository repo;
    @Autowired
    private PostService service;

    private User user;
    private Role role;
    private Post post;
    private Car car;

    @Before
    public void setUp() {
        role = new Role(1L, "USER");
        user = new User(1L, "ivanov@email.com", "1234", role, "555");
        car = new Car(1L,
            new Body().setId(1L),
            new Category().setId(1L),
            new Fuel().setId(1L),
            new CarModel().setId(1L).setManufacture(
                new Manufacture().setId(1L).setName("Other")),
            new Transmission().setId(1L),
            2.2D, 45000L, 2015);
        post = new Post().setId(1L).setContent("content").setPrice(5000D)
            .setIsActive(Boolean.TRUE).setCar(car);
        BDDMockito.given(repo.save(post)).willReturn(post);
        BDDMockito.given(repo.findAll()).willReturn(Arrays.asList(post));
        BDDMockito.given(repo.findAllByCarManufacture("Other"))
            .willReturn(Arrays.asList(post));
        BDDMockito.given(repo.findAllByPicsIsNotNull())
            .willReturn(Arrays.asList(post));
        BDDMockito.given(repo.findAllByPublishDateToday())
            .willReturn(Arrays.asList(post));
    }

    @Test
    public void addPostAd() {
        Long createdId = service.addPostAd(user, post, new ArrayList<>());
        assertThat(createdId).isEqualTo(1L);
    }

    @Test
    public void editPostAdByExistUser() throws AuthenticationException {
        service.editPostAd(user, post.setUser(user));
    }

    @Test(expected = AuthenticationException.class)
    public void userTryToEditNotHisOwnPost() throws AuthenticationException {
        User nonExistUser = new User().setId(2L).setEmail("petrov@user.com");
        service.editPostAd(user, post.setUser(nonExistUser));
        thrown.expect(AuthenticationException.class);
        thrown.expectMessage(is("User: ivanov@email.com attempts to edit not his own post."));
    }

    @Test
    public void findAll() {
        List<Post> posts = service.findAll();
        assertThat(posts).hasSize(1);
        assertThat(posts.get(0)).isEqualTo(post);
    }

    @Test
    public void findTodaysPosts() {
        List<Post> posts = service.findTodaysPosts();
        assertThat(posts).hasSize(1);
        assertThat(posts.get(0)).isEqualTo(post);
    }

    @Test
    public void findWithPics() {
        List<Post> posts = service.findWithPics();
        assertThat(posts).hasSize(1);
        assertThat(posts.get(0)).isEqualTo(post);
    }

    @Test
    public void findByCarManufacture() {
        List<Post> posts = service.findByCarManufacture("Other");
        assertThat(posts).hasSize(1);
        assertThat(posts.get(0)).isEqualTo(post);
    }

    @TestConfiguration
    static class PostServiceImplTestContextConfiguration {

        @Autowired
        PostRepository postRepo;
        @Autowired
        CarRepository carRepo;
        @Autowired
        PictureRepository pictureRepo;

        @Bean
        public PostService service() {
            return new PostServiceImpl(postRepo, carRepo, pictureRepo);
        }
    }

}