package ru.job4j.models;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Class Car model.
 */
public class Car {

    private Long id;
    private String manufacturer;
    private String model;
    private Transmission transmission;
    private Engine engine;
    private Gearbox gearbox;
    private Timestamp createDate;

    public Car() {
    }

    public Car(final String manufacturer, final String model,
               final Transmission transmission, final Engine engine,
               final Gearbox gearbox) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.transmission = transmission;
        this.engine = engine;
        this.gearbox = gearbox;
    }

    public Car(final Long id, final String manufacturer, final String model,
               final Transmission transmission, final Engine engine,
               final Gearbox gearbox) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.transmission = transmission;
        this.engine = engine;
        this.gearbox = gearbox;
    }

    public Car(final Long id, final String manufacturer, final String model,
               final Transmission transmission, final Engine engine,
               final Gearbox gearbox, final Timestamp createDate) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.transmission = transmission;
        this.engine = engine;
        this.gearbox = gearbox;
        this.createDate = createDate;
    }

    public Long getId() {
        return id;
    }

    public Car setId(final Long id) {
        this.id = id;
        return this;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public Car setManufacturer(final String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public String getModel() {
        return model;
    }

    public Car setModel(final String model) {
        this.model = model;
        return this;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public Car setTransmission(final Transmission transmission) {
        this.transmission = transmission;
        return this;
    }

    public Engine getEngine() {
        return engine;
    }

    public Car setEngine(final Engine engine) {
        this.engine = engine;
        return this;
    }

    public Gearbox getGearbox() {
        return gearbox;
    }

    public Car setGearbox(final Gearbox gearbox) {
        this.gearbox = gearbox;
        return this;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public Car setCreateDate(final Timestamp createDate) {
        this.createDate = createDate;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car that = (Car) o;
        return Objects.equals(this.id, that.id)
            && Objects.equals(this.manufacturer, that.manufacturer)
            && Objects.equals(this.model, that.model)
            && Objects.equals(this.transmission, that.transmission)
            && Objects.equals(this.engine, that.engine)
            && Objects.equals(this.gearbox, that.gearbox)
            && Objects.equals(this.createDate, that.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, manufacturer, model, transmission, engine,
            gearbox, createDate);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
            .add("id = " + id)
            .add("manufacturer = " + manufacturer)
            .add("model = " + model)
            .add("transmission = " + transmission)
            .add("engine = " + engine)
            .add("gearbox = " + gearbox)
            .add("createDate = " + createDate)
            .toString();
    }
}
