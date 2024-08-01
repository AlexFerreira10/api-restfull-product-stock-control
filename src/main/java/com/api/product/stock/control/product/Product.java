package com.api.product.stock.control.product;

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

    public Product(RegisterProductDto dados) {
        this.name = dados.name();
        this.quantify = dados.quantify();
        this.validity = dados.validity();
        this.price = dados.price();
        this.batch = dados.batch();
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantify=" + quantify +
                ", validity=" + validity +
                ", price=" + price +
                '}';
    }
}