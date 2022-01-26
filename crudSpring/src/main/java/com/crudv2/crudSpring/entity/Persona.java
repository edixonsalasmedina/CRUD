package com.crudv2.crudSpring.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name =  "Persona")
public class Persona {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String lastName;
    private String idT;
    private String age;
    private String cityOfBirth;
    private String idNum;
}
