CREATE TABLE IF NOT EXISTS attribute (
    id BIGSERIAL PRIMARY KEY,
    created_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by bigint,
    updated_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by bigint,
    status CHARACTER VARYING(25),
    product_id bigint,
    name CHARACTER VARYING(200),
    description TEXT
);

CREATE TABLE IF NOT EXISTS product (
    id BIGSERIAL PRIMARY KEY,
    created_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by bigint,
    updated_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by bigint,
    status CHARACTER VARYING(25),
    uuid CHARACTER VARYING(36),
    model CHARACTER VARYING(200),
    description TEXT,
    category_id bigint,
    price NUMERIC(10, 2),
    path CHARACTER VARYING(200),
    code CHARACTER VARYING(50)
);

CREATE TABLE IF NOT EXISTS category_attribute (
    id BIGSERIAL PRIMARY KEY,
    created_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by int,
    updated_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by int,
    status CHARACTER VARYING(25),
    category_id bigint,
    name CHARACTER VARYING(200)
);

CREATE TABLE IF NOT EXISTS category (
    id BIGSERIAL PRIMARY KEY,
    created_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by int,
    updated_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by int,
    status CHARACTER VARYING(25),
    name CHARACTER VARYING(200),
    description TEXT,
    parent_id bigint
);

CREATE TABLE IF NOT EXISTS cart (
    id BIGSERIAL PRIMARY KEY,
    created_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    user_id bigint,
    product_id bigint,
    amount integer default 1,
    total NUMERIC(10, 2)
);

CREATE TABLE IF NOT EXISTS order_main (
    id BIGSERIAL PRIMARY KEY,
    created_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    uuid CHARACTER VARYING(36),
    user_id bigint,
    street CHARACTER VARYING(100),
    post_code CHARACTER VARYING(6),
    post CHARACTER VARYING(50),
    total NUMERIC(10, 2)
);

CREATE TABLE IF NOT EXISTS order_details (
    id BIGSERIAL PRIMARY KEY,
    created_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    order_id bigint,
    product_id bigint,
    price NUMERIC(10, 2),
    amount integer,
    total NUMERIC(10, 2)
);
