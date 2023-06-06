CREATE TABLE product(
    `id` BIGINT NOT NULL PRIMARY KEY,
    `category` VARCHAR(100) NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    `value` DOUBLE NOT NULL
);