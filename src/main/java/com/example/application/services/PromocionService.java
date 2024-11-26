package com.example.application.services;

import com.example.application.data.contador.ContadorRepository;
import com.example.application.data.sistema.ItemsPromocionRepository;
import com.example.application.data.sistema.Promocion;
import com.example.application.data.sistema.PromocionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PromocionService {


    PromocionRepository promocionRepository;
    ItemsPromocionRepository itemsRepository;
    ContadorRepository contadorRepository;

    public PromocionService(PromocionRepository promocionRepository, ItemsPromocionRepository itemsRepository,ContadorRepository contadorRepository) {
        this.promocionRepository = promocionRepository;
        this.itemsRepository = itemsRepository;
        this.contadorRepository = contadorRepository;
    }

    public Optional<Promocion> get (Long id){ return promocionRepository.findById(id);}

    public Promocion update(Promocion entity) { return promocionRepository.save(entity);}

    public void delete(Long id){ promocionRepository.deleteById(id);}

    public Page<Promocion> list(Pageable pageable) {
        return promocionRepository.findAll(pageable);
    }

    public Page<Promocion> list(Pageable pageable, Specification<Promocion> filter) {
        return promocionRepository.findAll(filter, pageable);
    }


    public Double actualizarMontoPromocion(Promocion promocion){
        //TODO: Crear metodo que realice la consulta del contador y haga los calculos
        // necesarios para actualizar monto de promo



        

        return null;
    }


}
