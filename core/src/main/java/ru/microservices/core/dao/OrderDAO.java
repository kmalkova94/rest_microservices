package ru.microservices.core.dao;

import ru.microservices.core.entities.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderDAO extends CrudRepository<Order, UUID> {
}
