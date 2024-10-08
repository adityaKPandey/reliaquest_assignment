package com.example.rqchallenge.employees.service;


import static com.example.rqchallenge.employees.errorhandler.ErrorMessages.ERROR_MESSAGE_EMPLOYEE_HIGHEST_SALARY;
import static com.example.rqchallenge.employees.errorhandler.ErrorMessages.ERROR_MESSAGE_EMPLOYEE_ID_NOT_MATCHING;
import static com.example.rqchallenge.employees.errorhandler.ErrorMessages.ERROR_MESSAGE_EMPLOYEE_NAME_TEXT_NOT_PRESENT;
import static com.example.rqchallenge.employees.errorhandler.ErrorMessages.ERROR_MESSAGE_EMPLOYEE_TEN_HIGHEST_SALARY;

import com.example.rqchallenge.employees.errorhandler.ErrorMessages;
import com.example.rqchallenge.employees.dao.EmployeeDataSource;
import com.example.rqchallenge.employees.domain.Employee;
import com.example.rqchallenge.exceptions.DataNotFoundException;
import java.text.MessageFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class EmployeeService {

  private EmployeeDataSource employeeDataSource;

  @Autowired
  public EmployeeService(@Qualifier("fileBasedEmployeeDataSource")EmployeeDataSource employeeDataSource){
    this.employeeDataSource = employeeDataSource;
  }

  public List<Employee> fetchAllEmployees(){
    return employeeDataSource.fetchEmployees();
  }


  public List<Employee> getEmployeesByNameSearch(String searchString) {
    List<Employee> employees = employeeDataSource.fetchEmployees();
    List<Employee> filteredEmployees = employees.stream()
        .filter(employee -> employee.getEmployeeName().contains(searchString)).collect(
            Collectors.toList());
    if (!CollectionUtils.isEmpty(filteredEmployees))
      return filteredEmployees;

    throw new DataNotFoundException(
        MessageFormat.format(ERROR_MESSAGE_EMPLOYEE_NAME_TEXT_NOT_PRESENT, searchString));
  }

  




  public Employee getEmployeeById(String id) {

    List<Employee> employees = employeeDataSource.fetchEmployees();
    Optional<Employee> matchingEmployee = employees.stream().filter(employee -> employee.getId().equals(id)).findFirst();
    if (matchingEmployee.isPresent())
      return matchingEmployee.get();

    throw new DataNotFoundException(MessageFormat.format(ERROR_MESSAGE_EMPLOYEE_ID_NOT_MATCHING, id));
  }

  public Integer getHighestSalaryOfEmployees() {
    List<Employee> employees = employeeDataSource.fetchEmployees();
    if(!CollectionUtils.isEmpty(employees)){
     Employee maxSalaryEmployee = employees.stream().max(Comparator.comparingDouble(Employee :: getEmployeeSalary)).get();
     return  maxSalaryEmployee.getEmployeeSalary().intValue();
    }
    throw new DataNotFoundException(ERROR_MESSAGE_EMPLOYEE_HIGHEST_SALARY);
  }

  public List<String> getTopTenHighestEarningEmployeeNames() {
    List<Employee> employees = employeeDataSource.fetchEmployees();
    if(!CollectionUtils.isEmpty(employees)){
       return employees.stream().sorted(Comparator.comparingDouble(Employee :: getEmployeeSalary).reversed()).limit(10).map(Employee :: getEmployeeName).
           collect(Collectors.toList());
    }
    throw new DataNotFoundException(ERROR_MESSAGE_EMPLOYEE_TEN_HIGHEST_SALARY);
  }
}
