package ru.microservices.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.microservices.core.entities.User;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsersDAO extends JpaRepository<User, UUID> {
}
