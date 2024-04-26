package com.bertha.inventorymanagementsystemapp.domain;

import com.bertha.inventorymanagementsystemapp.domain.enumClass.OrderStatus;
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
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long orderId;

    private LocalDate orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @NotNull
    private Integer orderQuantity;

    @NotNull
    private BigDecimal orderPrice;

    @OneToMany(mappedBy = "order")
    private List<Product> products;

    @ManyToOne
    private Customer customer;

}
