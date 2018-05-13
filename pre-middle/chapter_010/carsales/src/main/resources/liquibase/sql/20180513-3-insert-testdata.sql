-- insert cars
INSERT INTO cars (body_id, category_id, fuel_id, model_id, transmission_id, engine_size, mileage, year)
VALUES (1, 1, 1, 1, 1, 1.2, 78000, '2008-01-01 00:00:00');

-- insert users
INSERT INTO users (email, passw, role_id, phone) VALUES (
    'ivanov@email.com',
    '1234',
    '1',
    '555-55-55'
);

-- insert posts
INSERT INTO posts (content, price, is_active, car_id, user_id)
VALUES ('advertisment', 2000.0, TRUE, 1, 1);
