package com.crudv2.crudSpring.repository;

import com.crudv2.crudSpring.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Integer > {
    Persona findByName(String name);
}
