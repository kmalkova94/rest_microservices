package ru.microservices.users.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.microservices.users.dto.CreateOrUpdateUserDTO;
import ru.microservices.users.dto.UserDTO;
import ru.microservices.users.services.UsersService;

import java.rmi.ServerException;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @PostMapping
    public UserDTO createUser(@Valid @RequestBody CreateOrUpdateUserDTO requestDTO) throws ServerException {
        return usersService.createUser(requestDTO);
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable("id") UUID userId) throws BadRequestException {
        return usersService.getUserById(userId);
    }
}
