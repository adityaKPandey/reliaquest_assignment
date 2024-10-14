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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

  private static final Logger LOGGER = LoggerFactory.getLogger(RestServiceEmployeeDataSource.class);


  public List<Employee> fetchEmployees() throws DataNotFoundException {
    String employeeEndPoint = Constants.EMPLOYEES_URL;
    try {
      ResponseEntity<APIResponse> employeeResponse = employeeRestTemplate.exchange(employeeEndPoint,
          HttpMethod.GET,
          null,
          new ParameterizedTypeReference<APIResponse>() {
          });

      if (Objects.nonNull(employeeResponse) && employeeResponse.hasBody()) {
         APIResponse response = employeeResponse.getBody();
         return response.getData();
      }
    }catch(ResourceAccessException e){
      LOGGER.error("Error occurred in fetching employees from endpoint {} : ", employeeEndPoint, e);
      throw new DataNotFoundException(ErrorMessages.ERROR_MESSAGE_EMPLOYEE_REST_SERVICE);
    }
    return new ArrayList<>();
  }
}
