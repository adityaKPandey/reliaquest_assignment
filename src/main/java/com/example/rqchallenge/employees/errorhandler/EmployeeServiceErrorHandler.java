package com.example.rqchallenge.employees.errorhandler;

import com.example.rqchallenge.exceptions.DataNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class EmployeeServiceErrorHandler extends ResponseEntityExceptionHandler {


  @ExceptionHandler(DataNotFoundException.class)
  public ResponseEntity handleDataNotFoundException(DataNotFoundException dataNotFoundException){
     return new ResponseEntity(dataNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity handleException(Exception exception){
    return new ResponseEntity(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }


}
