package com.example.application.data.sistema;

import com.example.application.data.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "application_promos")
public class Promocion extends AbstractEntity {

    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Double montoInicial;
    @OneToMany
    private List<ItemsPromocion> listaItems;
    private Double montoFinal;
    @Enumerated(EnumType.STRING)
    private Estado estado;


}
