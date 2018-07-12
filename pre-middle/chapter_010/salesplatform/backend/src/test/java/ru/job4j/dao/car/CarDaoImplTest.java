package ru.job4j.dao.car;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.dao.car.parts.*;
import ru.job4j.model.car.Car;
import ru.job4j.model.car.parts.*;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CarDaoImplTest {

    private Car car;

    @Before
    public void setUp() {
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
    }

    @Test
    public void findOne() {
        assertThat(new CarDaoImpl().findOne(car.getId()).getBody().getType(),
            is("HATCHBACK"));
    }

    @Test
    public void findAll() {
        List<Car> cars = new CarDaoImpl().findAll();
        assertThat(cars.size(), greaterThanOrEqualTo(1));
        assertThat(cars, hasItem(car));
    }

    @Test
    public void create() {
        Car newCar = new Car().setBody(new BodyDaoImpl().findOne(2L))
            .setCategory(new CategoryDaoImpl().findOne(2L))
            .setFuel(new FuelDaoImpl().findOne(2L))
            .setCarModel(new CarModelDaoImpl().findOne(2L))
            .setTransmission(new TransmissionDaoImpl().findOne(2L))
            .setEngineSize(4.0)
            .setMileage(30000L)
            .setYear(2008);
        Long id = new CarDaoImpl().create(newCar);
        newCar.setId(id);
        Car savedCar = new CarDaoImpl().findOne(id);
        assertThat(savedCar, is(newCar));
        assertThat(savedCar.getBody().getType(), is("HATCHBACK"));
    }

    @Test
    public void update() {
        Car newCar = new Car().setBody(new BodyDaoImpl().findOne(2L))
            .setCategory(new CategoryDaoImpl().findOne(2L))
            .setFuel(new FuelDaoImpl().findOne(2L))
            .setCarModel(new CarModelDaoImpl().findOne(2L))
            .setTransmission(new TransmissionDaoImpl().findOne(2L))
            .setEngineSize(4.0)
            .setMileage(30000L)
            .setYear(2008);
        newCar.setId(new CarDaoImpl().create(newCar));
        newCar = new CarDaoImpl().findOne(newCar.getId());
        new CarDaoImpl().update(newCar.setYear(2000));
        Car savedCar = new CarDaoImpl().findOne(newCar.getId());
        assertThat(savedCar, is(newCar));
        assertThat(savedCar.getYear(), is(2000));
    }

    public void updateAll() {
    }

    @Test
    public void delete() {
        Car newCar = new Car().setBody(new BodyDaoImpl().findOne(2L))
            .setCategory(new CategoryDaoImpl().findOne(2L))
            .setFuel(new FuelDaoImpl().findOne(2L))
            .setCarModel(new CarModelDaoImpl().findOne(2L))
            .setTransmission(new TransmissionDaoImpl().findOne(2L))
            .setEngineSize(4.0)
            .setMileage(30000L)
            .setYear(2008);
        newCar.setId(new CarDaoImpl().create(newCar));
        newCar = new CarDaoImpl().findOne(newCar.getId());
        new CarDaoImpl().delete(newCar);
        Car foundCar = new CarDaoImpl().findOne(newCar.getId());
        assertThat(foundCar, nullValue());
    }

    @Test
    public void deleteById() {
        Car newCar = new Car().setBody(new BodyDaoImpl().findOne(2L))
            .setCategory(new CategoryDaoImpl().findOne(2L))
            .setFuel(new FuelDaoImpl().findOne(2L))
            .setCarModel(new CarModelDaoImpl().findOne(2L))
            .setTransmission(new TransmissionDaoImpl().findOne(2L))
            .setEngineSize(4.0)
            .setMileage(30000L)
            .setYear(2008);
        newCar.setId(new CarDaoImpl().create(newCar));
        newCar = new CarDaoImpl().findOne(newCar.getId());
        new CarDaoImpl().deleteById(newCar.getId());
        Car foundCar = new CarDaoImpl().findOne(newCar.getId());
        assertThat(foundCar, nullValue());
    }
}