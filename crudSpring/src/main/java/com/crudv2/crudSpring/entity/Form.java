package com.crudv2.crudSpring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
public class Form {
    @Id
    @GeneratedValue
    @NotBlank
    private int id;
    @NotBlank
    @Size(min = 1, max = 20)
    private String name;
    @NotBlank
    @Size(min = 1, max = 20)
    private String lastName;
    @NotBlank
    @Size(min = 1, max = 20)
    private String idT;
    @NotBlank
    @Size(min = 1, max = 3)
    private String age;
    @NotBlank
    @Size(min = 1, max = 20)
    private String cityOfBirth;
    @NotBlank
    @Size(min = 10, max = 12)
    @NotEmpty(message = "entry idNum")
    @NotBlank
    @NotNull
    private String idNum;
    @NotBlank
    private String imagen;
}
