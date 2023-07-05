CREATE TABLE orders (
    id  SERIAL PRIMARY KEY NOT NULL,
    customer_id integer,
    total_price  NUMERIC(10, 2) NOT NULL,
    status VARCHAR(255) NOT NULL
);
