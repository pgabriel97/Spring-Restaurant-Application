
CREATE TABLE IF NOT EXISTS USERS(username varchar(20) NOT NULL, password varchar(20) NOT NULL,
                                 enabled boolean NOT NULL DEFAULT FALSE,primary key(username));

create table IF NOT EXISTS USER_ROLES (user_role_id SERIAL PRIMARY KEY,
                                       username varchar(20) NOT NULL,role varchar(20) NOT NULL,
                                       UNIQUE (username,role),FOREIGN KEY (username) REFERENCES users (username));

CREATE TABLE IF NOT EXISTS FRANCHISE (ID INT PRIMARY KEY NOT NULL, NAME TEXT NOT NULL, TYPE TEXT NOT NULL,
                                      GRADE FLOAT(2) NOT NULL);

CREATE TABLE IF NOT EXISTS RESTAURANT (ID INT PRIMARY KEY NOT NULL, BRAND_ID INT NOT NULL,
                                       NAME TEXT NOT NULL, ADDRESS TEXT NOT NULL,SEAT_COUNT INT NOT NULL,
                                       MENU_ID INT NOT NULL, GRADE FLOAT(2) NOT NULL, IMG TEXT);

CREATE TABLE IF NOT EXISTS MENU (ID INT NOT NULL, ORDER_NO INT NOT NULL,FOOD_NAME TEXT NOT NULL,
                                 PRICE INT NOT NULL, PRIMARY KEY (ID, ORDER_NO));

CREATE TABLE IF NOT EXISTS RATING (ID_RESTAURANT INT NOT NULL, ID_USER INT NOT NULL,
                                   GRADE INT NOT NULL, PRIMARY KEY (ID_RESTAURANT, ID_USER));
