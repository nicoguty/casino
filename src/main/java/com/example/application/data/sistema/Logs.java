package com.example.application.data.sistema;

import com.example.application.data.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "application_logs")
public class Logs extends AbstractEntity {

    private LocalDateTime fecha;
    private String Mensaje;
    @Enumerated(EnumType.STRING)
    private TipoMensaje tipoMensaje;

}
