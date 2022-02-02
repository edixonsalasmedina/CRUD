package com.crudv2.crudSpring.service;

import com.crudv2.crudSpring.entity.Persona;
import com.crudv2.crudSpring.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//UPDATEEEEEEEEEEE
@Service
public class PersonaService {
    @Autowired
    private PersonaRepository repository;

    public Persona savePersona(Persona persona) {
        return repository.save(persona);
    }

    public List<Persona> savePersonas(List<Persona> personas) {
        return repository.saveAll(personas);
    }

    public List<Persona> getPersonas() { return repository.findAll(); }

    public Persona getPersonaById(int id) {
        return repository.findById(id).orElse(null);
    }
    public Persona getPersonaByIdNum(String id) {
        return repository.findByIdNum(id);
    }

    public Persona getPersonaByName(String name) {
        return repository.findByName(name);
    }

    public void deletePersona(Persona persona) {
        repository.deleteById(persona.getId());
    }

    public Persona updatePersona(Persona persona) {
        return repository.save(persona);
    }


}