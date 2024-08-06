package com.api.product.stock.control.controller;

import com.api.product.stock.control.supplier.DataSupplierDto;
import com.api.product.stock.control.supplier.RegisterSupplierDto;
import com.api.product.stock.control.supplier.SupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;

@RequestMapping("/supplier")
@RestController
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @PostMapping("/create")
    public ResponseEntity<DataSupplierDto> create(@RequestBody @Valid RegisterSupplierDto data, UriComponentsBuilder uriBuilder) {
        var supplierDto = supplierService.create(data);

        var uri = uriBuilder.path("/create").buildAndExpand(supplierDto.cnpj()).toUri();

        return ResponseEntity.created(uri).body(supplierDto);
    }

    @GetMapping("/read")
    public ResponseEntity<List<DataSupplierDto>> read() {
        var list = supplierService.read();
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DataSupplierDto> delete(@PathVariable Long id) {
        supplierService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DataSupplierDto> update(@RequestBody @Valid RegisterSupplierDto data, @PathVariable Long id) {
        var supplierDto = supplierService.update(id, data);
        return ResponseEntity.ok(supplierDto);
    }
}