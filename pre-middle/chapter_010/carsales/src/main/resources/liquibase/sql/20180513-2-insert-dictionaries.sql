-- insert body dictionary
INSERT INTO body (type, description) VALUES ('HATCHBACK', 'Hatchback');
INSERT INTO body (type, description) VALUES ('SALOON', 'Saloon');
INSERT INTO body (type, description) VALUES ('ESTATE', 'Estate');
INSERT INTO body (type, description) VALUES ('MPV', 'MPV');
INSERT INTO body (type, description) VALUES ('COUPE', 'Coupe');
INSERT INTO body (type, description) VALUES ('CONVERTIBLE', 'Convertible');
INSERT INTO body (type, description) VALUES ('OTHER', 'Other');

-- insert fuel dictionary
INSERT INTO fuel (type, description) VALUES ('DIESEL', 'Diesel');
INSERT INTO fuel (type, description) VALUES ('ELECTRIC', 'Electric');
INSERT INTO fuel (type, description) VALUES ('GAS', 'Gas');
INSERT INTO fuel (type, description) VALUES ('HYBRID_ELECTRIC', 'Hybrid Electric');
INSERT INTO fuel (type, description) VALUES ('PETROL', 'Petrol');
INSERT INTO fuel (type, description) VALUES ('OTHER', 'Other');

-- insert transmission dictionary
INSERT INTO transmission (type, description) VALUES ('AUTOMATIC', 'Automatic');
INSERT INTO transmission (type, description) VALUES ('MANUAL', 'Manual');
INSERT INTO transmission (type, description) VALUES ('SEMI_AUTO', 'Semi-Auto');
INSERT INTO transmission (type, description) VALUES ('OTHER', 'Other');

-- insert category dictionary
INSERT INTO category (type, description) VALUES ('CITY', 'City cars');
INSERT INTO category (type, description) VALUES ('SMALL', 'Small cars');
INSERT INTO category (type, description) VALUES ('MEDIUM', 'Medium cars');
INSERT INTO category (type, description) VALUES ('LARGE', 'Large cars');
INSERT INTO category (type, description) VALUES ('EXECUTIVE', 'Executive cars');
INSERT INTO category (type, description) VALUES ('LUXURY', 'Luxury cars');
INSERT INTO category (type, description) VALUES ('SPORT', 'Sport cars');
INSERT INTO category (type, description) VALUES ('MPV', 'Multi Purpose Vehicles');
INSERT INTO category (type, description) VALUES ('OTHER', 'Other');

-- insert manufacture dictionary
INSERT INTO manufacture (name) VALUES ('BMW');
INSERT INTO manufacture (name) VALUES ('Audi');
INSERT INTO manufacture (name) VALUES ('Honda');
INSERT INTO manufacture (name) VALUES ('Ford');
INSERT INTO manufacture (name) VALUES ('Ferrari');
INSERT INTO manufacture (name) VALUES ('Mercedes');
INSERT INTO manufacture (name) VALUES ('General Motors');
INSERT INTO manufacture (name) VALUES ('Bentley');
INSERT INTO manufacture (name) VALUES ('Other');


-- insert car_model dictionary
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

-- insert role dictionary
INSERT INTO role (type) VALUES ('USER');
INSERT INTO role (type) VALUES ('GUEST');
