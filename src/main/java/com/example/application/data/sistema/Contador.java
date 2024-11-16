package com.example.application.data.sistema;

import com.example.application.data.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Contador extends AbstractEntity {
    Double contador;
}
