package ru.job4j.xmlbased;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UserStorageTest {

    @Test
    public void whenAddUserToStorageShouldSave() {
        Storage memory = new MemoryStorage();
        UserStorage storage = new UserStorage(memory);
        assertThat(storage.add(new User()), is(1L));
    }

    @Test
    public void whenAddUserToStorageUsingLoadContextShouldSave() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        UserStorage storage = context.getBean(UserStorage.class);
        assertThat(storage.add(new User()), is(1L));
    }
}