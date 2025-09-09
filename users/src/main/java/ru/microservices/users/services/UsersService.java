package ru.microservices.users.services;

import ru.microservices.core.dao.UsersDAO;
import ru.microservices.core.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.microservices.users.converters.UsersConverter;
import ru.microservices.users.dto.CreateOrUpdateUserDTO;
import ru.microservices.users.dto.UserDTO;

import java.rmi.ServerException;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersDAO usersDAO;
    private final UsersConverter usersConverter;

    public UserDTO createUser(CreateOrUpdateUserDTO requestDTO) throws ServerException {
        /**
         * TODO
         * 1) validate username + email
         * 2) add constraint on username + email unique
         */
        User user = User.builder()
                .email(requestDTO.getEmail())
                .username(requestDTO.getUsername())
                .phoneNumber(requestDTO.getPhoneNumber())
                .build();
        User result = usersDAO.save(user);

        return usersDAO.findById(result.getUserId())
                .map(UsersConverter::convertToUserDTO)
                .orElseThrow(() -> new ServerException("Не удалось сохранить пользователя"));

    }
}
