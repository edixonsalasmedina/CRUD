
package com.crudv2.crudSpring.service;

import com.crudv2.crudSpring.entity.Form;
import com.crudv2.crudSpring.entity.Imagen;
import com.crudv2.crudSpring.entity.Persona;
import com.crudv2.crudSpring.repository.ImagenRepository;
import com.crudv2.crudSpring.repository.PersonaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpRange;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PersonaImagenTest {
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
    private PersonaImagen personaImagenService;


    private Persona persona;
    private Persona persona2;
    private Form form;
    private Form form2;
    private Imagen I;
    @BeforeEach
    void setUp() {
        Long num = Long.valueOf(1);
        MockitoAnnotations.openMocks(this);
        persona = new Persona(2, "edixon", "salas", "CC", "21", "Miranda", "1002808625");
        persona2 = new Persona(2, "salas", "salas", "CC", "21", "Miranda", "1002808625");
        form = new Form(2, "edixon", "salas", "CC", "21", "Miranda", "1002808625", "ahsdas12bjasbdkaasdax");
        form2 = new Form(2, "salas", "salas", "CC", "21", "Miranda", "1002808625", "ahsdas12bjasbdkaasdax");
        Integer i = persona.getId();
        I = new Imagen();
        I.setId("a");
        I.setIdPersona("2"); I.setImagen("ahsdas12bjasbdkaasdax");
        when(modelMapper.map(form, Persona.class)).thenReturn(persona);
        when(personaService.savePersona(persona)).thenReturn(persona);
        when(personaRepository.save(persona)).thenReturn(persona);
        when(personaService.getPersonas()).thenReturn(Arrays.asList(persona));

        when(personaRepository.findAll()).thenReturn(Arrays.asList(persona));
        when(personaService.getPersonaById(persona.getId())).thenReturn(persona);
        when(personaRepository.findById(persona.getId())).thenReturn(Optional.ofNullable(persona));
        when(personaRepository.findByName(persona.getName())).thenReturn(persona);
        when(personaImagenService.getPersonaByIdNum(persona.getIdNum())).thenReturn(persona);
        //when(personaService.deletePersona(persona)).thenReturn(num);
        when(imagenService.listImagenIdPersona(i.toString())).thenReturn(I);
        when(imagenService.delete(I)).thenReturn(1);
        //when(personaRepository.deleteById(persona.getId())).thenReturn(num);
        when(imagenRepository.findItemByIdPersona(i.toString())).thenReturn(I);
        when(imagenRepository.deleteImagenByIdPersona(I.getIdPersona())).thenReturn(1);
        when(personaService.updatePersona(persona)).thenReturn(persona);//--


    }

    @Test
    void modelMapper() {}//Preguntar

    @Test
    void savePersona() {
        when(personaImagenService.getPersonaByIdNum(form.getIdNum())).thenReturn(persona);
        ResponseEntity<Object> actual = personaImagenService.savePersona(form);
        assertEquals(HttpStatus.NOT_ACCEPTABLE, actual.getStatusCode());

        when(personaImagenService.getPersonaByIdNum(form.getIdNum())).thenReturn(null);
        actual = personaImagenService.savePersona(form);
        assertEquals(HttpStatus.ACCEPTED, actual.getStatusCode());
    }

    @Test
    void getPersonas() {
        ResponseEntity<Object> actual = personaImagenService.getPersonas();
        assertEquals(HttpStatus.FOUND, actual.getStatusCode());
        assertEquals("Optional[["+persona.toString()+"]])", actual.getBody().toString().split(", data=")[1]);
        when(personaService.getPersonas()).thenReturn(Arrays.asList());
        actual = personaImagenService.getPersonas();
        assertEquals(actual.getStatusCode(), HttpStatus.NOT_FOUND);

    }
    @Test
    void getPersonaById() {


        ResponseEntity<Object> actual = personaImagenService.getPersonaById(persona.getId());
        assertEquals("Optional["+persona.toString()+"])", actual.getBody().toString().split(", data=")[1]);
        when(personaService.getPersonaById(persona.getId())).thenReturn(null);
        actual = personaImagenService.getPersonaById(persona.getId());
        assertEquals(actual.getStatusCode(), HttpStatus.NOT_FOUND);
    }
    @Test
    void getPersonaByIdNum() {

        Persona actual = personaImagenService.getPersonaByIdNum(persona.getIdNum());
        assertEquals(actual, persona);
    }

    @Test
    void deletePersona() {
        ResponseEntity<Object> actual = personaImagenService.deletePersona(persona);
        assertEquals(actual.getStatusCode(), HttpStatus.ACCEPTED);
        when(personaService.getPersonaByIdNum(persona.getIdNum())).thenReturn(null);
        actual = personaImagenService.deletePersona(persona);
        assertEquals(actual.getStatusCode(), HttpStatus.NOT_ACCEPTABLE);
    }

    @Test
    void updatePersona() {
        ResponseEntity<Object> actual = personaImagenService.updatePersona(form);
        assertEquals(actual.getStatusCode(), HttpStatus.ACCEPTED);
        when(personaImagenService.getPersonaByIdNum(form.getIdNum())).thenReturn(null);
        actual = personaImagenService.updatePersona(form);
        assertEquals(actual.getStatusCode(), HttpStatus.NOT_ACCEPTABLE);

    }

    }
