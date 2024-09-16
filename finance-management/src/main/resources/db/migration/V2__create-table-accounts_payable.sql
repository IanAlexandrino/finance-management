CREATE TABLE accounts_payable (
    id BIGSERIAL PRIMARY KEY UNIQUE NOT NULL,
    description TEXT NOT NULL,
    value NUMERIC(10, 2) NOT NULL,
    due_date DATE NOT NULL,
    status VARCHAR(20) NOT NULL,
    category TEXT NOT NULL,
    user_id BIGSERIAL NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
);