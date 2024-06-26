INSERT INTO users(username,password,enabled) VALUES ('ana','{noop}ana', true);
INSERT INTO users(username,password,enabled) VALUES ('gabi','{noop}gabi', true);
INSERT INTO users(username,password,enabled) VALUES ('enduser','{noop}enduser', true);
INSERT INTO user_roles (username, role) VALUES ('ana', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role) VALUES ('gabi', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role) VALUES ('enduser', 'ROLE_USER');
INSERT INTO users(username,password,enabled) VALUES ('test','{noop}test', true);
INSERT INTO user_roles (username, role) VALUES ('test', 'ROLE_USER');

INSERT INTO franchise (id, name, type, grade)
            VALUES (1, 'Mc', 'Fast food', 8.5),(2, 'KFC', 'Fast food', 0),(3, 'Pizza Hut', 'Pizza', 0);

INSERT INTO restaurant (id, brand_id, name, address, seat_count, menu_id, grade, img)
            VALUES (1, 1, 'McDonalds Unirii', 'Unirii', 30, 1, 8.5, 'mc1.jpg'), (2, 2, 'KFC Pacii', 'Pacii', 20, 1, 0, 'kfc.jpg'),
                               (3, 3, 'Pizza Hut ParkLake', 'Dristor', 10, 2, 0, 'pizzahut.jpg');

INSERT INTO menu (id, order_no, food_name, price)
            VALUES (1, 1, 'Sandwich', 15), (1, 2, 'Salad', 10), (2, 1, 'Shawarma', 17), (3, 1, 'Pizza', 23), (2, 2, 'Cake', 5);

INSERT INTO rating (id_restaurant, username, grade) VALUES (1, 'gabi', 9), (1, 'ana', 8);

INSERT INTO COMMENT (id_comment, id_restaurant, username, comment_text) VALUES ('1', 1, 'gabi', 'Misto!'), ('2', 2, 'ana', 'Imi place restaurantul vostru!');

INSERT INTO RESERVATION  (ID_RESTAURANT, ID_USER, start_date, start_time, GUEST_NUMBER) VALUES (1, 'ana', '2020-01-22', '02:03:00', 3);