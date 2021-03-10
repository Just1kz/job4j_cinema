create table if not exists accounts(
idA serial primary key,
username text,
phone_number text
);

create table if not exists halls(
idH serial primary key,
rowX int,
columnX int,
price int,
status text,
account_id int references accounts(idA)
);

insert into accounts(username, phone_number) VALUES ('Admin', '0');
insert into accounts(username, phone_number) VALUES ('Anton', '+79529008848');
insert into accounts(username, phone_number) VALUES ('Stas', '+79234568848');
insert into accounts(username, phone_number) VALUES ('Petr', '+74567848848');
insert into accounts(username, phone_number) VALUES ('Pavel', '+79524569848');

insert into halls(rowX, columnX, price, status, account_id) VALUES (1, 1, 500, 'Free', 1);
insert into halls(rowX, columnX, price, status, account_id) VALUES (1, 2, 500, 'Free', 1);
insert into halls(rowX, columnX, price, status, account_id) VALUES (1, 3, 500, 'Free', 1);
insert into halls(rowX, columnX, price, status, account_id) VALUES (2, 1, 600, 'Free', 1);
insert into halls(rowX, columnX, price, status, account_id) VALUES (2, 2, 600, 'Free', 1);
insert into halls(rowX, columnX, price, status, account_id) VALUES (2, 3, 600, 'Free', 1);
insert into halls(rowX, columnX, price, status, account_id) VALUES (3, 1, 450, 'Free', 1);
insert into halls(rowX, columnX, price, status, account_id) VALUES (3, 2, 450, 'Free', 1);
insert into halls(rowX, columnX, price, status, account_id) VALUES (3, 3, 450, 'Free', 1);

SELECT * FROM halls order by idH, status;