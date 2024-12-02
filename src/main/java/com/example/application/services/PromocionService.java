package com.example.application.services;

import com.example.application.broadcaster.BroadcasterActualizar;
import com.example.application.data.contador.ContadorRepository;
import com.example.application.data.sistema.*;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class PromocionService {


    PromocionRepository promocionRepository;
    ItemsPromocionRepository itemsRepository;
    ContadorRepository contadorRepository;
    Double numeroObtenido = 1d;

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

    @Scheduled(fixedRate = 5000)
    @Transactional
    public void obtencionDeContador() {
        Logger.getAnonymousLogger().info("Consultando contador...");
        List<Promocion> lista = promocionRepository.findAll((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("estado"), Estado.EN_CURSO));
        if(!lista.isEmpty()) {
            lista.forEach(promocion -> {
                if (promocion.getHuboCambioFactor()) {
                    ItemsPromocion itemNuevo = new ItemsPromocion();
                    itemNuevo.setFactor(promocion.getFactorConversion());
                    itemNuevo.setPuntos(numeroObtenido);
                    itemsRepository.save(itemNuevo);
                    promocion.setHuboCambioFactor(false);
                } else {
                    ItemsPromocion itemExistente = itemsRepository.getReferenceById(promocion.getId());
                    itemExistente.setPuntos(numeroObtenido); // Asegúrate de que `promocion.getId()` sea el ID correcto.
                }
                promocion.setMontoActual(promocion.getMontoActual() + promocion.getFactorConversion() * (numeroObtenido));
                promocionRepository.save(promocion);
                numeroObtenido++;

            });
            BroadcasterActualizar.broadcast(true);
        }

    }


}
