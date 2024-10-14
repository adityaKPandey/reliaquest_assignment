package com.example.rqchallenge.employees.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class FileBasedEmployeeDataSourceTest {

  @Mock
  private ObjectMapper objectMapper;

  @InjectMocks
  private FileBasedEmployeeDataSource fileBasedEmployeeDataSource;


  @BeforeEach
  void setup(){
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testFetchEmployees(){
    Assertions.assertThrows(Exception.class, () -> fileBasedEmployeeDataSource.fetchEmployees());
  }

}
