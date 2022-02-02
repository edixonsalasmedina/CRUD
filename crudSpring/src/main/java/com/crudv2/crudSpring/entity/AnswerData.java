package com.crudv2.crudSpring.entity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import lombok.Generated;
import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
@Generated
public class AnswerData {

    private LocalDateTime timestamp;
    private HttpStatus status;
    public Optional<Object> data;

    public AnswerData(HttpStatus status, Optional<Object> data){
        LocalDate hoy = LocalDate.now();
        LocalTime ahora = LocalTime.now();
        this.timestamp = LocalDateTime.of(hoy,ahora);
        this.status = status;
        this.data = Optional.of(data.orElse("[]"));
    }


}