package com.crudv2.crudSpring.repository;

import com.crudv2.crudSpring.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PersonaRepository extends JpaRepository<Persona, Integer > {
    Persona findByName(String name);
    Persona findByIdNum(String idNum);
    @Transactional
    @Modifying

    void deleteById(int id);
    void deleteByIdNum(String idNum);
    //Persona deleteById(int id);
}
