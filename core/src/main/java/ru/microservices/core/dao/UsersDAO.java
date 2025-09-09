package ru.microservices.core.dao;

import ru.microservices.core.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsersDAO extends CrudRepository<User, UUID> {
}
