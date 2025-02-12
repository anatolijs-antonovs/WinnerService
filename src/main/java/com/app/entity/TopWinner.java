package com.app.entity;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
public class TopWinner {
    @Id
    @GeneratedValue
    @Hidden
    private Long id;

    private String name;
    private Float amount;
}
