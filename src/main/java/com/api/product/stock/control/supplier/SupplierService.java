package com.api.product.stock.control.supplier;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    @Transactional
    public DataSupplierDto create(RegisterSupplierDto data){
        var supplier = supplierRepository.save(new Supplier(data));
        return new DataSupplierDto(supplier.getName(),supplier.getCnpj());
    }

    public List<DataSupplierDto> read(){
        var list =  supplierRepository.findAll().stream().map((Supplier x) -> new DataSupplierDto(x.getName(),x.getCnpj()))
                .collect(Collectors.toList());
        return list;
    }

    @Transactional
    public void delete(Long id){
        launcherEntityNotFoundException(id);
        supplierRepository.deleteById(id);
    }

    @Transactional
    public DataSupplierDto update(Long id, RegisterSupplierDto data){
        launcherEntityNotFoundException(id);
        var supplier = supplierRepository.getReferenceById(id);
        updateData(supplier, data);
        return new DataSupplierDto(supplier.getName(),supplier.getCnpj());
    }

    private void updateData(Supplier supplier, RegisterSupplierDto data) {
        if(!supplier.getName().equals(data.name())) {
            supplier.setName(data.name());
        }
        if(!supplier.getEmail().equals(data.email())) {
            supplier.setEmail(data.email());
        }
        if(!supplier.getCnpj().equals(data.cnpj())) {
            supplier.setCnpj(data.cnpj());
        }
    }

    private void launcherEntityNotFoundException(Long id) {
        if(supplierRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Product not found");
        }
    }
}
