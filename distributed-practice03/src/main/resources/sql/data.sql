CREATE TABLE
    t_coffee
    (
        id bigint(-1) NOT NULL AUTO_INCREMENT,
        create_time TIMESTAMP NULL,
        update_time TIMESTAMP NULL,
        name VARCHAR(255),
        price bigint(-1),
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


INSERT INTO t_coffee (id, create_time, update_time, name, price) VALUES (1, '2022-07-28 22:45:42', '2022-07-28 22:45:42', 'espresso', 2000);
INSERT INTO t_coffee (id, create_time, update_time, name, price) VALUES (2, '2022-07-28 22:45:42', '2022-07-28 22:45:42', 'latte', 2500);
INSERT INTO t_coffee (id, create_time, update_time, name, price) VALUES (3, '2022-07-28 22:45:42', '2022-07-28 22:45:42', 'capuccino', 2500);
INSERT INTO t_coffee (id, create_time, update_time, name, price) VALUES (4, '2022-07-28 22:45:42', '2022-07-28 22:45:42', 'mocha', 3000);
INSERT INTO t_coffee (id, create_time, update_time, name, price) VALUES (5, '2022-07-28 22:45:42', '2022-07-28 22:45:42', 'macchiato', 3000);
INSERT INTO t_coffee (id, create_time, update_time, name, price) VALUES (6, '2022-07-28 22:00:51', '2022-07-28 22:20:59', 'espresso', 2000);
INSERT INTO t_coffee (id, create_time, update_time, name, price) VALUES (7, '2022-07-28 22:12:23', '2022-07-28 22:20:59', '测试', 2000);
INSERT INTO t_coffee (id, create_time, update_time, name, price) VALUES (8, '2022-07-28 22:14:53', '2022-07-28 22:20:59', '测试', 2000);
INSERT INTO t_coffee (id, create_time, update_time, name, price) VALUES (9, '2022-07-28 22:16:06', '2022-07-28 22:16:06', '测试', 2000);
INSERT INTO t_coffee (id, create_time, update_time, name, price) VALUES (10, '2022-07-28 22:19:02', '2022-07-28 22:19:02', '测试', 2000);
INSERT INTO t_coffee (id, create_time, update_time, name, price) VALUES (11, '2022-07-28 22:20:59', '2022-07-28 22:20:59', '测试', 2000);
