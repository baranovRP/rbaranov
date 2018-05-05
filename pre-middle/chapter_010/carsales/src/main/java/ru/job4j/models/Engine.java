package ru.job4j.models;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Class Engine model.
 */
public class Engine {

    private Long id;
    private String type;
    private String description;

    public Engine() {
    }

    public Engine(final String type, final String description) {
        this.type = type;
        this.description = description;
    }

    public Engine(final Long id, final String type, final String description) {
        this.id = id;
        this.type = type;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public Engine setId(final Long id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public Engine setType(final String type) {
        this.type = type;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Engine setDescription(final String description) {
        this.description = description;
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
        Engine that = (Engine) o;
        return Objects.equals(this.id, that.id)
            && Objects.equals(this.type, that.type)
            && Objects.equals(this.description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, description);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
            .add("id = " + id)
            .add("type = " + type)
            .add("description = " + description)
            .toString();
    }
}
