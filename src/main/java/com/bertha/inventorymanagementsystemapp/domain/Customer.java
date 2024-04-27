package com.bertha.inventorymanagementsystemapp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

        @Id
        @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
        private Long customerId;

        private String customerName;

        private String customerEmail;

        private String customerPhone;

       @OneToMany(mappedBy = "customer")
        private List<ProductOrder> orders;

}
