-- Insertion des éditeurs
INSERT INTO publishers (name, country, established_year)
VALUES ('Penguin Books', 'UK', 1935),
       ('HarperCollins', 'USA', 1989),
       ('Gallimard', 'France', 1911);

-- Insertion des auteurs
INSERT INTO authors (first_name, last_name, birth_year, country)
VALUES ('George', 'Orwell', 1903, 'UK'),
       ('Harper', 'Lee', 1926, 'USA'),
       ('Victor', 'Hugo', 1802, 'France'),
       ('J.K.', 'Rowling', 1965, 'UK');

-- Insertion des livres
INSERT INTO books (isbn, title, publication_year, stock, price, publisher_id)
VALUES ('9780141036137', '1984', 1949, 15, 9.99, 1),
       ('9780061120084', 'To Kill a Mockingbird', 1960, 8, 14.99, 2),
       ('9782070409184', 'Les Misérables', 1862, 5, 19.99, 3),
       ('9780747532743', 'Harry Potter and the Philosopher''s Stone', 1997, 20, 12.99, 1);

-- Insertion des clients
INSERT INTO clients (first_name, last_name, email, phone, address) VALUES
('Alice', 'Smith', 'alice.smith@example.com', '+441234567890', '123 Main St, London, UK'),
('Bob', 'Johnson', 'bob.johnson@example.com', '+33123456789', '45 Rue de Paris, Paris, France'),
('Charlie', 'Brown', 'charlie.brown@example.com', '+16175555555', '789 Elm St, New York, USA ');

-- Insertion des commandes
INSERT INTO orders (client_id, order_date, status) VALUES
(1, '2024-11-01', 'Pending'),
(2, '2024-11-02', 'Shipped'),
(3, '2024-11-03', 'Cancelled');

-- Insertion des livres dans les commandes
INSERT INTO order_books (order_id, isbn, quantity) VALUES
(1, '9780141036137', 1),  -- Alice commande 1984
(2, '9780061120084', 2),  -- Bob commande 2 exemplaires de Mockingbird
(3, '9782070409184', 1);  -- Charlie commande Les Misérables

-- Insertion des relations livres-auteurs
INSERT INTO book_authors (book_isbn, author_id) VALUES
('9780141036137', 1),  -- 1984 par George Orwell
('9780061120084', 2),  -- To Kill a Mockingbird par Harper Lee
('9782070409184', 3),  -- Les Misérables par Victor Hugo
('9780747532743', 4);  -- Harry Potter par J.K. Rowling
