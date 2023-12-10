CREATE TABLE subscription_type (
    id SERIAL PRIMARY KEY,
    tenant_id INT,
    name VARCHAR(255) NOT NULL,
    duration_in_months INT NOT NULL,
    price INT NOT NULL
);
