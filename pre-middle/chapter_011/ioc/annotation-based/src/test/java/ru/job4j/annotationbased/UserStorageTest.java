package ru.job4j.annotationbased;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.job4j.xmlbased.Storage;
import ru.job4j.xmlbased.User;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UserStorageTest {

    @Test
    public void whenAddUserToStorageUsingComponentScanShouldSave() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        Storage memory = context.getBean(Storage.class);
        UserStorage storage = new UserStorage(memory);
        assertThat(storage.add(new User()), is(1L));
    }
}