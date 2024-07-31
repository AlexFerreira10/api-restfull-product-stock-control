CREATE TABLE tb_product (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(20) NOT NULL,
                            price DECIMAL(10,2) NOT NULL,
                            quantify INTEGER NOT NULL,
                            validity DATE NOT NULL
);