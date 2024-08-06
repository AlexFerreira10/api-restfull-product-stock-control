ALTER TABLE tb_product DROP COLUMN supplierEmail;
ALTER TABLE tb_product
ADD COLUMN id_supplier BIGINT NOT NULL,
ADD CONSTRAINT FK_id_supplier FOREIGN KEY (id_supplier) REFERENCES tb_supplier(id);