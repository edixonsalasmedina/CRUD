package com.crudv2.crudSpring.controller;


import com.crudv2.crudSpring.controllerAdvice.exceptions.ApiClienteInvalid;
import com.crudv2.crudSpring.entity.AnswerData;
import com.crudv2.crudSpring.entity.AnswerNotData;
import com.crudv2.crudSpring.entity.Form;
import com.crudv2.crudSpring.entity.Persona;
import com.crudv2.crudSpring.service.PersonaImagen;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


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
    public ResponseEntity<Object>  addPersona(@Valid @RequestBody Form form) {
        return service.savePersona(form);
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
        return service.getPersonas();

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
        return service.getPersonaById(id);

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
        return service.updatePersona(form);
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
    public ResponseEntity<Object> deletePersona(@Valid @RequestBody Persona persona) {
        return service.deletePersona(persona);
    }
}
