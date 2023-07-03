CREATE TABLE orders (
    id  SERIAL PRIMARY KEY NOT NULL,
    customer_id SERIAL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    status VARCHAR(255) NOT NULL
);
