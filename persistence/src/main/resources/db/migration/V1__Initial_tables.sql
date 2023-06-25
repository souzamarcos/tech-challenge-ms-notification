CREATE TABLE product(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `category` VARCHAR(100) NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    `value` DOUBLE NOT NULL,
    `created_at` DATETIME NOT NULL,
    `modified_at` DATETIME NOT NULL,
    `deleted_at` DATETIME DEFAULT NULL,
    CONSTRAINT `pk_id` PRIMARY KEY (id),
    INDEX ix_deleted_at (deleted_at),
    INDEX ix_category (category)
);


CREATE TABLE client(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `cpf` VARCHAR(11) NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `created_at` DATETIME NOT NULL,
    `modified_at` DATETIME NOT NULL,
    `deleted_at` DATETIME DEFAULT NULL,
    CONSTRAINT `pk_id` PRIMARY KEY (id),
    INDEX ix_deleted_at (deleted_at)
);