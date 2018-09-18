package ru.job4j.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.job4j.DispatchFilter;
import ru.job4j.Filter;
import ru.job4j.model.Post;
import ru.job4j.model.Role;
import ru.job4j.model.User;
import ru.job4j.model.car.Car;
import ru.job4j.model.car.parts.*;
import ru.job4j.repository.PictureRepository;
import ru.job4j.repository.PostRepository;
import ru.job4j.repository.car.CarRepository;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DispatchFilterServiceImplTest {

    @MockBean
    private PostRepository repo;
    @Autowired
    private DispatchFilterService dispatchFilterService;
    @Autowired
    private PostService postService;

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
                new Manufacture().setId(1L).setName("BMW")),
            new Transmission().setId(1L),
            2.2D, 45000L, 2015);
        post = new Post().setId(1L).setContent("content").setPrice(5000D)
            .setIsActive(Boolean.TRUE).setCar(car);
        BDDMockito.given(postService.findByCarManufacture("BMW"))
            .willReturn(Arrays.asList(post));
    }

    @Test
    public void sent() {
        List<Post> posts =
            dispatchFilterService.sent(Filter.MANUFACTURE, "BMW");
        assertThat(posts).hasSize(1);
        assertThat(posts.get(0)).isEqualTo(post);
    }

    @TestConfiguration
    static class DispatcherFilterServiceImplContextConfiguration {

        @Autowired
        PostRepository postRepo;
        @Autowired
        CarRepository carRepo;
        @Autowired
        PictureRepository pictureRepo;

        @Bean
        public PostService postService() {
            return new PostServiceImpl(postRepo, carRepo, pictureRepo);
        }

        @Bean
        public DispatchFilterService service() {
            return new DispatchFilterServiceImpl();
        }

        @Bean
        public DispatchFilter filter() {
            return new DispatchFilter(postService());
        }
    }
}