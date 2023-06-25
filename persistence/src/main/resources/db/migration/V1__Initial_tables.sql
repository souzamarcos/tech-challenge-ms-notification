CREATE TABLE product(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `category` VARCHAR(100) NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    `value` DOUBLE NOT NULL,
    `created_at` DATETIME DEFAULT NOW(),
    `modified_at` DATETIME DEFAULT NOW(),
    `deleted_at` DATETIME DEFAULT NULL,
    CONSTRAINT `pk_id` PRIMARY KEY (id)
);

CREATE INDEX ix_deleted_at ON product (deleted_at);
CREATE INDEX ix_category ON product (category);

CREATE TABLE client(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `cpf` VARCHAR(11) NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `created_at` DATETIME DEFAULT NOW(),
    `modified_at` DATETIME DEFAULT NOW(),
    `deleted_at` DATETIME DEFAULT NULL,
    CONSTRAINT `pk_id` PRIMARY KEY (id)
);

CREATE INDEX ix_deleted_at ON client (deleted_at);