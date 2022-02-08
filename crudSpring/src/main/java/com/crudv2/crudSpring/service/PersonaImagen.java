
package com.crudv2.crudSpring.service;

import com.crudv2.crudSpring.entity.*;
import com.crudv2.crudSpring.repository.PersonaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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

    public ResponseEntity<Object> savePersona(Form form) {
        ResponseEntity<Object> res;
        if(getPersonaByIdNum(form.getIdNum()) == null){
            //int val = service.savePersona(form);
            Persona persona = modelMapper.map(form, Persona.class);
            Persona persona2 = personaService.savePersona(persona);
            Integer i = persona2.getId();
            Imagen I = new Imagen();
            I.setImagen(form.getImagen());
            I.setIdPersona(i.toString());
            imagenService.agregar(I);
            res = ResponseEntity.status(HttpStatus.ACCEPTED).body(new AnswerNotData(HttpStatus.ACCEPTED, "Persona registrada correctamente"));
        }
        else{
            res = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new AnswerNotData(HttpStatus.NOT_ACCEPTABLE,
                    "Persona ya registrada"));
        }
        return res;


    }

    public ResponseEntity<Object> getPersonas() {
        List<Persona> personas = personaService.getPersonas();
        ResponseEntity<Object> res;
        if(personas.isEmpty()){
            res = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AnswerNotData(HttpStatus.NOT_FOUND, "No se " + "encontraron personas"));
        }
        else{res = ResponseEntity.status(HttpStatus.FOUND).body(new AnswerData(HttpStatus.FOUND, Optional.of(personas)));
        }
        return res;
    }

    public ResponseEntity<Object> getPersonaById(int id) {
        Persona persona = personaService.getPersonaById(id);
        ResponseEntity<Object> res;
        if(persona == null){
            res = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AnswerNotData(HttpStatus.NOT_FOUND, "No se encontro a la persona"));
        }
        else{res = ResponseEntity.status(HttpStatus.FOUND).body(new AnswerData(HttpStatus.FOUND, Optional.of(persona)));
        }
        return res;
    }



    public Persona getPersonaByIdNum(String id) {
        return personaService.getPersonaByIdNum(id);
    }

    public ResponseEntity<Object> deletePersona(Persona persona) {


        ResponseEntity<Object> res;
        Persona p1 = personaService.getPersonaByIdNum(persona.getIdNum());
        if(p1 != null){
            if(p1.getId() == persona.getId()){

                Integer i = persona.getId();
                personaService.deletePersona(persona);
                Imagen I = imagenService.listImagenIdPersona(i.toString());
                imagenService.delete(I);
                res = ResponseEntity.status(HttpStatus.ACCEPTED).body(new AnswerNotData(HttpStatus.ACCEPTED,
                        "Usuario eliminado correctamente"));
            }
            else{res = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new AnswerNotData(HttpStatus.NOT_ACCEPTABLE,
                    "El numero de identificacion no coindice con el id de usuario"));
            }
        }
        else{
            res = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new AnswerNotData(HttpStatus.NOT_ACCEPTABLE,
                    "Persona no existe"));
        }
        return res;

    }

    public ResponseEntity<Object> updatePersona(Form form) {

        ResponseEntity<Object> res;
        Persona p1 = personaService.getPersonaByIdNum(form.getIdNum());
        if (p1 != null) {
            if (p1.getId() == form.getId()) {
                Persona persona = modelMapper.map(form, Persona.class);
                Persona persona2 = personaService.updatePersona(persona);
                Integer i = persona2.getId();
                Imagen I = imagenService.listImagenIdPersona(i.toString());
                I.setImagen(form.getImagen());
                imagenService.editar(I);
                //int val = service.updatePersona(form);
                res = ResponseEntity.status(HttpStatus.ACCEPTED).body(new AnswerNotData(HttpStatus.ACCEPTED,
                        "Usuario actualizado correctamente"));
            } else {
                res = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new AnswerNotData(HttpStatus.NOT_ACCEPTABLE,
                        "El numero de identificacion no coindice con el id de usuario"));
            }
        } else {
            res = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new AnswerNotData(HttpStatus.NOT_ACCEPTABLE,
                    "Persona no esta registrada"));
        }
        return res;
    }

}