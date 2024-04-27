package com.bertha.inventorymanagementsystemapp.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_order")
public class ProductOrder {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long orderId;

    private LocalDate orderDate;


    private Integer orderQuantity;

    private BigDecimal orderPrice;

    @OneToMany(mappedBy = "order")
    private List<Product> products;

    @ManyToOne
    private Customer customer;

    public void addProduct(Product product) {
        products.add(product);
        product.setOrder(this);
    }

    public void removeProduct(Product product) {
        products.remove(product);
        product.setOrder(null);
    }

}
