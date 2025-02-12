package com.app.entity;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Winner {
    @Id
    @GeneratedValue
    @Hidden
    private Long id;

    private String name;
    private Float amount;
}
