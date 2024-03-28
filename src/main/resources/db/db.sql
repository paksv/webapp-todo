CREATE DATABASE IF NOT EXISTS webapp_todo;

USE webapp_todo;

CREATE TABLE users(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE todos(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    date DATETIME NOT NULL,
    is_done BOOLEAN,
    user_id INT NOT NULL,
    CONSTRAINT fk_user_todo FOREIGN KEY (user_id) REFERENCES users(id)
);
