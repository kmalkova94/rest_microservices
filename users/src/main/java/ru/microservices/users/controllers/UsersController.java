package ru.microservices.users.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.microservices.users.dto.CreateOrUpdateUserDTO;
import ru.microservices.users.dto.UserDTO;
import ru.microservices.users.services.UsersService;

import java.rmi.ServerException;

@RestController
@RequestMapping("/users")
//@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }
    @PostMapping
    public UserDTO createUser(@Valid CreateOrUpdateUserDTO requestDTO) throws ServerException {
        return usersService.createUser(requestDTO);
    }
}
