package ru.microservices.core.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders", schema = "rest_microservices")
public class Order {
    @Id
    @Column(name = "id", nullable = false)
    private UUID orderId;
    @Column(name = "user_id", nullable = false)
    private UUID userId;
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "created_at", nullable = false)
    private Timestamp creationTimestamp;

    @OneToMany(mappedBy = "order")
    private List<OrderItems> orderItems;
}
