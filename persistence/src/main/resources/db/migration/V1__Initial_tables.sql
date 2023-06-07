CREATE TABLE product(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `category` VARCHAR(100) NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    `value` DOUBLE NOT NULL,
    CONSTRAINT `pk_id` PRIMARY KEY (id)
);