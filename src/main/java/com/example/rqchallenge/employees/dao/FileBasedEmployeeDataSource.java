package com.example.rqchallenge.employees.dao;

import com.example.rqchallenge.employees.errorhandler.ErrorMessages;
import com.example.rqchallenge.employees.domain.APIResponse;
import com.example.rqchallenge.employees.domain.Employee;
import com.example.rqchallenge.exceptions.DataNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository("fileBasedEmployeeDataSource")
public class FileBasedEmployeeDataSource implements EmployeeDataSource{

  @Autowired
  private RestTemplate employeeRestTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  @Value("${employee.datastore.file}")
  private String employeeDataStoreFile;


  public List<Employee> fetchEmployees() throws DataNotFoundException {
    try {
      InputStream inputStream = this.getClass().getResourceAsStream(employeeDataStoreFile);
      String employeeJson = new String(inputStream.readAllBytes(), Charset.defaultCharset());

      APIResponse apiResponse = objectMapper.readValue(employeeJson, APIResponse.class);
      return apiResponse.getData();
    } catch (IOException ex) {
      ex.printStackTrace();
      throw new DataNotFoundException(ErrorMessages.ERROR_MESSAGE_EMPLOYEE_FILE_READ);
    }
  }
}
