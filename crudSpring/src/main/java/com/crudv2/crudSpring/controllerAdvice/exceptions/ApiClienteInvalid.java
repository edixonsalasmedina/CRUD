package com.crudv2.crudSpring.controllerAdvice.exceptions;


import com.crudv2.crudSpring.entity.AnswerNotData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class ApiClienteInvalid extends RuntimeException {
    AnswerNotData answerNotData;

    public ApiClienteInvalid(AnswerNotData answerNotData) {
        this.answerNotData = answerNotData;
    }

    public AnswerNotData getAnswerNotData() {
        return answerNotData;
    }

    public void setAnswerNotData(AnswerNotData answerNotData) {
        this.answerNotData = answerNotData;
    }

}