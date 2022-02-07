/*
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

import java.util.Arrays;
import java.util.List;
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


    }

    @Test
    void modelMapper() {}//Preguntar

    @Test
    void savePersona() {
        int actual = personaImagenService.savePersona(form);
        assertEquals(actual, 1);
    }

    @Test
    void getPersonas() {
        List<Persona> actual = personaImagenService.getPersonas();
        assertEquals(actual, Arrays.asList(persona));
    }

    @Test
    void getPersonaById() {
        Persona actual = personaImagenService.getPersonaById(persona.getId());
        assertEquals(actual, persona);
    }

    @Test
    void getPersonaByName() {
        Persona actual = personaImagenService.getPersonaByName(persona.getName());
        assertEquals(actual, persona);
    }

    @Test
    void deletePersona() {
        int actual = personaImagenService.deletePersona(persona);
        assertEquals(actual, 1);
    }

    @Test
    void updatePersona() {
        int actual = personaImagenService.updatePersona(form);
        assertEquals(actual, 1);

    }
}
*/