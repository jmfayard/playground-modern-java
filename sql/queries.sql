-- all boks from french authors
select b.isbn,
       b.title,
       concat(a.first_name, ' ', a.last_name) AS author,
       p.name
FROM books b
         inner join main.book_authors ba on b.isbn = ba.book_isbn
         inner join main.authors a on a.id = ba.author_id
         inner join main.publishers p on p.id = b.publisher_id
WHERE a.country = 'UK';

-- income and client name from all non cancelled orders
select o.id,
       (ob.quantity * b.price) as total_price,
       b.isbn,
       b.title
from orders o
         inner join main.order_books ob on o.id = ob.order_id
         inner join main.clients c on c.id = o.client_id
         inner join main.books b on ob.isbn = b.isbn

-- books that have never been ordered
select b.isbn, b.title
FROM books b
WHERE b.isbn NOT IN (SELECT isbn
                     from order_books);


-- Authors with no books
select *
FROM authors a
WHERE a.id NOT IN (select author_id from book_authors);


-- Ranking of publishers with number of books
SELECT p.name, count(p.id) as count, b.isbn, b.title
FROM publishers p
INNER JOIN main.books b on p.id = b.publisher_id
GROUP BY p.id
ORDER BY count DESC
;

-- Ranking of clients
select count(client_id) as count,
       concat(c.first_name, ' ', c.last_name) as name
FROM orders
INNER JOIN main.order_books ob on orders.id = ob.order_id
inner join main.clients c on orders.client_id = c.id
group by client_id
;