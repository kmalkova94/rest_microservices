package ru.microservices.users.converters;

import ru.microservices.core.entities.User;
import org.springframework.stereotype.Service;
import ru.microservices.users.dto.UserDTO;

@Service
public class UsersConverter {
    public static UserDTO convertToUserDTO(User user) {
        return UserDTO.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .creationTimestamp(user.getCreationTimestamp())
                .build();
    }
}
