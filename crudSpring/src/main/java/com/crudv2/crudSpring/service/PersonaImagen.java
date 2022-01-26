package com.crudv2.crudSpring.service;

import com.crudv2.crudSpring.entity.Form;
import com.crudv2.crudSpring.entity.Imagen;
import com.crudv2.crudSpring.entity.Persona;
import com.crudv2.crudSpring.repository.PersonaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonaImagen {
    @Autowired
    private ImagenService imagenService;
    @Autowired
    private PersonaService personaService;
    @Autowired
    ModelMapper modelMapper;
    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public Persona savePersona(Form form) {
        Persona persona = modelMapper.map(form, Persona.class);
        Persona persona2 = personaService.savePersona(persona);
        Integer i = persona2.getId();
        Imagen I = new Imagen();
        I.setImagen(form.getImagen());
        I.setIdPersona(i.toString());
        imagenService.agregar(I);
        return persona;

    }
    public List<Persona> getPersonas() { return personaService.getPersonas();
    }

    public Persona getPersonaById(int id) {
        return personaService.getPersonaById(id);
    }

    public Persona getPersonaByName(String name) {
        return personaService.getPersonaByName(name);
    }

    public String deletePersona(int id) {
        Integer i = id;
        personaService.deletePersona(id);
        imagenService.delete(i.toString());
        return "" + id;
    }

    public Persona updatePersona(Form form) {
        Persona persona = modelMapper.map(form, Persona.class);
        Persona persona2 = personaService.updatePersona(persona);
        Integer i = persona2.getId();
        Imagen I = new Imagen();
        I.setImagen(form.getImagen());
        I.setIdPersona(i.toString());
        imagenService.editar(I);
        return persona;
    }


}