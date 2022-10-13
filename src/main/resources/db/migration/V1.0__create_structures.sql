CREATE TABLE IF NOT EXISTS attribute (
    id ${type_serial} PRIMARY KEY,
    created_on timestamp ${timestamp},
    created_by bigint,
    updated_on timestamp ${timestamp},
    updated_by bigint,
    status ${varchar}(25),
    product_id bigint,
    name ${varchar}(200),
    description ${text}
);

CREATE TABLE IF NOT EXISTS product (
    id ${type_serial} PRIMARY KEY,
    created_on timestamp ${timestamp},
    created_by bigint,
    updated_on timestamp ${timestamp},
    updated_by bigint,
    status ${varchar}(25),
    uuid ${varchar}(36),
    model ${varchar}(200),
    description ${text},
    category_id bigint,
    price ${numeric}(10, 2),
    path ${varchar}(200),
    code ${varchar}(50)
);

CREATE TABLE IF NOT EXISTS category_attribute (
    id ${type_serial} PRIMARY KEY,
    created_on timestamp ${timestamp},
    created_by int,
    updated_on timestamp ${timestamp},
    updated_by int,
    status ${varchar}(25),
    category_id bigint,
    name ${varchar}(200)
);

CREATE TABLE IF NOT EXISTS category (
    id ${type_serial} PRIMARY KEY,
    created_on timestamp ${timestamp},
    created_by int,
    updated_on timestamp ${timestamp},
    updated_by int,
    status ${varchar}(25),
    name ${varchar}(200),
    description ${text},
    parent_id bigint
);

CREATE TABLE IF NOT EXISTS cart (
    id ${type_serial} PRIMARY KEY,
    created_on timestamp ${timestamp},
    user_id bigint,
    product_id bigint,
    amount integer default 1,
    total ${numeric}(10, 2)
);

CREATE TABLE IF NOT EXISTS order_main (
    id ${type_serial} PRIMARY KEY,
    created_on timestamp ${timestamp},
    uuid ${varchar}(36),
    user_id bigint,
    street ${varchar}(100),
    post_code ${varchar}(6),
    post ${varchar}(50),
    total ${numeric}(10, 2)
);

CREATE TABLE IF NOT EXISTS order_details (
    id ${type_serial} PRIMARY KEY,
    created_on timestamp ${timestamp},
    order_id bigint,
    product_id bigint,
    price ${numeric}(10, 2),
    amount integer,
    total ${numeric}(10, 2)
);
