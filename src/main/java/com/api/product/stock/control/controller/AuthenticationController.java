package com.api.product.stock.control.controller;

import com.api.product.stock.control.infra.DataTokenJWTDto;
import com.api.product.stock.control.infra.TokenService;
import com.api.product.stock.control.user.RegisterAuthenticationDto;
import com.api.product.stock.control.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Tag(name = "Login", description = "EndPoint for token acess control")
public class AuthenticationController {

    // O Spring não consegue injetar o AuthenticationManager sozinho, precisamos fazer manualmente no SecurityConfig
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @Operation(summary = "Endpoint for login and access validation",
            description ="Endpoint for login and access validation",
            tags = {"Login"},
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
            })
    public ResponseEntity<?> doLogin(@RequestBody @Valid RegisterAuthenticationDto data) {
        // Passamos o nosso dto para o dto do Spring
        var token = new UsernamePasswordAuthenticationToken(data.login(),data.password());
        var authentication = manager.authenticate(token); // Precisa ser o dto do Spring

        var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new DataTokenJWTDto(tokenJWT));
    }
}