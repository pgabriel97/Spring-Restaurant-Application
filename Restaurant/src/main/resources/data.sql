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

INSERT INTO rating (id_restaurant, id_user, grade) VALUES (1, 1, 9), (1, 2, 8);

INSERT INTO COMMENT (id_comment, id_restaurant, id_user, comment_text) VALUES ('1', 1, 1, 'Misto!'), ('2', 2, 2, 'Imi place restaurantul vostru!');

INSERT INTO rating (id_restaurant, id_user, grade) VALUES (1, 1, 9), (1, 2, 8);

INSERT INTO reservation (id_restaurant, id_user, start_date ,guest_number)
            VALUES (1, 'ana', current_date,current_time, 3), (2, 'ana', current_date,current_time, 1),(2, 'gabi',current_date,current_time, 2);
