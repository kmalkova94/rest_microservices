package ru.microservices.orders.converters;

import org.springframework.stereotype.Service;
import ru.microservices.core.entities.Order;
import ru.microservices.core.entities.OrderItems;
import ru.microservices.orders.dto.OrderDTO;

@Service
public class OrdersConverter {
    public OrderDTO convertToOrderDTO(Order order) {
        return OrderDTO.builder()
                .orderId(order.getOrderId())
                .userId(order.getUserId())
                .items(order.getOrderItems().stream().map(OrderItems::getProductId).toList())
                .creationTimestamp(order.getCreationTimestamp())
                .build();
    }
}
