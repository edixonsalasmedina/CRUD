/*
package com.crudv2.crudSpring.controller;

import com.crudv2.crudSpring.entity.AnswerData;
import com.crudv2.crudSpring.entity.Form;
import com.crudv2.crudSpring.entity.Imagen;
import com.crudv2.crudSpring.entity.Persona;
import com.crudv2.crudSpring.repository.ImagenRepository;
import com.crudv2.crudSpring.repository.PersonaRepository;
import com.crudv2.crudSpring.service.ImagenService;
import com.crudv2.crudSpring.service.PersonaImagen;
import com.crudv2.crudSpring.service.PersonaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PersonaControllerTest {
    @Autowired
    private ObjectMapper objectMapper;
    private MockMvc mockMvc;
    @Mock
    private PersonaImagen service;
    @Mock
    private PersonaRepository personaRepository;
    @Mock
    private ImagenRepository imagenRepository;
    @Mock
    ModelMapper modelMapper;
    @Mock
    PersonaService personaService;
    @Mock
    ImagenService imagenService;
    @InjectMocks
    private PersonaController personaController;
    private Persona persona;
    private Persona persona2;
    private Persona persona3;
    private Form form;
    private Form form2;
    private Form form3;
    private Imagen I;


    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        //mockMvc = MockMvcBuilders.standaloneSetup(PersonaController.class).build();
        persona = new Persona(2, "edixon", "salas", "CC", "21", "Miranda", "1002808625");
        persona2 = new Persona(2, "salas", "salas", "CC", "21", "Miranda", "1002808625");
        form = new Form(2, "edixon", "salas", "CC", "21", "Miranda", "1002808625", "ahsdas12bjasbdkaasdax");
        form2 = new Form(2, "salas", "salas", "CC", "21", "Miranda", "1002808626", "ahsdas12bjasbdkaasdax");
        persona3 = new Persona(3, "edixon", "salas", "CC", "21", "Miranda", "1002808626");

        form3 = new Form(3, "edixon", "salas", "CC", "21", "Miranda", "1002808626", "ahsdas12bjasbdkaasdax");

        Integer i = persona.getId();
        I = new Imagen();
        I.setId("a");
        I.setIdPersona("2"); I.setImagen("ahsdas12bjasbdkaasdax");
        when(modelMapper.map(form, Persona.class)).thenReturn(persona);
        when(personaService.savePersona(persona)).thenReturn(persona);
        when(personaRepository.save(persona)).thenReturn(persona);
        when(personaService.getPersonas()).thenReturn(Arrays.asList(persona));
        when(personaService.getPersonas()).thenReturn(Arrays.asList(persona));
        when(personaRepository.findAll()).thenReturn(Arrays.asList(persona));
        when(personaService.getPersonaById(persona.getId())).thenReturn(persona);
        when(personaRepository.findById(persona.getId())).thenReturn(Optional.ofNullable(persona));
        when(personaRepository.findByName(persona.getName())).thenReturn(persona);
        when(personaService.getPersonaByName(persona.getName())).thenReturn(persona);
        //when(personaService.deletePersona(persona)).thenReturn(num);
        when(imagenService.listImagenIdPersona(i.toString())).thenReturn(I);
        when(imagenService.delete(I)).thenReturn(1);
        //when(personaRepository.deleteById(persona.getId())).thenReturn(num);
        when(imagenRepository.findItemByIdPersona(i.toString())).thenReturn(I);
        when(imagenRepository.deleteImagenByIdPersona(I.getIdPersona())).thenReturn(1);
        when(personaService.updatePersona(persona)).thenReturn(persona);//--
        when(service.getPersonaByIdNum(form.getIdNum())).thenReturn(null);
        when(service.getPersonaByIdNum(form3.getIdNum())).thenReturn(persona3);
        when(service.savePersona(form)).thenReturn(1);

    }


    @Test
    void addPersona() {
        assertEquals(202, personaController.addPersona(form).getStatusCodeValue());
        assertEquals(406, personaController.addPersona(form3).getStatusCodeValue());
    }
    @Test
    void findAllPersonas() {
        when(service.getPersonas()).thenReturn(Arrays.asList());
        assertEquals(404, personaController.findAllPersonas().getStatusCodeValue());
        when(service.getPersonas()).thenReturn(Arrays.asList(persona));
        assertEquals(302, personaController.findAllPersonas().getStatusCodeValue());
        //assertEquals( Arrays.asList(persona), personaController.findAllPersonas().getBody().data.get());
    }

    @Test
    void findPersonaById() {
        when(service.getPersonaById(2)).thenReturn(null);
        assertEquals(404, personaController.findPersonaById(2).getStatusCodeValue());
        when(service.getPersonaById(2)).thenReturn(persona);
        assertEquals(302, personaController.findPersonaById(2).getStatusCodeValue());

    }

    @Test
    void updatePersona() {
        when(service.getPersonaByIdNum(form.getIdNum())).thenReturn(persona);
        when(service.updatePersona(form)).thenReturn(1);
        assertEquals(202, personaController.updatePersona(form).getStatusCodeValue());
        when(service.getPersonaByIdNum(form.getIdNum())).thenReturn(persona3);
        assertEquals(406, personaController.updatePersona(form).getStatusCodeValue());
        when(service.getPersonaByIdNum(form.getIdNum())).thenReturn(null);
        assertEquals(406, personaController.updatePersona(form).getStatusCodeValue());
    }

    @Test
    void deletePersona() {
        when(service.getPersonaByIdNum(persona.getIdNum())).thenReturn(persona);
        when(service.deletePersona(persona)).thenReturn(1);
        assertEquals(202, personaController.deletePersona(persona).getStatusCodeValue());
        when(service.getPersonaByIdNum(persona.getIdNum())).thenReturn(persona3);
        assertEquals(406, personaController.deletePersona(persona).getStatusCodeValue());
        when(service.getPersonaByIdNum(persona.getIdNum())).thenReturn(null);
        assertEquals(406, personaController.deletePersona(persona).getStatusCodeValue());
    }
}

 */