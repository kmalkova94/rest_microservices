package ru.microservices.core.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders", schema = "rest_microservices")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @Column(name = "id", nullable = false, columnDefinition = "uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID orderId;
    @Column(name = "user_id", nullable = false)
    private UUID userId;
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "created_at", nullable = false)
    @Builder.Default
    private Timestamp creationTimestamp = new Timestamp(Instant.now().toEpochMilli());

    @OneToMany(mappedBy = "order", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<OrderItems> orderItems;

    public void addItem(OrderItems item) {
        item.setOrder(this);
        if(CollectionUtils.isEmpty(orderItems)){
            orderItems = new ArrayList<>();
        }
        orderItems.add(item);
    }
}
