package com.example.rqchallenge.employees.rest;

import com.example.rqchallenge.employees.IEmployeeController;
import com.example.rqchallenge.employees.domain.Employee;
import com.example.rqchallenge.employees.service.EmployeeService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

  private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

  @Override
  public ResponseEntity<List<Employee>> getAllEmployees() throws IOException {
     LOGGER.info("Received GET request for all employees");
     return new ResponseEntity(employeeService.fetchAllEmployees(), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<Employee>> getEmployeesByNameSearch(String searchString) {
    LOGGER.info("Received GET request for employees with search string {}", searchString);
    return new ResponseEntity(employeeService.getEmployeesByNameSearch(searchString), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Employee> getEmployeeById(String id) {
    LOGGER.info("Received GET request for employee with id {}", id);
    return new ResponseEntity(employeeService.getEmployeeById(id), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Integer> getHighestSalaryOfEmployees() {
    LOGGER.info("Received GET request for highest salary of employee with id ");
    return new ResponseEntity<>(employeeService.getHighestSalaryOfEmployees(), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() {
    LOGGER.info("Received GET request for top ten highest earning employee names ");
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
