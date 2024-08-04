package com.api.product.stock.control.user;

import jakarta.validation.constraints.NotBlank;

public record RegisterAuthenticationDto(
        @NotBlank(message = "The login field can't be empty")
        String login,

        @NotBlank(message = "The password field can't be empty")
        String password
    ) {
}