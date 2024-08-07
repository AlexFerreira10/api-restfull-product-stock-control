package com.api.product.stock.control.controller;

import com.api.product.stock.control.supplier.DataSupplierDto;
import com.api.product.stock.control.supplier.RegisterSupplierDto;
import com.api.product.stock.control.supplier.SupplierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;

@RequestMapping("/supplier")
@RestController
@Tag(name = "Supplier", description = "EndPoints for supplier control")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @PostMapping("/create")
    @Operation(summary = "Register a new supplier",
            description ="Register a new supplier",
            tags = {"Supplier"},
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content(
                            mediaType = "application/json")),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
            })
    public ResponseEntity<DataSupplierDto> create(@RequestBody @Valid RegisterSupplierDto data, UriComponentsBuilder uriBuilder) {
        var supplierDto = supplierService.create(data);

        var uri = uriBuilder.path("/create").buildAndExpand(supplierDto.cnpj()).toUri();

        return ResponseEntity.created(uri).body(supplierDto);
    }

    @GetMapping("/read")
    @Operation(summary = "Search all registered suppliers",
            description ="Search all registered suppliers",
            tags = {"Supplier"},

            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DataSupplierDto.class),
                            examples = @ExampleObject())),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
            })
    public ResponseEntity<List<DataSupplierDto>> read() {
        var list = supplierService.read();
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a supplier",
            description ="Delete a supplier",
            tags = {"Supplier"},
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "204",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
            })
    public ResponseEntity<DataSupplierDto> delete(@PathVariable Long id) {
        supplierService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update the registration of a supplier",
            description ="Update the registration of a supplier",
            tags = {"Supplier"},
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content(
                            mediaType = "application/json")),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
            })
    @PutMapping("/update/{id}")
    public ResponseEntity<DataSupplierDto> update(@RequestBody @Valid RegisterSupplierDto data, @PathVariable Long id) {
        var supplierDto = supplierService.update(id, data);
        return ResponseEntity.ok(supplierDto);
    }
}