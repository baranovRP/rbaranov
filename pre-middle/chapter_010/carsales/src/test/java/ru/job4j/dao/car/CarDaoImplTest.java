package ru.job4j.dao.car;

import org.junit.Test;
import ru.job4j.dao.car.parts.*;
import ru.job4j.models.car.Car;

import java.sql.Timestamp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

public class CarDaoImplTest {

    @Test
    public void findOne() {
        Car car = new CarDaoImpl().findOne(1L);
        assertThat(car.getBody().getType(), is("HATCHBACK"));
    }

    @Test
    public void findAll() {
        assertThat(new CarDaoImpl().findAll(), hasSize(7));
    }

    @Test
    public void create() {
        Car car = new Car().setBody(new BodyDaoImpl().findOne(2L))
            .setCategory(new CategoryDaoImpl().findOne(2L))
            .setFuel(new FuelDaoImpl().findOne(2L))
            .setCarModel(new CarModelDaoImpl().findOne(2L))
            .setTransmission(new TransmissionDaoImpl().findOne(2L))
            .setEngineSize(4.0)
            .setMileage(30000L)
            .setYear(new Timestamp(System.currentTimeMillis()));
        Long id = new CarDaoImpl().create(car);
        car.setId(id);
        Car savedCar = new CarDaoImpl().findOne(id);
        assertThat(savedCar, is(car));
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