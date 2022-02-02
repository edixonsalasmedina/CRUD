package com.crudv2.crudSpring.controller;


import com.crudv2.crudSpring.entity.AnswerData;
import com.crudv2.crudSpring.entity.AnswerNotData;
import com.crudv2.crudSpring.entity.Form;
import com.crudv2.crudSpring.entity.Persona;
import com.crudv2.crudSpring.service.PersonaImagen;
import com.crudv2.crudSpring.service.PersonaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@RestController
public class PersonaController {
    private List<Persona> l;

    @Autowired
    private PersonaImagen service;

    @Operation(summary = "Anadir una persona")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Persona agregada correctamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AnswerData.class)) }),
            @ApiResponse(responseCode = "406", description = "Numero de identificacion ya existente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AnswerNotData.class)) }) })
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
    @Operation(summary = "Listar todas las personas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Persona listadas correctamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AnswerData.class)) }),
            @ApiResponse(responseCode = "404", description = "No hay personas agregadas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AnswerNotData.class)) }) })
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
    @Operation(summary = "Buscar persona por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Si se encontro a la persona",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AnswerData.class)) }),
            @ApiResponse(responseCode = "404", description = "No se encontro a la persona",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AnswerNotData.class)) }) })
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


    @Operation(summary = "Actualizar una persona")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Se actualizo correctamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AnswerData.class)) }),
            @ApiResponse(responseCode = "406", description = "El numero de identificacion ya existe",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AnswerNotData.class)) }) })
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
    @Operation(summary = "Borrar una persona")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Se borro correctamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AnswerData.class)) }),
            @ApiResponse(responseCode = "406", description = "El numero de identificacion no coindice con el id de usuario o" +
                    " la persona no existe",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AnswerNotData.class)) }) })



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
