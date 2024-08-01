package com.api.product.stock.control.controller;

import com.api.product.stock.control.product.DataProductDto;
import com.api.product.stock.control.product.Product;
import com.api.product.stock.control.product.ProductService;
import com.api.product.stock.control.product.RegisterProductDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/product")
@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/create")
    public ResponseEntity<DataProductDto> create (@RequestBody @Valid RegisterProductDto dto, UriComponentsBuilder uriBuilder) {
        Product product = service.insert(dto);

        var uri = uriBuilder.path("/product").buildAndExpand(product.getId()).toUri();

        return ResponseEntity.created(uri).body(new DataProductDto(dto.name(),dto.batch()));
    }

    @GetMapping("/read")
    public ResponseEntity<List<DataProductDto>> read () {
        List<DataProductDto> lista = service.findAll()
                .stream()
                .map((Product p) -> new DataProductDto(p.getName(),p.getBatch()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DataProductDto> update (@RequestBody @Valid RegisterProductDto dto, @PathVariable Long id) {
        Product product = service.update(dto,id);
        return ResponseEntity.ok(new DataProductDto(dto.name(),dto.batch()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}