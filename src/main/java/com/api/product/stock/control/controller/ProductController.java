package com.api.product.stock.control.controller;

import com.api.product.stock.control.product.DataProductDto;
import com.api.product.stock.control.product.Product;
import com.api.product.stock.control.service.EmailService;
import com.api.product.stock.control.service.ProductService;
import com.api.product.stock.control.product.RegisterProductDto;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/product")
@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private EmailService emailService;

    @PostMapping("/create")
    public ResponseEntity<DataProductDto> create (@RequestBody @Valid RegisterProductDto dto, UriComponentsBuilder uriBuilder) throws MessagingException, UnsupportedEncodingException {
        Product product = service.insert(dto);

        emailService.sendMail(product);

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

    @GetMapping("/findAllActive")
    public ResponseEntity<List<DataProductDto>> findAllActive () {
        var list = service.findAllActive().stream()
                .map((Product p) -> new DataProductDto(p.getName(),p.getBatch()))
                .collect(Collectors.toList());;

        return ResponseEntity.ok(list);
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<Void> activate (@PathVariable Long id) {
        service.activate(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/inactive/{id}")
    public ResponseEntity<Void> inactive (@PathVariable Long id) {
        service.inactivate(id);
        return ResponseEntity.noContent().build();
    }
}