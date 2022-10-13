INSERT INTO app_user (
    status, uuid, email, password, first_name, last_name, role, longitude, latitude, country,
    city, post_code, post, street, contact
) VALUES
    ('ACTIVE', '53c069de-e435-11ec-8fea-0242ac120002', 'user1@wp.pl',
        '$2a$10$9rdlBPfe5uhGITH23dgRx.3FvzR78SCqnuW2e0yt78THlxoMHYWb.', 'user1', 'user1', 'USER',
        14.5528, 53.4285, 'Polska', 'Szczecin', '00-000', 'Szczecin', 'Szeroka 1/2', 'EMAIL'),
    ('ACTIVE', '53c06b8c-e435-11ec-8fea-0242ac120002', 'user2@wp.pl',
        '$2a$10$caVyTrHN9JjoPn7470WNfepAGRPpbK2zJwzWk4A/GY2j6HdYrsE4i', 'user2', 'user2', 'USER',
        21.0122, 52.2297, 'Polska', 'Warszawa', '00-001', 'Warszawa', 'Wąska 4b', 'EMAIL'),
    ('ACTIVE', '53c06d62-e435-11ec-8fea-0242ac120002', 'admin@wp.pl',
        '$2a$10$IshBIAokxAppEXa0CRf6E.lwsMVJ.L2omm1nTaPtZrBOJsHosAh72', 'admin', 'admin', 'ADMIN',
         17.0385, 51.1079, 'Polska', 'Wrocław', '00-002', 'Wrocław', 'Zagórna 3c', 'EMAIL');
COMMIT;
