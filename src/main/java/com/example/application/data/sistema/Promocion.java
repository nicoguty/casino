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
    private Double montoActual;
    private Double montoFinal;
    private Double ultimoValorContador;
    private Boolean huboCambioFactor;

    @OneToMany
    private List<ItemsPromocion> listaItems;

    @Enumerated(EnumType.STRING)
    private Estado estado;
    @Enumerated(EnumType.STRING)
    private OrdenPromocion orden;


}
