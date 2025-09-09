package ru.microservices.core.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "orders_items", schema = "rest_microservices")
public class OrderItems {
    @Id
    @Column(name = "id", nullable = false)
    private UUID itemId;
    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
