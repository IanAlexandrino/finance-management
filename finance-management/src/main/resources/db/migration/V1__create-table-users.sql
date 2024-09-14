CREATE TABLE users (
    id BIGINT PRIMARY KEY UNIQUE NOT NULL,
    login TEXT UNIQUE NOT NULL,
    email TEXT UNIQUE NOT NULL,
    name TEXT NOT NULL,
    password TEXT NOT NULL,
    role TEXT NOT NULL,
    activated BOOLEAN NOT NULL
);