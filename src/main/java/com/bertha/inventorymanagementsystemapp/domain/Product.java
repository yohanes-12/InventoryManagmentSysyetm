package com.bertha.inventorymanagementsystemapp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long itemId;


    @Column( nullable = false, length = 50)
    private String itemCategory;

    @Column( nullable = false, length = 50)
    private String ItemName;

    @Column( nullable = false)
    private double itemPrice;


    @ManyToOne
    private Supplier supplier;

    @OneToMany(mappedBy = "item")
    private List<Stock> stock;

    @ManyToMany(mappedBy = "item")
    private List<Order> orders;




}
