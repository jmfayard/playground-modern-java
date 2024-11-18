-- Création des tables
CREATE TABLE publishers
(
    id               INTEGER PRIMARY KEY AUTOINCREMENT,
    name             TEXT NOT NULL,
    country          TEXT,
    established_year INTEGER
);

CREATE TABLE authors
(
    id         INTEGER PRIMARY KEY AUTOINCREMENT,
    first_name TEXT NOT NULL,
    last_name  TEXT NOT NULL,
    birth_year INTEGER,
    country    TEXT
);

CREATE TABLE books
(
    isbn             TEXT PRIMARY KEY,
    title            TEXT    NOT NULL,
    publication_year INTEGER,
    stock            INTEGER NOT NULL,
    price            REAL    NOT NULL,
    publisher_id     INTEGER NOT NULL,
    FOREIGN KEY (publisher_id) REFERENCES publishers (id)
);

CREATE TABLE clients
(
    id         INTEGER PRIMARY KEY AUTOINCREMENT,
    first_name TEXT        NOT NULL,
    last_name  TEXT        NOT NULL,
    email      TEXT UNIQUE NOT NULL,
    phone      TEXT,
    address    TEXT
);

CREATE TABLE orders
(
    id         INTEGER PRIMARY KEY AUTOINCREMENT,
    client_id  INTEGER NOT NULL,
    order_date TEXT    NOT NULL,
    status     TEXT    NOT NULL CHECK (status IN ('Pending', 'Shipped', 'Cancelled')),
    FOREIGN KEY (client_id) REFERENCES clients (id)
);

CREATE TABLE order_books
(
    order_id INTEGER NOT NULL,
    isbn     TEXT    NOT NULL,
    quantity INTEGER NOT NULL,
    PRIMARY KEY (order_id, isbn),
    FOREIGN KEY (order_id) REFERENCES orders (id),
    FOREIGN KEY (isbn) REFERENCES books (isbn)
);

CREATE TABLE book_authors
(
    book_isbn TEXT    NOT NULL,
    author_id INTEGER NOT NULL,
    PRIMARY KEY (book_isbn, author_id),
    FOREIGN KEY (book_isbn) REFERENCES books (isbn),
    FOREIGN KEY (author_id) REFERENCES authors (id)
);
