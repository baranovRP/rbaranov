package ru.job4j.dao.car.parts;

import org.junit.Test;
import ru.job4j.models.car.parts.Transmission;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TransmissionDaoImplTest {

    @Test
    public void findOne() {
        Transmission manufacture = new TransmissionDaoImpl().findOne(1L);
        assertThat(manufacture.getType(), is("AUTOMATIC"));
    }

    @Test
    public void findAll() {
        assertThat(new TransmissionDaoImpl().findAll(), hasSize(4));
    }
}