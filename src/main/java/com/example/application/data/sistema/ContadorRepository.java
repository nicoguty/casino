package com.example.application.data.sistema;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ContadorRepository extends JpaRepository<Contador, Long>, JpaSpecificationExecutor<Contador> {

}
