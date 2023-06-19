ALTER TABLE product
ADD COLUMN created_at DATETIME NOT NULL DEFAULT NOW(),
ADD COLUMN modified_at DATETIME NOT NULL DEFAULT NOW(),
ADD COLUMN deleted_at DATETIME NULL DEFAULT NULL;

ALTER TABLE product
ALTER created_at DROP DEFAULT,
ALTER modified_at DROP DEFAULT;

CREATE INDEX ix_deleted_at ON product (deleted_at);
CREATE INDEX ix_category ON product (category);