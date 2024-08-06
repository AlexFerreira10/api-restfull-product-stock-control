package com.api.product.stock.control.product;

import com.api.product.stock.control.infra.EmailService;
import com.api.product.stock.control.supplier.Supplier;
import com.api.product.stock.control.supplier.SupplierRepository;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private EmailService emailService;

    @Transactional
    public DataProductDto insert(RegisterProductDto data) {
        if(productRepository.findByBatch(data.batch()) != null) {
            throw new DataIntegrityViolationException("Integrity Error: Batch already exists");
        }
        if(productRepository.findByBatch(data.batch()) != null) {
            throw new DataIntegrityViolationException("Integrity Error: Batch already exists");
        }
        Supplier supplier = supplierRepository.getReferenceById(data.idSupplier());
        Product product = productRepository.save(new Product(data,supplier));
        try {
            emailService.sendMail(product);
        } catch (MessagingException | UnsupportedEncodingException e) {
            System.out.println("Problemas no envio do email:" + e.getMessage());
        }

        return new DataProductDto(product.getName(), product.getBatch(),product.getSupplier().getName(), product.getSupplier().getCnpj());
    }

    public List<DataProductDto> findAll() {
        return productRepository.findAll().stream().map((Product x) -> new DataProductDto(x.getName(),x.getBatch(),x.getSupplier().getName(),x.getSupplier().getCnpj()))
                .collect(Collectors.toList());
    }

    public List<DataProductDto> findAllActive() {
        return productRepository.findAll().stream().filter(x -> x.getActive().equals(true))
                .map((Product x) -> new DataProductDto(x.getName(),x.getBatch(),x.getSupplier().getName(),x.getSupplier().getCnpj()))
                .collect(Collectors.toList());
    }

    public Product findById(Long id) {
        launcherEntityNotFoundException(id);
        return productRepository.getReferenceById(id);
    }

    @Transactional
    public DataProductDto update(RegisterProductDto data, Long id) {
        launcherEntityNotFoundException(id);
        Product product = findById(id);
        updateData(product, data);
        return new DataProductDto(product.getName(), product.getBatch(),product.getSupplier().getName(), product.getSupplier().getCnpj());
    }

    @Transactional
    public void delete(Long id) {
        launcherEntityNotFoundException(id);
        productRepository.deleteById(id);
    }

    @Transactional
    public void inactivate(Long id) {
        launcherEntityNotFoundException(id);
        Product product = findById(id);
        product.setActive(false);
    }

    @Transactional
    public void activate (Long id) {
        launcherEntityNotFoundException(id);
        Product product = findById(id);
        product.setActive(true);
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

    // Not found do getReferenceById
    private void launcherEntityNotFoundException(Long id) {
        if(productRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Product not found");
        }
    }
}