
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

    public int savePersona(Form form) {
        try{
            Persona persona = modelMapper.map(form, Persona.class);
            Persona persona2 = personaService.savePersona(persona);
            Integer i = persona2.getId();
            Imagen I = new Imagen();
            I.setImagen(form.getImagen());
            I.setIdPersona(i.toString());
            imagenService.agregar(I);
            return 1;
        } catch (Exception e){
            return  0;
        }

    }
    public List<Persona> getPersonas() { return personaService.getPersonas();
    }

    public Persona getPersonaById(int id) {
        return personaService.getPersonaById(id);
    }

    public Persona getPersonaByName(String name) {
        return personaService.getPersonaByName(name);
    }


    public Persona getPersonaByIdNum(String id) {
        return personaService.getPersonaByIdNum(id);
    }

    public int deletePersona(Persona persona) {
        Integer i = persona.getId();
        personaService.deletePersona(persona);
        Imagen I = imagenService.listImagenIdPersona(i.toString());
        return imagenService.delete(I);
    }

    public int updatePersona(Form form) {
        try {
            Persona persona = modelMapper.map(form, Persona.class);
            Persona persona2 = personaService.updatePersona(persona);
            Integer i = persona2.getId();
            Imagen I = imagenService.listImagenIdPersona(i.toString());
            I.setImagen(form.getImagen());
            imagenService.editar(I);
            return 1;
        } catch (Exception  e){
            return 0;
        }
    }


}