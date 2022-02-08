package com.crudv2.crudSpring.controllerAdvice;

import com.crudv2.crudSpring.controllerAdvice.exceptions.ApiClienteInvalid;
import com.crudv2.crudSpring.entity.AnswerNotData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {


    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<Object> nullError(){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new AnswerNotData(HttpStatus.NOT_ACCEPTABLE, "El elemento que trata de usar es nulo"));

    }
    @ExceptionHandler(ArithmeticException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<Object> errorNumber(){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new AnswerNotData(HttpStatus.NOT_ACCEPTABLE, "division por cero"));
    }
    @ExceptionHandler(ArrayIndexOutOfBoundsException .class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<Object> errorIndex(){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new AnswerNotData(HttpStatus.NOT_ACCEPTABLE, "El indice esta por fuera del arreglo"));
    }
    @ExceptionHandler(NumberFormatException .class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<Object> errorFormat(){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new AnswerNotData(HttpStatus.NOT_ACCEPTABLE, "Esta tratando de castear una cadena a entero"));
    }
    }


