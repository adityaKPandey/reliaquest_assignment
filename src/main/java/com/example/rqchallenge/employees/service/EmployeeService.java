package com.example.rqchallenge.employees.service;


import static com.example.rqchallenge.employees.errorhandler.ErrorMessages.ERROR_MESSAGE_EMPLOYEE_HIGHEST_SALARY;
import static com.example.rqchallenge.employees.errorhandler.ErrorMessages.ERROR_MESSAGE_EMPLOYEE_ID_NOT_MATCHING;
import static com.example.rqchallenge.employees.errorhandler.ErrorMessages.ERROR_MESSAGE_EMPLOYEE_NAME_TEXT_NOT_PRESENT;
import static com.example.rqchallenge.employees.errorhandler.ErrorMessages.ERROR_MESSAGE_EMPLOYEE_TEN_HIGHEST_SALARY;
import static com.example.rqchallenge.employees.errorhandler.ErrorMessages.ERROR_MESSAGE_NO_EMPLOYEES_PRESENT;

import com.example.rqchallenge.employees.dao.EmployeeDataSource;
import com.example.rqchallenge.employees.domain.Employee;
import com.example.rqchallenge.exceptions.DataNotFoundException;
import java.text.MessageFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class EmployeeService {

  private EmployeeDataSource employeeDataSource;

  private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

  @Autowired
  public EmployeeService(
      @Qualifier("fileBasedEmployeeDataSource") EmployeeDataSource employeeDataSource) {
    this.employeeDataSource = employeeDataSource;
  }

  public List<Employee> fetchAllEmployees() {
    List<Employee> employees = employeeDataSource.fetchEmployees();
    if (!CollectionUtils.isEmpty(employees)) {
      LOGGER.info("Total number of employees {}", employees.size());
      return employees;
    }
    throw new DataNotFoundException(ERROR_MESSAGE_NO_EMPLOYEES_PRESENT);
  }


  public List<Employee> getEmployeesByNameSearch(String searchString) {
    try {
      List<Employee> employees = employeeDataSource.fetchEmployees();
      List<Employee> filteredEmployees = employees.stream()
          .filter(employee -> employee.getEmployeeName().contains(searchString)).collect(
              Collectors.toList());
      if (!CollectionUtils.isEmpty(filteredEmployees)) {
        LOGGER.info("Total number of matching employees present {}", filteredEmployees.size());
        return filteredEmployees;
      }
    } catch (Exception e) {
      LOGGER.error("Error occurred in getEmployeesByNameSearch for input search string {} : ",
          searchString, e);
      throw e;
    }

    LOGGER.warn("No record found while searching employees by name for input search string {}",
        searchString);
    throw new DataNotFoundException(
        MessageFormat.format(ERROR_MESSAGE_EMPLOYEE_NAME_TEXT_NOT_PRESENT, searchString));
  }


  public Employee getEmployeeById(String id) {
    try {
      List<Employee> employees = employeeDataSource.fetchEmployees();
      if(!CollectionUtils.isEmpty(employees)) {
        Optional<Employee> matchingEmployee = employees.stream()
            .filter(employee -> employee.getId().equals(id)).findFirst();
        if (matchingEmployee.isPresent()) {
          return matchingEmployee.get();
        }
      }
    } catch (Exception e) {
      LOGGER.error("Error occurred in getEmployeeById for input id {} : ", id, e);
      throw e;
    }

    LOGGER.warn("No record found while searching employees by id for input id {}", id);
    throw new DataNotFoundException(
        MessageFormat.format(ERROR_MESSAGE_EMPLOYEE_ID_NOT_MATCHING, id));
  }

  public Integer getHighestSalaryOfEmployees() {
    try {
      List<Employee> employees = employeeDataSource.fetchEmployees();
      if (!CollectionUtils.isEmpty(employees)) {
        Employee maxSalaryEmployee = employees.stream()
            .max(Comparator.comparingDouble(Employee::getEmployeeSalary)).get();

        LOGGER.info("Employee with id {} has highest salary", maxSalaryEmployee.getId());
        return maxSalaryEmployee.getEmployeeSalary().intValue();
      }
    } catch (Exception e) {
      LOGGER.error("Error occurred in getHighestSalaryOfEmployees : ", e);
      throw e;
    }
    LOGGER.warn("No employee present in system");
    throw new DataNotFoundException(ERROR_MESSAGE_EMPLOYEE_HIGHEST_SALARY);
  }

  public List<String> getTopTenHighestEarningEmployeeNames() {
    try {
      List<Employee> employees = employeeDataSource.fetchEmployees();
      if (!CollectionUtils.isEmpty(employees)) {
        List<String> highestEarners =  employees.stream()
            .sorted(Comparator.comparingDouble(Employee::getEmployeeSalary).reversed()).limit(10)
            .map(Employee::getEmployeeName).
            collect(Collectors.toList());

        LOGGER.info("Top ten highest earners {}", highestEarners);
        return highestEarners;
      }
    } catch (Exception e) {
      LOGGER.error("Error occurred in getTopTenHighestEarningEmployeeNames : ", e);
      throw e;
    }
    LOGGER.warn("No employee present in system");
    throw new DataNotFoundException(ERROR_MESSAGE_EMPLOYEE_TEN_HIGHEST_SALARY);
  }
}
