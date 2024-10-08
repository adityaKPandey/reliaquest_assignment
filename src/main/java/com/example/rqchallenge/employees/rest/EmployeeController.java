package com.example.rqchallenge.employees.rest;

import com.example.rqchallenge.employees.IEmployeeController;
import com.example.rqchallenge.employees.domain.Employee;
import com.example.rqchallenge.employees.service.EmployeeService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/employee")
public class EmployeeController implements IEmployeeController {

  @Autowired
  private EmployeeService employeeService;

  @Override
  public ResponseEntity<List<Employee>> getAllEmployees() throws IOException {
     return new ResponseEntity(employeeService.fetchAllEmployees(), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<Employee>> getEmployeesByNameSearch(String searchString) {
    return new ResponseEntity(employeeService.getEmployeesByNameSearch(searchString), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Employee> getEmployeeById(String id) {
    return new ResponseEntity(employeeService.getEmployeeById(id), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Integer> getHighestSalaryOfEmployees() {
    return new ResponseEntity<>(employeeService.getHighestSalaryOfEmployees(), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() {
    return new ResponseEntity<>(employeeService.getTopTenHighestEarningEmployeeNames(), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Employee> createEmployee(Map<String, Object> employeeInput) {
    return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<String> deleteEmployeeById(String id) {
    return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
  }

}
