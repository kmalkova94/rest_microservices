package ru.microservices.core.dao;

import ru.microservices.core.entities.OrderItems;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderItemsDAO extends CrudRepository<OrderItems, UUID> {
}
