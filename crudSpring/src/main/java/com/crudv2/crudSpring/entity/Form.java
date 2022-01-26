package com.crudv2.crudSpring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
public class Form {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String lastName;
    private String idT;
    private String age;
    private String cityOfBirth;
    private String idNum;
    private String imagen;
}
