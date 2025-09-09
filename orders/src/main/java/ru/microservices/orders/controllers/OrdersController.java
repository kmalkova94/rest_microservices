package ru.microservices.orders.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.microservices.orders.dto.CreateOrUpdateOrderDTO;
import ru.microservices.orders.dto.OrderDTO;
import ru.microservices.orders.services.OrderService;

import java.io.IOException;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {
    private final OrderService orderService;

    @PostMapping
    public OrderDTO createOrder(@Valid @RequestBody CreateOrUpdateOrderDTO requestDTO) throws IOException, InterruptedException {
        OrderDTO result = orderService.createOrder(requestDTO);
        return result;
    }
}
