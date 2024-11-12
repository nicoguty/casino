package com.example.application.data.sistema;

import com.example.application.data.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "application_item_promos")
public class ItemsPromocion extends AbstractEntity {

    private Double puntos;
    private Double factor;
}
