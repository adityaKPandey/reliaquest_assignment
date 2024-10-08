package com.example.rqchallenge.employees.rest;

import com.example.rqchallenge.employees.domain.Employee;
import com.example.rqchallenge.employees.service.EmployeeService;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class EmployeeControllerTest {

  @Mock
  private EmployeeService employeeService;

  @InjectMocks
  private EmployeeController employeeController;

  @BeforeEach
  void setup(){
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testGetAllEmployees() throws IOException {
    Mockito.when(employeeService.fetchAllEmployees()).thenReturn(Arrays.asList(new Employee()));
    ResponseEntity<List<Employee>> employees = employeeController.getAllEmployees();
    Assertions.assertEquals(employees.getStatusCode(), HttpStatus.OK);
    Assertions.assertEquals(employees.getBody().size(), 1);
  }


}
