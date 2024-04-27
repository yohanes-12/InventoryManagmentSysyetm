package com.bertha.inventorymanagementsystemapp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockId;

    private Integer stockQuantity;

    @OneToOne
    private Product product;

    public void setQuantity(Integer quantity) {
        this.stockQuantity = quantity;
    }

    public Stock(Integer stockQuantity, Product product) {
        this.stockQuantity = stockQuantity;
        this.product = product;
    }
}
