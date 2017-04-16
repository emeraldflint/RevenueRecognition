INSERT INTO products (name, type) VALUES ('Product 1', 'WordProcessor');
INSERT INTO products (name, type) VALUES ('Product 2', 'SPREADSHEET');
INSERT INTO products (name, type) VALUES ('Product 3', 'DATABASE');

INSERT INTO contracts (product_id, revenue, dateSigned) VALUES (1, 500.0, DATE '2017-04-22');

INSERT INTO revenueRecognitions (contract_id, amount, recognizedOn) VALUES (1, 333, DATE '2017-04-23');
INSERT INTO revenueRecognitions (contract_id, amount, recognizedOn) VALUES (1, 333, DATE '2017-04-22');
INSERT INTO revenueRecognitions (contract_id, amount, recognizedOn) VALUES (1, 333, DATE '2017-04-22');
