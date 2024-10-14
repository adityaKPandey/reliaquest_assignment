package com.example.rqchallenge.employees.errorhandler;

import static com.example.rqchallenge.employees.Constants.MAX_ERROR_MESSAGE_LENGTH;

import com.example.rqchallenge.exceptions.DataNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class EmployeeServiceErrorHandler extends ResponseEntityExceptionHandler {


  @ExceptionHandler(DataNotFoundException.class)
  public ResponseEntity handleDataNotFoundException(DataNotFoundException dataNotFoundException){
     return new ResponseEntity(dataNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
  }

 @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity handleIllegalArgumentException(Exception exception){
    return new ResponseEntity(exception.getMessage().substring(0, Math.min(exception.getMessage().length(),
        MAX_ERROR_MESSAGE_LENGTH)), HttpStatus.BAD_REQUEST);
  }


  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
    return new ResponseEntity(exception.getMessage().substring(0, Math.min(exception.getMessage().length(),
        MAX_ERROR_MESSAGE_LENGTH)), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity handleException(Exception exception){
    return new ResponseEntity(exception.getMessage().substring(0, Math.min(exception.getMessage().length(),
        MAX_ERROR_MESSAGE_LENGTH)), HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
