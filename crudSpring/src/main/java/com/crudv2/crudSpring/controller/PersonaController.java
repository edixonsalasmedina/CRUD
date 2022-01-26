package com.crudv2.crudSpring.controller;


import com.crudv2.crudSpring.entity.Form;
import com.crudv2.crudSpring.entity.Persona;
import com.crudv2.crudSpring.service.PersonaImagen;
import com.crudv2.crudSpring.service.PersonaService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class PersonaController {

    @Autowired
    private PersonaImagen service;
    
    @PostMapping("/addPersona")
    public Persona addPersona(@RequestBody Form form) {
        return service.savePersona(form);
    }


    @GetMapping("/personas")
    public List<Persona> findAllPersonas() {
        return service.getPersonas();
    }

    @GetMapping("/PersonaById/{id}")
    public Persona findPersonaById(@PathVariable int id) {
        return service.getPersonaById(id);
    }


    @PutMapping("/update")
    public Persona updatePersona(@RequestBody Form form) {
        return service.updatePersona(form);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        return service.deletePersona(id);
    }
}