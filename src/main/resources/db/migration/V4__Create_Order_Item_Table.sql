CREATE TABLE order_items (
    id  SERIAL PRIMARY KEY NOT NULL,
    order_id integer NOT NULL,
    product_id integer NOT NULL,
    quantity integer NOT NULL,
    price  NUMERIC(10, 2) NOT NULL,
    observation VARCHAR(255)
);
