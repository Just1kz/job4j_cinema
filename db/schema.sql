create table if not exists accounts(
idA serial primary key,
username text,
phone_number text unique
);

create table if not exists halls(
idH serial primary key check ( idH <= 9 ),
rowX int check ( rowX >= 1 and rowX <= 3 ),
columnX int check ( columnX >= 1 and columnX <= 3 ),
price int,
status text default 'Free',
account_id int references accounts(idA),
UNIQUE (rowX, columnX)
);

create table if not exists orders(
    id serial primary key,
    rowX int not null,
    columnX int not null,
    account_id int references accounts(idA),
    UNIQUE (rowX, columnX)
);

insert into accounts(username, phone_number) VALUES ('Admin', '0');
insert into accounts(username, phone_number) VALUES ('Anton', '+79529008848');
insert into accounts(username, phone_number) VALUES ('Stas', '+79234568848');
insert into accounts(username, phone_number) VALUES ('Petr', '+74567848848');
insert into accounts(username, phone_number) VALUES ('Pavel', '+79524569848');

insert into halls(rowX, columnX, price, account_id) VALUES (1, 1, 500, 1);
insert into halls(rowX, columnX, price, account_id) VALUES (1, 2, 500, 1);
insert into halls(rowX, columnX, price, account_id) VALUES (1, 3, 500, 1);
insert into halls(rowX, columnX, price, account_id) VALUES (2, 1, 600, 1);
insert into halls(rowX, columnX, price, account_id) VALUES (2, 2, 600, 1);
insert into halls(rowX, columnX, price, account_id) VALUES (2, 3, 600, 1);
insert into halls(rowX, columnX, price, account_id) VALUES (3, 1, 450, 1);
insert into halls(rowX, columnX, price, account_id) VALUES (3, 2, 450, 1);
insert into halls(rowX, columnX, price, account_id) VALUES (3, 3, 450, 1);

SELECT * FROM halls order by idH, status;