package ru.microservices.users.services;

import org.apache.coyote.BadRequestException;
import ru.microservices.core.dao.UsersDAO;
import ru.microservices.core.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.microservices.users.converters.UsersConverter;
import ru.microservices.users.dto.CreateOrUpdateUserDTO;
import ru.microservices.users.dto.UserDTO;

import java.rmi.ServerException;
import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

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
                .map(usersConverter::convertToUserDTO)
                .orElseThrow(() -> new ServerException("Не удалось сохранить пользователя"));

    }

    public List<UserDTO> getAllUsers() {
        /**
         * TODO
         * 1)add pagination
         */
        return StreamSupport.stream(usersDAO.findAll().spliterator(), false)
                .map(usersConverter::convertToUserDTO)
                .toList();
    }

    public UserDTO getUserById(UUID id) throws BadRequestException {
        return usersDAO.findById(id)
                .map(usersConverter::convertToUserDTO)
                .orElseThrow(() -> new BadRequestException("Пользователь не найден"));
    }
}
