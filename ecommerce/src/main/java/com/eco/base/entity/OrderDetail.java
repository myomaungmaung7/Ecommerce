package com.eco.base.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "order_details")
public class OrderDetail {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;
    private Double price;

    @ManyToOne(cascade = {CascadeType.DETACH})
    @JoinColumn(name = "order_id")
    private Order orders;

    @ManyToOne(cascade = {CascadeType.DETACH})
    @JoinColumn(name = "product_id")
    private Product product;
}
