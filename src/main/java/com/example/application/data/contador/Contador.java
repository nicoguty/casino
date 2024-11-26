package com.example.application.data.contador;

import com.example.application.data.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contador")
public class Contador extends AbstractEntity {

    @Column(name = "base_pts", nullable = false)
    private Integer basePts; // Puntos base otorgados al jugador

    @Column(name = "created_dtm", nullable = false)
    private LocalDateTime createdDtm; // Fecha y hora de creación del registro

    @Column(name = "description")
    private String description;
}
