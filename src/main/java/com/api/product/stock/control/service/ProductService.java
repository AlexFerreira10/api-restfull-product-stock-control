package com.api.product.stock.control.service;

import com.api.product.stock.control.product.Product;
import com.api.product.stock.control.product.ProductRepository;
import com.api.product.stock.control.product.RegisterProductDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional
    public Product insert(RegisterProductDto data) {
        if(repository.findByBatch(data.batch()) != null) {
            throw new DataIntegrityViolationException("Integrity Error: Batch already exists");
        }
        return repository.save(new Product(data));
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(Long id) {
        if(repository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Product not found");
        }
        return repository.getReferenceById(id);
    }

    @Transactional
    public Product update(RegisterProductDto data, Long id) {
        if(repository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Product not found");
        }
        Product product = findById(id);
        updateData(product, data);
        return product;
    }

    @Transactional
    public void delete(Long id) {
        if(repository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Product not found");
        }
        repository.deleteById(id);
    }

    private void updateData(Product product, RegisterProductDto data) {
        if(!product.getName().equals(data.name())) {
            product.setName(data.name());
        }
        if(!product.getPrice().equals(data.price())) {
            product.setPrice(data.price());
        }
        if(!product.getQuantify().equals(data.quantify())) {
            product.setQuantify(data.quantify());
        }
        if(!product.getValidity().equals(data.validity())){
            product.setValidity(data.validity());
        }
        if(!product.getBatch().equals(data.batch())){
            product.setBatch(data.batch());
        }
    }
}