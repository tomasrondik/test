INSERT INTO store(id, name, phone_number, email_address, category) VALUES ('uuid1', 'Starbucks', '+100100200', 'hello@starbucks.com', 'COFFEE_HOUSE');

INSERT INTO item(id, name, description, price, store_id) VALUES ('uuid2', 'Flat white', null, 4.50, 'uuid1');
