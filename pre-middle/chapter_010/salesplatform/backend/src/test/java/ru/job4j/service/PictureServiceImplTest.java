package ru.job4j.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.job4j.model.Picture;
import ru.job4j.repository.PictureRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class PictureServiceImplTest {

    @MockBean
    private PictureRepository repo;
    @Autowired
    private PictureService service;
    private Picture pic;

    @Before
    public void setUp() {
        pic = new Picture().setId(1L);
        Optional<Picture> optionalPic = Optional.of(pic);
        BDDMockito.given(repo.findById(1L)).willReturn(optionalPic);
        BDDMockito.given(repo.findPicsByPostId(1L)).willReturn(Arrays.asList(pic));
    }

    @Test
    public void findExistPicture() {
        Picture found = service.findPicture(1L);
        assertThat(found).isEqualTo(pic);
    }

    @Test(expected = IllegalStateException.class)
    public void findNonExistPicture() {
        service.findPicture(2L);
    }

    @Test
    public void findPicsByPostId() {
        List<Picture> pics = service.findPicsByPostId(1L);
        assertThat(pics).hasSize(1);
        assertThat(pics.get(0)).isEqualTo(pic);
    }

    @TestConfiguration
    static class PictureServiceImplTestContextConfiguration {
        @Autowired
        PictureRepository repo;

        @Bean
        public PictureService service() {
            return new PictureServiceImpl(repo);
        }
    }
}