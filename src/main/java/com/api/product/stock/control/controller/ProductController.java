package com.api.product.stock.control.controller;

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
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<DataProductDto> create (@RequestBody @Valid RegisterProductDto dto, UriComponentsBuilder uriBuilder)  {
        DataProductDto productDto = productService.insert(dto);

        var uri = uriBuilder.path("/product").buildAndExpand(productDto.batch()).toUri();

        return ResponseEntity.created(uri).body(productDto);
    }

    @GetMapping("/read")
    public ResponseEntity<List<DataProductDto>> read () {
        var lista = productService.findAll();
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DataProductDto> update (@RequestBody @Valid RegisterProductDto dto, @PathVariable Long id) {
        var productDto = productService.update(dto, id);
        return ResponseEntity.ok(productDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/findAllActive")
    public ResponseEntity<List<DataProductDto>> findAllActive () {
        var list = productService.findAllActive();
        return ResponseEntity.ok(list);
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<Void> activate (@PathVariable Long id) {
        productService.activate(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/inactive/{id}")
    public ResponseEntity<Void> inactive (@PathVariable Long id) {
        productService.inactivate(id);
        return ResponseEntity.noContent().build();
    }
}