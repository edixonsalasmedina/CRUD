package com.crudv2.crudSpring.service;

import com.crudv2.crudSpring.entity.Imagen;
import com.crudv2.crudSpring.entity.Persona;
import com.crudv2.crudSpring.repository.ImagenRepository;
import com.crudv2.crudSpring.repository.PersonaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ImagenServiceTest {
    @Mock
    private ImagenRepository repository;

    @InjectMocks
    private ImagenService Service;


    private Imagen imagen;
    private Imagen imagen2;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        imagen = new Imagen("a", "0", "asdasdasdasd45asd45a5");
        imagen2 = new Imagen("b", "1", "asdasdasdasd45asd45a5");

    }

    @Test
    void listar() {
        when(repository.findAll()).thenReturn(Arrays.asList(imagen));
        List<Imagen> actual = Service.listar();
        assertEquals(actual.get(0), imagen);
        assertThat(actual).hasSize(1);
    }

    @Test
    void listImagenId() {
        when(repository.findItemById("a")).thenReturn(imagen);
        Imagen actual = Service.listImagenId("a");
        assertEquals(actual, imagen);
    }

    @Test
    void listImagenIdPersona() {
        when(repository.findItemByIdPersona("0")).thenReturn(imagen);
        Imagen actual = Service.listImagenIdPersona("0");
        assertEquals(actual, imagen);
    }

    @Test
    void agregar() {
        when(repository.save(imagen)).thenReturn(imagen);
        Imagen actual = Service.agregar(imagen);
        assertEquals(actual, imagen);
    }

    @Test
    void editar() {
        when(repository.save(imagen)).thenReturn(imagen);
        Service.editar(imagen);
        verify(repository).save(imagen);
    }

    @Test
    void delete() {
        when(repository.deleteImagenByIdPersona(imagen.getIdPersona())).thenReturn(1);
        int actual = Service.delete(imagen);
        verify(repository).deleteImagenByIdPersona(imagen.getIdPersona());
        assertEquals(actual, 1);

    }
}