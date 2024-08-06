package com.api.product.stock.control.product;

public record DataProductDto(
        String name,
        Integer batch,
        String nameSupplier,
        String cnpj


) {
    public DataProductDto(String name, Integer batch, String nameSupplier ,String cnpj) {
        this.name = name;
        this.batch = batch;
        this.nameSupplier = nameSupplier;
        this.cnpj = cnpj;
    }
}
