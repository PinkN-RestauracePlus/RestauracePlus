CREATE TABLE DAT.rights (
	status VARCHAR(50) NOT NULL UNIQUE,
    id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)
);

CREATE TABLE DAT.tables (
    capacity INTEGER NOT NULL,
    id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)
);

CREATE TABLE DAT.order_status (
    status VARCHAR(50) NOT NULL UNIQUE,
    id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)
);

CREATE TABLE DAT.users (
    user_name VARCHAR(50) NOT NULL,
    personal_number INTEGER NOT NULL UNIQUE,
    login VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    right_id INTEGER NOT NULL CONSTRAINT rights_foregin REFERENCES DAT.rights(id),
    id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)
);

CREATE TABLE DAT.orders (
    order_time VARCHAR(50) NOT NULL,
    order_date VARCHAR(50) NOT NULL,
    table_id INTEGER NOT NULL CONSTRAINT tables_foregin REFERENCES DAT.tables(id),
    order_status_id INTEGER NOT NULL CONSTRAINT order_status_foregin REFERENCES DAT.order_status(id),
    id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)
);

CREATE TABLE DAT.feedback (
    waiter_stat INTEGER NOT NULL,
    price_stat INTEGER NOT NULL,
    food_stat INTEGER NOT NULL,
    atmosphere_stat INTEGER NOT NULL,
    waiter_id INTEGER NOT NULL CONSTRAINT users_foregin REFERENCES DAT.users(id),
    order_id INTEGER NOT NULL CONSTRAINT orders_foregin REFERENCES DAT.orders(id),
    id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)
);

CREATE TABLE DAT.menu (
    item_name VARCHAR(50) NOT NULL UNIQUE,
    price INTEGER NOT NULL,
    availability BOOLEAN NOT NULL,
    id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)
);

CREATE TABLE DAT.list_of_ordered_items (
    status INTEGER NOT NULL,
    menu_item_id INTEGER NOT NULL CONSTRAINT menu_foregin REFERENCES DAT.menu(id),
    order_id INTEGER NOT NULL CONSTRAINT list_orders_foregin REFERENCES DAT.orders(id),
    id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)
);

CREATE TABLE DAT.reservation (
    table_id INTEGER NOT NULL CONSTRAINT res_tables_foregin REFERENCES DAT.tables(id),
    res_time VARCHAR(50) NOT NULL,
    res_date VARCHAR(50) NOT NULL,
    number_of_people INTEGER NOT NULL,
    res_name VARCHAR(50) NOT NULL,
    note VARCHAR(120),
    id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)
);


