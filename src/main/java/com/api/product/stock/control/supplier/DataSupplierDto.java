package com.api.product.stock.control.supplier;

public record DataSupplierDto(
        String name,
        String cnpj
) {
    public DataSupplierDto(String name, String cnpj) {
            this.name = name;
            this.cnpj = cnpj;
    }
}
