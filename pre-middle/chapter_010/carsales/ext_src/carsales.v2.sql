-- Create db
DROP DATABASE sales_car_platform;
CREATE DATABASE sales_car_platform;

-- create and initialize tables
DROP TABLE IF EXISTS body CASCADE;

CREATE TABLE IF NOT EXISTS body (
    id          SERIAL PRIMARY KEY,
    type        CHARACTER VARYING(100) UNIQUE NOT NULL,
    description TEXT
);

INSERT INTO body (type, description) VALUES ('HATCHBACK', 'Hatchback');
INSERT INTO body (type, description) VALUES ('SALOON', 'Saloon');
INSERT INTO body (type, description) VALUES ('ESTATE', 'Estate');
INSERT INTO body (type, description) VALUES ('MPV', 'MPV');
INSERT INTO body (type, description) VALUES ('COUPE', 'Coupe');
INSERT INTO body (type, description) VALUES ('CONVERTIBLE', 'Convertible');
INSERT INTO body (type, description) VALUES ('OTHER', 'Other');


DROP TABLE IF EXISTS fuel CASCADE;

CREATE TABLE IF NOT EXISTS fuel (
    id          SERIAL PRIMARY KEY,
    type        CHARACTER VARYING(100) UNIQUE NOT NULL,
    description TEXT
);

INSERT INTO fuel (type, description) VALUES ('DIESEL', 'Diesel');
INSERT INTO fuel (type, description) VALUES ('ELECTRIC', 'Electric');
INSERT INTO fuel (type, description) VALUES ('GAS', 'Gas');
INSERT INTO fuel (type, description)
VALUES ('HYBRID_ELECTRIC', 'Hybrid Electric');
INSERT INTO fuel (type, description) VALUES ('PETROL', 'Petrol');
INSERT INTO fuel (type, description) VALUES ('OTHER', 'Other');


DROP TABLE IF EXISTS transmission CASCADE;

CREATE TABLE IF NOT EXISTS transmission (
    id          SERIAL PRIMARY KEY,
    type        CHARACTER VARYING(100) UNIQUE NOT NULL,
    description TEXT
);

INSERT INTO transmission (type, description) VALUES ('AUTOMATIC', 'Automatic');
INSERT INTO transmission (type, description) VALUES ('MANUAL', 'Manual');
INSERT INTO transmission (type, description) VALUES ('SEMI_AUTO', 'Semi-Auto');
INSERT INTO transmission (type, description) VALUES ('OTHER', 'Other');


DROP TABLE IF EXISTS category CASCADE;

CREATE TABLE IF NOT EXISTS category (
    id          SERIAL PRIMARY KEY,
    type        CHARACTER VARYING(100) UNIQUE NOT NULL,
    description TEXT
);

INSERT INTO category (type, description) VALUES ('CITY', 'City cars');
INSERT INTO category (type, description) VALUES ('SMALL', 'Small cars');
INSERT INTO category (type, description) VALUES ('MEDIUM', 'Medium cars');
INSERT INTO category (type, description) VALUES ('LARGE', 'Large cars');
INSERT INTO category (type, description) VALUES ('EXECUTIVE', 'Executive cars');
INSERT INTO category (type, description) VALUES ('LUXURY', 'Luxury cars');
INSERT INTO category (type, description) VALUES ('SPORT', 'Sport cars');
INSERT INTO category (type, description)
VALUES ('MPV', 'Multi Purpose Vehicles');
INSERT INTO category (type, description) VALUES ('OTHER', 'Other');


DROP TABLE IF EXISTS manufacture CASCADE;

CREATE TABLE IF NOT EXISTS manufacture (
    id   SERIAL PRIMARY KEY,
    name TEXT NOT NULL UNIQUE
);

INSERT INTO manufacture (name) VALUES ('BMW');
INSERT INTO manufacture (name) VALUES ('Audi');
INSERT INTO manufacture (name) VALUES ('Honda');
INSERT INTO manufacture (name) VALUES ('Ford');
INSERT INTO manufacture (name) VALUES ('Ferrari');
INSERT INTO manufacture (name) VALUES ('Mercedes');
INSERT INTO manufacture (name) VALUES ('General Motors');
INSERT INTO manufacture (name) VALUES ('Bentley');
INSERT INTO manufacture (name) VALUES ('Other');


DROP TABLE IF EXISTS car_model CASCADE;

CREATE TABLE IF NOT EXISTS car_model (
    id             SERIAL PRIMARY KEY,
    name           TEXT    NOT NULL UNIQUE,
    manufacture_id INTEGER NOT NULL REFERENCES manufacture (id) ON DELETE SET NULL
);

INSERT INTO car_model (name, manufacture_id) VALUES ('5-series', 1);
INSERT INTO car_model (name, manufacture_id) VALUES ('M2', 1);
INSERT INTO car_model (name, manufacture_id) VALUES ('3-series Wagon', 1);
INSERT INTO car_model (name, manufacture_id) VALUES ('A4', 2);
INSERT INTO car_model (name, manufacture_id) VALUES ('A8', 2);
INSERT INTO car_model (name, manufacture_id) VALUES ('Q7', 2);
INSERT INTO car_model (name, manufacture_id) VALUES ('Accord', 3);
INSERT INTO car_model (name, manufacture_id) VALUES ('Civic', 3);
INSERT INTO car_model (name, manufacture_id) VALUES ('BR-V', 3);
INSERT INTO car_model (name, manufacture_id) VALUES ('FIESTA', 4);
INSERT INTO car_model (name, manufacture_id) VALUES ('FOCUS', 4);
INSERT INTO car_model (name, manufacture_id) VALUES ('C-MAX', 4);
INSERT INTO car_model (name, manufacture_id) VALUES ('F12 Berlinetta', 5);
INSERT INTO car_model (name, manufacture_id) VALUES ('458 Italia', 5);
INSERT INTO car_model (name, manufacture_id) VALUES ('Enzo', 5);
INSERT INTO car_model (name, manufacture_id) VALUES ('L 319', 6);
INSERT INTO car_model (name, manufacture_id) VALUES ('SLR McLaren', 6);
INSERT INTO car_model (name, manufacture_id) VALUES ('Vito van', 6);
INSERT INTO car_model (name, manufacture_id) VALUES ('Chevrolet Bolt EV', 7);
INSERT INTO car_model (name, manufacture_id) VALUES ('Cadillac ATS Coupe', 7);
INSERT INTO car_model (name, manufacture_id) VALUES ('Buick Cascada', 7);
INSERT INTO car_model (name, manufacture_id) VALUES ('Brooklands', 8);
INSERT INTO car_model (name, manufacture_id) VALUES ('Azure', 8);
INSERT INTO car_model (name, manufacture_id) VALUES ('Mark VI', 8);
INSERT INTO car_model (name, manufacture_id) VALUES ('Other', 9);


DROP TABLE IF EXISTS cars CASCADE;

CREATE TABLE IF NOT EXISTS cars (
    id              SERIAL PRIMARY KEY,
    body_id         INTEGER          NOT NULL REFERENCES body (id) ON DELETE SET NULL,
    category_id     INTEGER          NOT NULL REFERENCES category (id) ON DELETE SET NULL,
    fuel_id         INTEGER          NOT NULL REFERENCES fuel (id) ON DELETE SET NULL,
    model_id        INTEGER          NOT NULL REFERENCES car_model (id) ON DELETE SET NULL,
    transmission_id INTEGER          NOT NULL REFERENCES transmission (id) ON DELETE SET NULL,
    engine_size     DOUBLE PRECISION NOT NULL,
    mileage         INTEGER          NOT NULL,
    year            TIMESTAMP        NOT NULL
);

INSERT INTO cars (body_id, category_id, fuel_id, model_id, transmission_id, engine_size, mileage, year)
VALUES (1, 1, 1, 1, 1, 1.2, 78000, '2008');


DROP TABLE IF EXISTS role CASCADE;

CREATE TABLE IF NOT EXISTS role (
    id   SERIAL PRIMARY KEY,
    type CHARACTER VARYING(100) UNIQUE NOT NULL
);

INSERT INTO role (type) VALUES ('USER');
INSERT INTO role (type) VALUES ('GUEST');


DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE IF NOT EXISTS users (
    id      SERIAL PRIMARY KEY,
    email   CHARACTER VARYING(100) UNIQUE NOT NULL,
    passw   CHARACTER VARYING(100)        NOT NULL,
    role_id INTEGER REFERENCES role (id) ON DELETE SET NULL,
    phone   TEXT
);

INSERT INTO users (email, passw, role_id, phone) VALUES (
    'ivanov@email.com',
    '1234',
    '1',
    '555-55-55'
);


DROP TABLE IF EXISTS posts CASCADE;

CREATE TABLE IF NOT EXISTS posts (
    id           SERIAL PRIMARY KEY,
    content      TEXT,
    price        DOUBLE PRECISION NOT NULL,
    is_active    BOOLEAN          NOT NULL,
    car_id       INTEGER REFERENCES cars (id) ON DELETE SET NULL,
    user_id      INTEGER REFERENCES users (id) ON DELETE SET NULL,
    publish_date TIMESTAMP        NOT NULL DEFAULT now()
);

INSERT INTO posts (content, price, is_active, car_id, user_id)
VALUES ('advertisment', 2000.0, TRUE, 1, 1);


DROP TABLE IF EXISTS pictures CASCADE;

CREATE TABLE IF NOT EXISTS pictures (
    id      SERIAL PRIMARY KEY,
    data    BYTEA,
    post_id INTEGER REFERENCES posts (id) ON DELETE SET NULL
);
