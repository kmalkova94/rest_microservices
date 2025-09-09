package ru.microservices.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.microservices.core.entities.OrderItems;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderItemsDAO extends JpaRepository<OrderItems, UUID> {
}
