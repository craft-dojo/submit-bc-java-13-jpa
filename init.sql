DROP DATABASE IF EXISTS `db_assign_13_jpa`;
CREATE DATABASE `db_assign_13_jpa`;
USE `db_assign_13_jpa`;

CREATE TABLE students (
    id BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    major VARCHAR(100),
    PRIMARY KEY (`id`),
    UNIQUE KEY `email` (`email`)
);

CREATE TABLE `categories` (
    `id` BIGINT AUTO_INCREMENT NOT NULL,
    `name` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE products (
    id BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2),
    category_id BIGINT,
    PRIMARY KEY (`id`),
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

INSERT INTO db_assign_13_jpa.students (name,email,major) VALUES
	 ('Nguyen Van An','an.nguyen@example.com','Computer Science'),
	 ('Tran Thi Binh','binh.tran@example.com','Information Technology'),
	 ('Le Minh Cuong','cuong.le@example.com','Software Engineering');

INSERT INTO db_assign_13_jpa.categories (name) VALUES
	 ('Phone'),
	 ('Tablet'),
	 ('Laptop');

INSERT INTO db_assign_13_jpa.products (name,price,category_id) VALUES
	 ('iPhone 15 Pro',999.00,1),
	 ('Samsung Galaxy S24',849.00,1),
	 ('Google Pixel 8',699.00,1),
	 ('iPad Air M2',599.00,2),
	 ('Samsung Galaxy Tab S9',749.00,2),
	 ('Xiaomi Pad 6',349.00,2),
	 ('MacBook Air M3',1099.00,3),
	 ('Dell XPS 13',1199.00,3),
	 ('Lenovo ThinkPad X1 Carbon',1499.00,3);
