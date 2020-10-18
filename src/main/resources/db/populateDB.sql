DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (datetime,description, calories, user_id)
VALUES ('2015-06-01 14:00:00', 'Админ ланч', 510, 100001),
       ('2015-05-01 14:00:00', 'Юзер ланч', 710, 100000);


