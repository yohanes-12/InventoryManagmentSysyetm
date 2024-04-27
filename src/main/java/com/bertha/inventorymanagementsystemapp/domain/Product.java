package com.bertha.inventorymanagementsystemapp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long productId;

    @Column( nullable = false, length = 50)
    private String productCode;

    @Column( nullable = false, length = 50)
    private String productName;

    @Column( nullable = false, length = 50)
    private String productCategory;

    @Column( nullable = false)
    private BigDecimal Price;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Supplier supplier;

    @OneToOne
    private Stock stock;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private ProductOrder order;


}
