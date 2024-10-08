package com.example.rqchallenge.employees.dao;

import com.example.rqchallenge.employees.domain.Employee;
import com.example.rqchallenge.exceptions.DataNotFoundException;
import java.util.List;

public interface EmployeeDataSource {

  List<Employee> fetchEmployees() throws DataNotFoundException;

}
