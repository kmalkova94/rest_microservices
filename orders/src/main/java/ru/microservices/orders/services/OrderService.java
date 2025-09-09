package ru.microservices.orders.services;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import ru.microservices.core.dao.OrderDAO;
import ru.microservices.core.entities.Order;
import ru.microservices.core.entities.OrderItems;
import ru.microservices.orders.clients.UserServiceClient;
import ru.microservices.orders.converters.OrdersConverter;
import ru.microservices.orders.dto.CreateOrUpdateOrderDTO;
import ru.microservices.orders.dto.OrderDTO;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final UserServiceClient userServiceClient;
    private final OrderDAO orderDAO;
    private final OrdersConverter ordersConverter;


    public OrderDTO createOrder(CreateOrUpdateOrderDTO requestDTO) throws IOException, InterruptedException {
        userServiceClient.getUserById(requestDTO.getUserId())
                .orElseThrow(() -> new BadRequestException("Пользователь не найден"));

        List<OrderItems> orderItemsList = requestDTO.getItems().stream()
                .map(itemId -> OrderItems.builder().productId(itemId).build()).toList();
        Order order = Order.builder()
                .userId(requestDTO.getUserId())
                .status(requestDTO.getStatus()).build();
        orderItemsList.forEach(order::addItem);
        Order savedOrder = orderDAO.save(order);
        orderDAO.flush();
        return orderDAO.findById(savedOrder.getOrderId())
                .map(ordersConverter::convertToOrderDTO)
                .orElseThrow(() -> new ServerException("Не удалось сохранить пользователя"));
    }
}
