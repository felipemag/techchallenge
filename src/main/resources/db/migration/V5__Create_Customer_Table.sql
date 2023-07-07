CREATE TABLE customers (
    id  SERIAL PRIMARY KEY NOT NULL,
    first_name varchar(255) NOT NULL,
    last_name varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    document varchar(255) NOT NULL,
    UNIQUE(email, document)
);
