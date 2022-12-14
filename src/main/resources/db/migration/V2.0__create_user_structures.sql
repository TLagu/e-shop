CREATE TABLE IF NOT EXISTS app_user (
    id BIGSERIAL PRIMARY KEY,
    created_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by int,
    updated_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by int,
    status CHARACTER VARYING(25),
    uuid CHARACTER VARYING(36),
    email CHARACTER VARYING(45),
    password CHARACTER VARYING(64),
    first_name CHARACTER VARYING(20),
    last_name CHARACTER VARYING(20),
    role CHARACTER VARYING(20),
    longitude NUMERIC(8, 5),
    latitude NUMERIC(8, 5),
    country CHARACTER VARYING(100),
    city CHARACTER VARYING(100),
    post_code CHARACTER VARYING(6),
    post CHARACTER VARYING(100),
    street CHARACTER VARYING(200),
    contact CHARACTER VARYING(20)
);
