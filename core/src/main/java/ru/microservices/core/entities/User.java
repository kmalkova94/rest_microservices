package ru.microservices.core.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "users", schema = "rest_microservices")
@Builder
@Getter
public class User {
    @Id
    @Column(name = "id", nullable = false, columnDefinition = "uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "created_timestamp", nullable = false)
    @Builder.Default
    private Timestamp creationTimestamp = new Timestamp(Instant.now().toEpochMilli());
}
