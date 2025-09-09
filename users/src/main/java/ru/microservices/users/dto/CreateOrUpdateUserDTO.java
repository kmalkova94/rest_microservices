package ru.microservices.users.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrUpdateUserDTO {
    @NotNull(message = "Имя пользователя не указано")
    private String username;
    @NotNull(message = "Email пользователя не указан")
    private String email;
    private String phoneNumber;
}
