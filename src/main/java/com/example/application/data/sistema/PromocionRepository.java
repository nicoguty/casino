package com.example.application.data.sistema;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PromocionRepository extends JpaRepository<Promocion, Long>, JpaSpecificationExecutor<Promocion> {
}
