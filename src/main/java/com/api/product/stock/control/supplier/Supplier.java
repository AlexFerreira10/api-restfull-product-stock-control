package com.api.product.stock.control.supplier;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Table(name = "tb_supplier")
@Entity(name = "Supplier")
@Schema(description = "Supplier data model")
public class Supplier {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "cnpj")
    private String cnpj;

    public Supplier(RegisterSupplierDto data) {
        this.name = data.name();
        this.email = data.email();
        this.cnpj = data.cnpj();
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", cnpj='" + cnpj + '\'' +
                '}';
    }
}