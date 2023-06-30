CREATE TABLE products (
    id  SERIAL PRIMARY KEY NOT NULL,
    name    VARCHAR(255) NOT NULL,
    category    VARCHAR(255)    NOT NULL,
    description TEXT    NOT NULL,
    price  NUMERIC(10, 2),
    is_available BOOLEAN
);
