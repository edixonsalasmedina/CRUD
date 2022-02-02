package com.crudv2.crudSpring.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.Generated;
import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
@Generated
public class AnswerNotData {

    private LocalDateTime timestamp;
    private HttpStatus status;
    private String message;


    public AnswerNotData(HttpStatus status, String message){
        LocalDate hoy = LocalDate.now();
        LocalTime ahora = LocalTime.now();
        this.timestamp = LocalDateTime.of(hoy,ahora);
        this.status = status;
        this.message = message;
    }

}