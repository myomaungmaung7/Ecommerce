package com.eco.base.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "orders")
public class Order {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate orderDate;
    private Double totalAmount;

    @OneToOne(mappedBy = "orders")
    private Payment payment;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orders", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<OrderDetail> orderDetails;

    @ManyToOne(cascade = {CascadeType.DETACH})
    @JoinColumn(name = "user_id")
    private User user;
}
