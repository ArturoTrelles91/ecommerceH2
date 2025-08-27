-- CATEGORIES (primero, para que existan los IDs 1 y 2)
INSERT INTO CATEGORIES (NAME, DESCRIPTION) VALUES
('Electrónica', 'Productos electrónicos y gadgets'),
('Hogar',       'Artículos para el hogar');

-- PRODUCTS (sin DESCRIPTION; usa el FK CATEGORY_ID)
INSERT INTO PRODUCTS (NAME, PRICE, STOCK, CATEGORY_ID) VALUES
('Audífonos BT',    899.00, 50, 1),
('Teclado Mecánico',1299.00, 35, 1),
('Lámpara LED',     499.00, 60, 2);