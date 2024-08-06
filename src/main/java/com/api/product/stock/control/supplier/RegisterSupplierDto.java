package com.api.product.stock.control.supplier;

import jakarta.validation.constraints.NotBlank;

public record RegisterSupplierDto(
        @NotBlank(message = "The name field can't be empty")
        String name,

        @NotBlank(message = "The supplier email field can't be empty")
        String email,

        @NotBlank(message = "The email field can't be empty")
        String cnpj
) {
}
