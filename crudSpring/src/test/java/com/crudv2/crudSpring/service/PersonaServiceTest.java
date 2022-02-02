package com.crudv2.crudSpring.service;

import com.crudv2.crudSpring.entity.Persona;
import com.crudv2.crudSpring.repository.PersonaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

class PersonaServiceTest {
    @Mock
    private PersonaRepository repository;

    @InjectMocks
    private PersonaService personaService;


    private Persona persona;
    private Persona persona2;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        persona = new Persona(0, "edixon", "salas", "CC", "21", "Miranda", "1002808625");
        persona2 = new Persona(2, "edixon", "salas", "CC", "21", "Miranda", "1002808625");

    }


    @Test
    void savePersona() {
        when(repository.save(persona)).thenReturn(persona);
        Persona actual = personaService.savePersona(persona);
        assertEquals(actual, persona);
    }

    @Test
    void savePersonas() {
        when(repository.saveAll(Arrays.asList(persona))).thenReturn(Arrays.asList(persona));
        List<Persona> actual = personaService.savePersonas(Arrays.asList(persona));
        assertEquals(actual, Arrays.asList(persona));
        assertThat(actual).hasSize(1);
    }

    @Test
    void getPersonas() {
        when(repository.findAll()).thenReturn(Arrays.asList(persona));
        List<Persona> actual = personaService.getPersonas();
        assertEquals(actual.get(0), persona);
        assertThat(actual).hasSize(1);


    }

    @Test
    void getPersonaById() {
        when(repository.findById(0)).thenReturn(Optional.ofNullable(persona));
        Persona actual = personaService.getPersonaById(0);
        assertEquals(actual, persona);

    }

    @Test
    void getPersonaByName() {
        when(repository.findByName("edixon")).thenReturn(persona);
        Persona actual = personaService.getPersonaByName("edixon");
        assertEquals(actual, persona);
    }

    @Test
    void deletePersona() {
        personaService.deletePersona(persona);
        verify(repository).deleteById(persona.getId());

    }

    @Test
    void updatePersona() {
        personaService.updatePersona(persona);
        verify(repository).save(persona);
    }
}