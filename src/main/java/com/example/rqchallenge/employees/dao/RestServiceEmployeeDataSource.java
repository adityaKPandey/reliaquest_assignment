package com.example.rqchallenge.employees.dao;

import com.example.rqchallenge.employees.Constants;
import com.example.rqchallenge.employees.errorhandler.ErrorMessages;
import com.example.rqchallenge.employees.domain.APIResponse;
import com.example.rqchallenge.employees.domain.Employee;
import com.example.rqchallenge.exceptions.DataNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Repository("restServiceEmployeeDataSource")
public class RestServiceEmployeeDataSource implements EmployeeDataSource{

  @Autowired
  private RestTemplate employeeRestTemplate;

  @Autowired
  private ObjectMapper objectMapper;


  public List<Employee> fetchEmployees() throws DataNotFoundException {
    //https://dummy.restapiexample.com/api/v1/employees
    try {
      ResponseEntity<APIResponse> employeeResponse = employeeRestTemplate.exchange(
          Constants.EMPLOYEES_URL,
          HttpMethod.GET,
          null,
          new ParameterizedTypeReference<APIResponse>() {
          });

      if (Objects.nonNull(employeeResponse) && employeeResponse.hasBody()) {
         APIResponse response = employeeResponse.getBody();
         return response.getData();
      }
    }catch(ResourceAccessException e){
      e.printStackTrace();
       throw new DataNotFoundException(ErrorMessages.ERROR_MESSAGE_EMPLOYEE_REST_SERVICE);
    }
    return new ArrayList<>();
  }
}
