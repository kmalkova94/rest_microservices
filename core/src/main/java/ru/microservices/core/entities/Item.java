package ru.microservices.core.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "items", schema = "rest_microservices")
public class Item {
    @Id
    @Column(name = "id", nullable = false)
    private UUID itemId;
    @Column(name = "name", nullable = false)
    private String itemName;

}
