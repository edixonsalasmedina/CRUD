package com.crudv2.crudSpring.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Document("imagen")
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String idPersona;
    private String imagen;

}
