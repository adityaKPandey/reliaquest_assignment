package com.example.rqchallenge.employees.service;

import com.example.rqchallenge.employees.dao.EmployeeDataSource;
import com.example.rqchallenge.employees.domain.Employee;
import com.example.rqchallenge.exceptions.DataNotFoundException;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class EmployeeServiceTest {

  @InjectMocks
  private EmployeeService employeeService;

  @Mock
  private EmployeeDataSource employeeDataSource;

  @BeforeEach
  void setup(){
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testGetAllEmployees(){
    Mockito.when(employeeDataSource.fetchEmployees()).thenReturn(Arrays.asList(new Employee()));
    List<Employee> employees = employeeService.fetchAllEmployees();
    Assertions.assertEquals(employees.size(), 1);
  }

  @Test
  public void testGetAllEmployeesNoEmployeePresent(){
    Mockito.when(employeeDataSource.fetchEmployees()).thenReturn(Arrays.asList());
    Assertions.assertThrows(DataNotFoundException.class, () -> employeeService.fetchAllEmployees());
  }

  @Test
  public void testGetEmployeesByNameSearch(){
    Employee employee = new Employee();
    employee.setEmployeeName("JOE");
    Mockito.when(employeeDataSource.fetchEmployees()).thenReturn(Arrays.asList(employee));
    List<Employee> employees = employeeService.getEmployeesByNameSearch("JOE");
    Assertions.assertEquals(employees.size(), 1);
  }


  @Test
  public void testGetEmployeesByNameSearchDataNotFound(){
    Mockito.when(employeeDataSource.fetchEmployees()).thenReturn(Arrays.asList());
    Assertions.assertThrows(DataNotFoundException.class, () -> employeeService.getEmployeesByNameSearch("JOE"));
  }

  @Test
  public void testGetEmployeesByNameSearchThrowsException(){
    Mockito.when(employeeDataSource.fetchEmployees()).thenThrow(RuntimeException.class);
    Assertions.assertThrows(RuntimeException.class, () -> employeeService.getEmployeesByNameSearch("JOE"));
  }

  @Test
  public void testGetEmployeeById(){
    String id = "101";
    Employee employee = new Employee();
    employee.setEmployeeName("JOE");
    employee.setId(id);
    Mockito.when(employeeDataSource.fetchEmployees()).thenReturn(Arrays.asList(employee));
    Employee searchEmployee = employeeService.getEmployeeById(id);

    Assertions.assertEquals(searchEmployee.getId(), id);
  }

  @Test
  public void testGetEmployeeByIdThrowsException(){
    String id = "101";
    Mockito.when(employeeDataSource.fetchEmployees()).thenThrow(RuntimeException.class);
    Assertions.assertThrows(RuntimeException.class, () -> employeeService.getEmployeeById(id));
  }

  @Test
  public void testGetEmployeeByIdThrowsDataNotFoundException(){
    String id = "101";
    Mockito.when(employeeDataSource.fetchEmployees()).thenReturn(null);
    Assertions.assertThrows(DataNotFoundException.class, () -> employeeService.getEmployeeById(id));
  }

  @Test
  public void testGetHighestSalaryOfEmployee(){
    Employee employee1 = createEmployee("JOE", "101", 10000);

    Employee employee2 = createEmployee("MARK", "102", 90000);

    Mockito.when(employeeDataSource.fetchEmployees()).thenReturn(Arrays.asList(employee1, employee2));
    Assertions.assertEquals(employee2.getEmployeeSalary(), employeeService.getHighestSalaryOfEmployees());
  }

  @Test
  public void testGetTopTenHighestEarningEmployeeNames(){
    Employee employee1 = createEmployee("JOE", "101", 10000);

    Employee employee2 = createEmployee("MARK", "102", 90000);

    Mockito.when(employeeDataSource.fetchEmployees()).thenReturn(Arrays.asList(employee1, employee2));
    List<String> highestEarners = employeeService.getTopTenHighestEarningEmployeeNames();
    Assertions.assertEquals(highestEarners.size(), 2);

  }

  private Employee createEmployee(String joe, String s, int i) {
    Employee employee1 = new Employee();
    employee1.setEmployeeName(joe);
    employee1.setId(s);
    employee1.setEmployeeSalary(i);
    return employee1;
  }


}
