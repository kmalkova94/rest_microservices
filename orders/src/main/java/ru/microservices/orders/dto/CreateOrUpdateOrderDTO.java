package ru.microservices.orders.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrUpdateOrderDTO {
    @NotNull(message = "Пользователь не указан")
    private UUID userId;
    private String status = "CREATED";
    @NotNull(message = "Позиции заказа не указаны")
    private List<UUID> items;

}
