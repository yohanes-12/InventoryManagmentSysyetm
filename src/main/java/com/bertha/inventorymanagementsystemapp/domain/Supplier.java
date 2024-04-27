package com.bertha.inventorymanagementsystemapp.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "supplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long supplierId;

    @Column( nullable = false, length = 30)
    @NotBlank
    private String supplierName;

    private String supplierEmail;

    private String supplierPhone;

    @OneToMany(mappedBy = "supplier")
    private List<Product> products;

    public List<Product> addProduct(Product product){
        products.add(product);
        return products;
    }

    public List<Product> removeProduct(Product product){
        products.remove(product);
        return products;
    }


}
