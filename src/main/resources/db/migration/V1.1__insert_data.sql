INSERT INTO category (created_by, updated_by, status, name, description) VALUES
    (1, 1, 'ACTIVE', 'RAM', 'RAM'),
    (1, 1, 'ACTIVE', 'CPU', 'CPU'),
    (1, 1, 'ACTIVE', 'GPU', 'GPU');
COMMIT;
INSERT INTO category (created_by, updated_by, status, name, description, parent_id)
    (
        SELECT 1, 1, 'ACTIVE', 'AMD', 'AMD', id FROM category WHERE name = 'CPU' UNION
        SELECT 1, 1, 'ACTIVE', 'Intel', 'Intel', id FROM category WHERE name = 'CPU' UNION
        SELECT 1, 1, 'ACTIVE', 'ATI', 'ATI', id FROM category WHERE name = 'GPU' UNION
        SELECT 1, 1, 'ACTIVE', 'nVidia', 'nVidia', id FROM category WHERE name = 'GPU'
    );
COMMIT;

INSERT INTO category_attribute (created_by, updated_by, status, category_id, name) (
    SELECT 1, 1, 'ACTIVE', id, 'Pojemność modułu' FROM category WHERE name = 'RAM' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Ilość modułów' FROM category WHERE name = 'RAM' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Całkowita pojemność' FROM category WHERE name = 'RAM' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Częstotliwość pracy' FROM category WHERE name = 'RAM' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Typ gniazda' FROM category WHERE name IN ('CPU', 'AMD', 'Intel') UNION
    SELECT 1, 1, 'ACTIVE', id, 'Ilość rdzeni' FROM category WHERE name IN ('CPU', 'AMD', 'Intel')
);
COMMIT;

INSERT INTO product (created_by, updated_by, status, uuid, model, description, category_id, price, path, code)
    (
        SELECT 1, 1, 'ACTIVE', '4da99500-5bf8-4710-b005-bca9aaede908',
                     'G.SKILL Aegis 16GB', '2x8GB 3000MHz DDR4 CL16 1.35V DIMM', id, 279.00, '/img/1/foto.jpg',
                     'F4-3000C16D-16GISB'
                         FROM category WHERE name = 'RAM' UNION
        SELECT 1, 1, 'ACTIVE', 'cd324667-e1f7-4d06-91c2-4b31f8732ec6',
                     'G.SKILL Ripjaws V Black 16GB', '2x8GB 3200MHz DDR4 CL16 XMP2.0 DIMM', id, 319.00,
                     '/img/2/foto.jpg', 'F4-3200C16D-16GVKB'
                         FROM category WHERE name = 'RAM' UNION
        SELECT 1, 1, 'ACTIVE', 'c39b14f5-ca1b-4b35-baa5-5c1690a6be50',
                     'G.SKILL Ripjaws V Black 32GB', '2x16GB 3200MHz DDR4 CL16 XMP 2.0 DIMM', id, 619.00,
                     '/img/3/foto.jpg', 'F4-3200C16D-32GVK'
                         FROM category WHERE name = 'RAM' UNION
        SELECT 1, 1, 'ACTIVE', '3a20fda0-1f71-40e2-8043-91a0e5b4a037',
                     'G.SKILL Aegis 32GB', '2x16GB 3200MHz DDR4 CL16 XMP 2.0 DIMM', id, 589.00, '/img/4/foto.jpg',
                     'F4-3200C16D-32GIS'
                         FROM category WHERE name = 'RAM' UNION
        SELECT 1, 1, 'ACTIVE', '51d2fb72-a0f1-4d66-a936-f34daab50ce9',
                     'G.SKILL Aegis 8GB', '1x8GB 3000MHz DDR4 CL16 1.35V DIMM', id, 139.90, '/img/5/foto.jpg',
                     'F4-3000C16S-8GISB'
                         FROM category WHERE name = 'RAM' UNION
        SELECT 1, 1, 'ACTIVE', '285bc12d-2885-45df-afe2-7d30bc951386',
                     'G.SKILL Ripjaws V Black 64GB', '2x32GB 3200MHz DDR4 CL16 XMP2.0 DIMM', id, 1359.00,
                     '/img/6/foto.jpg', 'F4-3200C16D-64GVK'
                         FROM category WHERE name = 'RAM' UNION
        SELECT 1, 1, 'ACTIVE', '5a0f2eab-b6f6-4d81-9ef4-5233dacdce3e',
                     'G.SKILL Trident Z RGB 16GB', '2x8GB 3000MHz DDR4 CL16 XMP2 DIMM', id, 459.00, '/img/7/foto.jpg',
                     'F4-3000C16D-16GTZR'
                         FROM category WHERE name = 'RAM' UNION
        SELECT 1, 1, 'ACTIVE', 'fbd26bc3-701b-43bf-bea3-11d3f9998402',
                     'G.SKILL Trident Z Neo 32GB', '2x16GB 3200MHz DDR4 CL16 1.35V XMP 2.0 DIMM', id, 759.00,
                     '/img/8/foto.jpg', 'F4-3200C16D-32GTZN'
                         FROM category WHERE name = 'RAM' UNION
        SELECT 1, 1, 'ACTIVE', '9af9bcf4-2fea-4d5f-925f-7eddc1c0f948',
                     'G.SKILL Ripjaws V Red 16GB', '2x8GB 3600MHz DDR4 CL19 XMP2 DIMM', id, 339.00, '/img/9/foto.jpg',
                     'F4-3600C19D-16GVRB'
                         FROM category WHERE name = 'RAM' UNION
        SELECT 1, 1, 'ACTIVE', 'c2a36959-fd80-4b4f-9bb0-2798f65fde14',
                     'Corsair Vengeance RGB Pro Black 16GB', '2x8GB 3200MHz DDR4 CL16 1.35V DIMM', id, 399.00,
                     '/img/10/foto.jpg', 'CMW16GX4M2C3200C16'
                         FROM category WHERE name = 'RAM' UNION
        SELECT 1, 1, 'ACTIVE', '94c0cb71-54c5-483d-995e-a292c193d60e',
                     'Corsair Vengeance RGB Pro SL Black 32GB', '2x16GB 3600MHz DDR4 CL18 DIMM', id, 809.00,
                     '/img/11/foto.jpg', 'CMH32GX4M2D3600C18'
                         FROM category WHERE name = 'RAM' UNION

        SELECT 1, 1, 'ACTIVE', '2f68aaa0-39bf-46f9-bd67-62ea34128e00',
                     'AMD Ryzen 5 5600X', 'AMD Ryzen 5 - seria 5000', id, 1019.00, '/img/12/foto.jpg', '100-100000065BOX'
                         FROM category WHERE name = 'AMD' UNION
        SELECT 1, 1, 'ACTIVE', 'c75b0937-9055-40c3-9ff9-22070d76d9a6',
                     'AMD Ryzen 9 5950X', 'AMD Ryzen 9 - seria 5000', id, 2699.00, '/img/13/foto.jpg', '100-100000059WOF]'
                         FROM category WHERE name = 'AMD' UNION
        SELECT 1, 1, 'ACTIVE', '987917ad-e36c-452e-9a36-526ba59421cd',
                     'AMD Ryzen 9 5900X', 'AMD Ryzen 9 - seria 5000', id, 1969.00, '/img/14/foto.jpg', '100-100000061WOF'
                         FROM category WHERE name = 'AMD' UNION
        SELECT 1, 1, 'ACTIVE', 'cdad6f96-48f6-4c0a-887d-1de91c8aae1d',
                     'AMD Ryzen 7 5700X', 'AMD Ryzen 7 - seria 5000', id, 1439.00, '/img/15/foto.jpg', '100-100000926WOF'
                         FROM category WHERE name = 'AMD' UNION
        SELECT 1, 1, 'ACTIVE', '6fc3bbdc-9543-46e1-b733-6e58429343c2',
                     'AMD Ryzen 5 5600', 'AMD Ryzen 5 - seria 5000', id, 969.00, '/img/16/foto.jpg', '100-100000927BOX'
                         FROM category WHERE name = 'AMD' UNION
        SELECT 1, 1, 'ACTIVE', 'e259bd28-dffe-444b-83dd-00a328891a70',
                     'Intel Core i5-12600K', 'Intel Core i5-12XXX', id, 1459.00, '/img/17/foto.jpg', 'BX8071512600K'
                         FROM category WHERE name = 'Intel' UNION
        SELECT 1, 1, 'ACTIVE', 'd627125c-9fd0-446a-897a-8e19b881c5b3',
                     'Intel Core i5-12400F', 'Intel Core i5-12XXX', id, 899.00, '/img/18/foto.jpg', 'BX8071512400F'
                         FROM category WHERE name = 'Intel' UNION
        SELECT 1, 1, 'ACTIVE', 'a91be5a0-b771-4002-804c-78e366ee5ec6',
                     'Intel Core i3-12100F', 'Intel Core i3-12XXX', id, 509.00, '/img/19/foto.jpg', 'BX8071512100F'
                         FROM category WHERE name = 'Intel' UNION
        SELECT 1, 1, 'ACTIVE', '1ceacfd5-5c25-4094-8e8a-75b9b262f4a7',
                     'Intel Core i7-12700K', 'Intel Core i7-12XXX', id, 2149.00, '/img/20/foto.jpg', 'BX8071512700K'
                         FROM category WHERE name = 'Intel' UNION
        SELECT 1, 1, 'ACTIVE', 'e6fbd22f-361e-461e-b646-df71db896598',
                     'Intel Core i5-12600KF', 'Intel Core i5-12XXX', id, 1329.00, '/img/21/foto.jpg', 'BX8071512600KF'
                         FROM category WHERE name = 'Intel' UNION

        SELECT 1, 1, 'ACTIVE', '4ac53f58-b2c9-46ab-8b4e-c3235459c8c2',
                     'ASUS Radeon RX 6500 XT DUAL 4GB', 'RX z serii 6', id, 1199.00, '/img/22/foto.jpg', 'DUAL-RX6500XT-O4G'
                         FROM category WHERE name = 'ATI' UNION
        SELECT 1, 1, 'ACTIVE', '411a5019-6d7f-4dad-90c6-356c77792312',
                     'ASUS Radeon RX 6650 XT DUAL 8GB OC', 'RX z serii 6', id, 2349.00, '/img/23/foto.jpg', 'DUAL-RX6650XT-O8G'
                         FROM category WHERE name = 'ATI' UNION
        SELECT 1, 1, 'ACTIVE', '6f5f42f5-5ca1-4bd7-80d0-56a05c8fdc35',
                     'ASUS Radeon RX 6600 XT ROG STRIX 8GB OC', 'RX z serii 6', id, 2349.00, '/img/24/foto.jpg', 'ROG-STRIX-RX6600XT-O8G-GAMING'
                         FROM category WHERE name = 'ATI' UNION
        SELECT 1, 1, 'ACTIVE', '88eef6ca-cb9c-41c5-8a44-416ab4bd88bc',
                     'ASUS Radeon RX 6750 XT ROG STRIX 12GB OC', 'RX z serii 6', id, 3399.00, '/img/25/foto.jpg', 'ROG-STRIX-RX6750XT-O12G-GAMING'
                         FROM category WHERE name = 'ATI' UNION
        SELECT 1, 1, 'ACTIVE', '119e71da-8b67-4b21-baf8-9b86495cd985',
                     'Gigabyte GeForce RTX 3070 Ti Gaming 8GB OC', 'RTX z serii 30', id, 3999.00, '/img/26/foto.jpg', 'GV-N307TGAMING OC-8GD'
                         FROM category WHERE name = 'nVidia' UNION
        SELECT 1, 1, 'ACTIVE', '7f9e83b3-339c-4646-b4cb-b3efe0cf42be',
                     'ASUS GeForce RTX 3070 Ti TUF 8GB OC', 'RTX z serii 30', id, 3999.00, '/img/27/foto.jpg', 'TUF-RTX3070TI-O8G-GAMING'
                         FROM category WHERE name = 'nVidia' UNION
        SELECT 1, 1, 'ACTIVE', '51c449b8-2037-44b9-a420-c57bf36b35e9',
                     'ASUS GeForce RTX 3080 Ti TUF Gaming 12GB OC', 'RTX z serii 30', id, 6490.00, '/img/28/foto.jpg', 'TUF-RTX3080TI-O12G-GAMING'
                         FROM category WHERE name = 'nVidia' UNION
        SELECT 1, 1, 'ACTIVE', 'a6635111-cf58-4dfc-a1b2-8d9ab789c374',
                     'Gigabyte GeForce RTX 3080 Ti GAMING OC 12GB', 'RTX z serii 30', id, 6390.00, '/img/29/foto.jpg', 'GV-N308TGAMING OC-12GD 1.0'
                         FROM category WHERE name = 'nVidia' UNION
        SELECT 1, 1, 'ACTIVE', '75c01bbb-91c3-4e33-82fa-d7fe4b5bafbe',
                     'Gigabyte GeForce RTX 3080 Ti AORUS MASTER 12GB', 'RTX z serii 30', id, 7590.00, '/img/30/foto.jpg', 'GV-N308TAORUS M-12GD'
                         FROM category WHERE name = 'nVidia'
    );
COMMIT;

INSERT INTO attribute (created_by, updated_by, status, product_id, name, description) (
    SELECT 1, 1, 'ACTIVE', id, 'Pojemność modułu', '8 GB' FROM product WHERE code = 'F4-3000C16D-16GISB' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Ilość modułów', '2' FROM product WHERE code = 'F4-3000C16D-16GISB' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Całkowita pojemność', '16 GB' FROM product WHERE code = 'F4-3000C16D-16GISB' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Częstotliwość pracy', '3000 MHz' FROM product WHERE code = 'F4-3000C16D-16GISB' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Pojemność modułu', '8 GB' FROM product WHERE code = 'F4-3200C16D-16GVKB' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Ilość modułów', '2' FROM product WHERE code = 'F4-3200C16D-16GVKB' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Całkowita pojemność', '16 GB' FROM product WHERE code = 'F4-3200C16D-16GVKB' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Częstotliwość pracy', '3200 MHz' FROM product WHERE code = 'F4-3200C16D-16GVKB' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Pojemność modułu', '16 GB' FROM product WHERE code = 'F4-3200C16D-32GVK' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Ilość modułów', '2' FROM product WHERE code = 'F4-3200C16D-32GVK' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Całkowita pojemność', '32 GB' FROM product WHERE code = 'F4-3200C16D-32GVK' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Częstotliwość pracy', '3200 MHz' FROM product WHERE code = 'F4-3200C16D-32GVK' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Pojemność modułu', '16 GB' FROM product WHERE code = 'F4-3200C16D-32GIS' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Ilość modułów', '2' FROM product WHERE code = 'F4-3200C16D-32GIS' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Całkowita pojemność', '32 GB' FROM product WHERE code = 'F4-3200C16D-32GIS' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Częstotliwość pracy', '3200 MHz' FROM product WHERE code = 'F4-3200C16D-32GIS' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Pojemność modułu', '8 GB' FROM product WHERE code = 'F4-3000C16S-8GISB' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Ilość modułów', '1' FROM product WHERE code = 'F4-3000C16S-8GISB' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Całkowita pojemność', '8 GB' FROM product WHERE code = 'F4-3000C16S-8GISB' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Częstotliwość pracy', '3000 MHz' FROM product WHERE code = 'F4-3000C16S-8GISB' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Pojemność modułu', '8 GB' FROM product WHERE code = 'F4-3200C16D-64GVK' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Ilość modułów', '2' FROM product WHERE code = 'F4-3200C16D-64GVK' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Całkowita pojemność', '16 GB' FROM product WHERE code = 'F4-3200C16D-64GVK' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Częstotliwość pracy', '3000 MHz' FROM product WHERE code = 'F4-3200C16D-64GVK' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Pojemność modułu', '8 GB' FROM product WHERE code = 'F4-3000C16D-16GTZR' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Ilość modułów', '2' FROM product WHERE code = 'F4-3000C16D-16GTZR' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Całkowita pojemność', '16 GB' FROM product WHERE code = 'F4-3000C16D-16GTZR' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Częstotliwość pracy', '3000 MHz' FROM product WHERE code = 'F4-3000C16D-16GTZR' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Pojemność modułu', '16 GB' FROM product WHERE code = 'F4-3200C16D-32GTZN' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Ilość modułów', '2' FROM product WHERE code = 'F4-3200C16D-32GTZN' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Całkowita pojemność', '32 GB' FROM product WHERE code = 'F4-3200C16D-32GTZN' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Częstotliwość pracy', '3200 MHz' FROM product WHERE code = 'F4-3200C16D-32GTZN' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Pojemność modułu', '8 GB' FROM product WHERE code = 'F4-3600C19D-16GVRB' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Ilość modułów', '2' FROM product WHERE code = 'F4-3600C19D-16GVRB' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Całkowita pojemność', '16 GB' FROM product WHERE code = 'F4-3600C19D-16GVRB' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Częstotliwość pracy', '3600 MHz' FROM product WHERE code = 'F4-3600C19D-16GVRB' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Pojemność modułu', '8 GB' FROM product WHERE code = 'CMW16GX4M2C3200C16' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Ilość modułów', '2' FROM product WHERE code = 'CMW16GX4M2C3200C16' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Całkowita pojemność', '16 GB' FROM product WHERE code = 'CMW16GX4M2C3200C16' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Częstotliwość pracy', '3200 MHz' FROM product WHERE code = 'CMW16GX4M2C3200C16' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Pojemność modułu', '16 GB' FROM product WHERE code = 'CMH32GX4M2D3600C18' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Ilość modułów', '2' FROM product WHERE code = 'CMH32GX4M2D3600C18' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Całkowita pojemność', '32 GB' FROM product WHERE code = 'CMH32GX4M2D3600C18' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Częstotliwość pracy', '3600 MHz' FROM product WHERE code = 'CMH32GX4M2D3600C18' UNION

    SELECT 1, 1, 'ACTIVE', id, 'Typ gniazda', 'Socket AM4' FROM product WHERE model = 'AMD Ryzen 5 5600X' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Ilość rdzeni', '6 szt.' FROM product WHERE model = 'AMD Ryzen 5 5600X' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Typ gniazda', 'Socket AM4' FROM product WHERE model = 'AMD Ryzen 9 5950X' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Ilość rdzeni', '16 szt.' FROM product WHERE model = 'AMD Ryzen 9 5950X' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Typ gniazda', 'Socket AM4' FROM product WHERE model = 'AMD Ryzen 9 5900X' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Ilość rdzeni', '12 szt.' FROM product WHERE model = 'AMD Ryzen 9 5900X' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Typ gniazda', 'Socket AM4' FROM product WHERE model = 'AMD Ryzen 7 5700X' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Ilość rdzeni', '8 szt.' FROM product WHERE model = 'AMD Ryzen 7 5700X' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Typ gniazda', 'Socket AM4' FROM product WHERE model = 'AMD Ryzen 5 5600' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Ilość rdzeni', '6 szt.' FROM product WHERE model = 'AMD Ryzen 5 5600' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Typ gniazda', 'Socket 1700' FROM product WHERE model = 'Intel Core i5-12600K' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Ilość rdzeni', '10 szt.' FROM product WHERE model = 'Intel Core i5-12600K' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Typ gniazda', 'Socket 1700' FROM product WHERE model = 'Intel Core i5-12400F' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Ilość rdzeni', '6 szt.' FROM product WHERE model = 'Intel Core i5-12400F' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Typ gniazda', 'Socket 1700' FROM product WHERE model = 'Intel Core i3-12100F' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Ilość rdzeni', '4 szt.' FROM product WHERE model = 'Intel Core i3-12100F' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Typ gniazda', 'Socket 1700' FROM product WHERE model = 'Intel Core i7-12700K' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Ilość rdzeni', '12 szt.' FROM product WHERE model = 'Intel Core i7-12700K' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Typ gniazda', 'Socket 1700' FROM product WHERE model = 'Intel Core i5-12600KF' UNION
    SELECT 1, 1, 'ACTIVE', id, 'Ilość rdzeni', '10 szt.' FROM product WHERE model = 'Intel Core i5-12600KF'
);
COMMIT;
