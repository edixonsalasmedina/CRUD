package com.crudv2.crudSpring.controller;


import com.crudv2.crudSpring.entity.AnswerData;
import com.crudv2.crudSpring.entity.AnswerNotData;
import com.crudv2.crudSpring.entity.Form;
import com.crudv2.crudSpring.entity.Persona;
import com.crudv2.crudSpring.service.PersonaImagen;
import com.crudv2.crudSpring.service.PersonaService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class PersonaController {

    @Autowired
    private PersonaImagen service;
    @PostMapping("/addPersona")
    public ResponseEntity<Object>  addPersona(@RequestBody Form form) {
        ResponseEntity<Object> res;
        if(service.getPersonaByIdNum(form.getIdNum()) == null){
            int val = service.savePersona(form);
            res = ResponseEntity.status(HttpStatus.ACCEPTED).body(new AnswerData(HttpStatus.ACCEPTED,
                        Optional.of(val)));
        }
        else{
            res = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new AnswerNotData(HttpStatus.NOT_ACCEPTABLE,
                    "Persona ya registrada"));
        }
        return res;
    }
    @GetMapping("/personas")
    public ResponseEntity<Object> findAllPersonas() {
        List<Persona> personas = service.getPersonas();
        ResponseEntity<Object> res;
        if(personas.isEmpty()){
            res = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AnswerNotData(HttpStatus.NOT_FOUND, "No se " + "encontraron personas"));
        }
        else{res = ResponseEntity.status(HttpStatus.FOUND).body(new AnswerData(HttpStatus.FOUND, Optional.of(personas)));
        }
        return res;
    }

    @GetMapping("/personaById/{id}")
    public ResponseEntity<Object> findPersonaById(@PathVariable int id) {
        Persona persona = service.getPersonaById(id);
        ResponseEntity<Object> res;
        if(persona == null){
            res = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AnswerNotData(HttpStatus.NOT_FOUND, "No se encontro a la persona"));
        }
        else{res = ResponseEntity.status(HttpStatus.FOUND).body(new AnswerData(HttpStatus.FOUND, Optional.of(persona)));
        }
        return res;
    }



    @PutMapping("/update")
    public ResponseEntity<Object>  updatePersona(@RequestBody Form form) {
        ResponseEntity<Object> res;
        Persona p1 = service.getPersonaByIdNum(form.getIdNum());
        if( p1 != null ){
            if(p1.getId() == form.getId()){

                int val = service.updatePersona(form);
                res = ResponseEntity.status(HttpStatus.ACCEPTED).body(new AnswerData(HttpStatus.ACCEPTED,
                        Optional.of(val)));
            }
            else{res = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new AnswerNotData(HttpStatus.NOT_ACCEPTABLE,
                    "El numero de identificacion ya existe"));
            }
        }
        else{res = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new AnswerNotData(HttpStatus.NOT_ACCEPTABLE,
                    "Persona no esta registrada"));
        }
        return res;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deletePersona(@RequestBody Persona persona) {
        ResponseEntity<Object> res;
        Persona p1 = service.getPersonaByIdNum(persona.getIdNum());
        if(p1 != null){
            if(p1.getId() == persona.getId()){

                int val = service.deletePersona(persona);
                res = ResponseEntity.status(HttpStatus.ACCEPTED).body(new AnswerData(HttpStatus.ACCEPTED,
                        Optional.of(val)));
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
}
