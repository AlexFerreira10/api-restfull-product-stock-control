package com.api.product.stock.control.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.api.product.stock.control.product.DataProductDto;
import com.api.product.stock.control.product.RegisterProductDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;
import com.api.product.stock.control.product.ProductService;

@RequestMapping("/product")
@RestController
@Tag(name = "Product", description = "EndPoints for products control")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    @Operation(summary = "Register a new Product",
            description ="Register a new Product",
            tags = {"Product"},
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content(
                            mediaType = "application/json")),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
            })
    public ResponseEntity<DataProductDto> create (@RequestBody @Valid RegisterProductDto dto, UriComponentsBuilder uriBuilder)  {
        DataProductDto productDto = productService.insert(dto);

        var uri = uriBuilder.path("/product").buildAndExpand(productDto.batch()).toUri();

        return ResponseEntity.created(uri).body(productDto);
    }

    @GetMapping("/read")
    @Operation(summary = "Search all registered products",
            description ="Search all registered products",
            tags = {"Product"},

            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DataProductDto.class),
                            examples = @ExampleObject())),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
            })
    public ResponseEntity<List<DataProductDto>> read () {
        var lista = productService.findAll();
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update the registration of a product",
            description ="Update the registration of a product",
            tags = {"Product"},
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content(
                            mediaType = "application/json")),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
            })
    public ResponseEntity<DataProductDto> update (@RequestBody @Valid RegisterProductDto dto, @PathVariable Long id) {
        var productDto = productService.update(dto, id);
        return ResponseEntity.ok(productDto);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a product",
            description ="Delete a product",
            tags = {"Product"},
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "204",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
            })
    public ResponseEntity<Void> delete (@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/findAllActive")
    @Operation(summary = "Search active registered products",
            description ="Search active registered products",
            tags = {"Product"},

            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DataProductDto.class),
                            examples = @ExampleObject())),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
            })
    public ResponseEntity<List<DataProductDto>> findAllActive () {
        var list = productService.findAllActive();
        return ResponseEntity.ok(list);
    }

    @PutMapping("/activate/{id}")
    @Operation(summary = "Activate the product by id",
            description ="Activate the product by id",
            tags = {"Product"},
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "204",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
            })
    public ResponseEntity<Void> activate (@PathVariable Long id) {
        productService.activate(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/inactive/{id}")
    @Operation(summary = "Inactive the product by id",
            description ="Inactive the product by id",
            tags = {"Product"},
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "204",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
            })
    public ResponseEntity<Void> inactive (@PathVariable Long id) {
        productService.inactivate(id);
        return ResponseEntity.noContent().build();
    }
}