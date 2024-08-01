package com.api.product.stock.control.product;

public record DataProductDto(
        String name,
        Integer batch

) {
    public DataProductDto(String name, Integer batch) {
        this.name = name;
        this.batch = batch;
    }
}
