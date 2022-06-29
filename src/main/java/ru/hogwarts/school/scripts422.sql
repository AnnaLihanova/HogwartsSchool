CREATE TABLE cars
(
    id INTEGER PRIMARY KEY,
    brand VARCHAR,
    model VARCHAR,
    price INTEGER
);

CREATE TABLE owners
(
    id INTEGER PRIMARY KEY,
    name VARCHAR,
    age INTEGER,
    drivers_license BOOLEN,
    car_id INTEGER REFERENCES cars (id)
);