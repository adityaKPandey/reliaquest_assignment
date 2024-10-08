package com.example.rqchallenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DataNotFoundException extends RuntimeException{

  private String message;

  public DataNotFoundException(String message){
    super(message);
    this.message = message;
  }

}
