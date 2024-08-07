package com.api.product.stock.control.product;

import com.api.product.stock.control.supplier.Supplier;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@NoArgsConstructor
@Setter
@Getter
@Table(name = "tb_product")
@Entity(name = "Product")
@Schema(description = "Product data model")
public class Product {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Id
    private Long id;

    @Column(name = "batch")
    private Integer batch;

    @Column(name = "name")
    private String name;

    @Column(name = "quantify")
    private Integer quantify;

    @Column(name = "validity")
    private LocalDate validity;

    @Column(name = "price")
    private Double price;

    @Column(name = "active")
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_supplier")
    private Supplier supplier;


    public Product(RegisterProductDto dados, Supplier supplier) {
        this.active = true;
        this.name = dados.name();
        this.quantify = dados.quantify();
        this.validity = dados.validity();
        this.price = dados.price();
        this.batch = dados.batch();
        this.supplier = supplier;
    }

    public void actived() {
        setActive(true);
    }

    public void inactivated() {
        setActive(false);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", batch=" + batch +
                ", name='" + name + '\'' +
                ", quantify=" + quantify +
                ", validity=" + validity +
                ", price=" + price +
                ", active=" + active +
                ", supplier=" + supplier +
                '}';
    }
}