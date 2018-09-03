package ru.job4j.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.util.Pair;
import org.springframework.test.context.junit4.SpringRunner;
import ru.job4j.model.Role;
import ru.job4j.model.User;
import ru.job4j.repository.UserRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @MockBean
    private UserRepository repo;
    @Autowired
    private UserService service;

    private User user;
    private Role role;
    private Pair<String, String> credentials;

    @Before
    public void setUp() {
        role = new Role(1L, "USER");
        user = new User(1L, "ivanov@email.com", "1234", role, "555");
        Optional<User> optionalUser = Optional.of(user);
        credentials = Pair.of("ivanov@email.com", "1234");
        BDDMockito.given(repo.findByEmailIgnoreCaseAndPassw(
            credentials.getFirst(), credentials.getSecond()))
            .willReturn(optionalUser);
        BDDMockito.given(repo.findByEmailIgnoreCase(credentials.getFirst()))
            .willReturn(optionalUser);
        BDDMockito.given(repo.save(user)).willReturn(user);
    }

    @Test
    public void findByEmailPassw() {
        User found =
            service.findByEmailPassw(credentials.getFirst(), credentials.getSecond());
        assertThat(found).isEqualTo(user);
        assertThat(found.getPhone()).isEqualToIgnoringCase("555");
    }

    @Test
    public void findByExistEmail() {
        User found = service.findByEmail(credentials.getFirst());
        assertThat(found).isEqualTo(user);
        assertThat(found.getRole()).isEqualTo(role);
    }

    @Test(expected = IllegalStateException.class)
    public void findByNonExistEmailThrowIllegalStateException() {
        service.findByEmail("nonexist@email.com");
    }

    @Test
    public void isCredentialWithCorrectCredentialReturnTrue() {
        boolean isCredential =
            service.isCredential(credentials.getFirst(), credentials.getSecond());
        assertTrue(isCredential);
    }

    @Test
    public void isCredentialWithIncorrectCredentialReturnFalse() {
        boolean isCredential =
            service.isCredential(credentials.getFirst(), "2222");
        assertFalse(isCredential);
    }


    @Test
    public void create() {
        Long createdId = service.create(user);
        assertThat(createdId).isEqualTo(1L);
    }

    @TestConfiguration
    static class UserServiceImplTestContextConfiguration {

        @Autowired
        UserRepository repo;

        @Bean
        public UserService userService() {
            return new UserServiceImpl(repo);
        }
    }
}