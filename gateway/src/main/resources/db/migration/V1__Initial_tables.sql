CREATE TABLE payment(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `order_id` BIGINT NOT NULL,
    `status` VARCHAR(10) NOT NULL,
    `qr_code` VARCHAR(1000),
    `external_id` VARCHAR(255),
    `created_at` DATETIME NOT NULL,
    `modified_at` DATETIME NOT NULL,
    `deleted_at` DATETIME DEFAULT NULL,
    CONSTRAINT `pk_id` PRIMARY KEY (`id`),
    INDEX ix_deleted_at (deleted_at)
);
