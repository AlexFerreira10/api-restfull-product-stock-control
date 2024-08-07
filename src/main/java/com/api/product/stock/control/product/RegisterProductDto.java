package com.api.product.stock.control.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record RegisterProductDto(

        @NotBlank(message = "The name field can't be empty")
        String name,

        @NotNull(message = "The batch field can't be empty")
        Integer batch,

        @NotNull(message = "The quantify field can't be empty")
        Integer quantify,

        @NotNull(message = "The validity field can't be empty")
        LocalDate validity,

        @NotNull(message = "The price field can't be empty")
        Double price,

        @NotNull(message = "The id supplier field can't be empty")
        Long idSupplier
) {
}
