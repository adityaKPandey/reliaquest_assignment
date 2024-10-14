package com.example.rqchallenge.employees.errorhandler;

import com.example.rqchallenge.exceptions.DataNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class EmployeeServiceErrorHandlerTest {

  private EmployeeServiceErrorHandler employeeServiceErrorHandler = new EmployeeServiceErrorHandler();

  @Test
  public void testHandleDataNotFoundException(){
    ResponseEntity response = employeeServiceErrorHandler.handleDataNotFoundException(new DataNotFoundException("Error data not present"));
    Assertions.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
  }

  @Test
  public void testHandleIllegalArgumentException(){
    ResponseEntity response = employeeServiceErrorHandler.handleIllegalArgumentException(new DataNotFoundException("Error data not present"));
    Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
  }

  @Test
  public void testHandleException(){
    ResponseEntity response = employeeServiceErrorHandler.handleException(new Exception("Error occurred"));
    Assertions.assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
