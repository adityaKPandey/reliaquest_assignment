package com.example.rqchallenge.employees.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {

  @JsonProperty("id")
  private String id;

  @JsonProperty("employee_name")
  private String employeeName;

  @JsonProperty("employee_salary")
  private Integer employeeSalary;

  @JsonProperty("employee_age")
  private Integer employeeAge;

  @JsonProperty("profile_image")
  private String profileImage;

  public Employee() {
  }

  public Employee(String id, String employeeName, Integer employeeSalary,
      Integer employeeAge, String profileImage) {
    this.id = id;
    this.employeeName = employeeName;
    this.employeeSalary = employeeSalary;
    this.employeeAge = employeeAge;
    this.profileImage = profileImage;
  }

  public String getId() {
    return id;
  }

  public String getEmployeeName() {
    return employeeName;
  }

  public Integer getEmployeeSalary() {
    return employeeSalary;
  }

  public Integer getEmployeeAge() {
    return employeeAge;
  }

  public String getProfileImage() {
    return profileImage;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setEmployeeName(String employeeName) {
    this.employeeName = employeeName;
  }

  public void setEmployeeSalary(Integer employeeSalary) {
    this.employeeSalary = employeeSalary;
  }

  public void setEmployeeAge(Integer employeeAge) {
    this.employeeAge = employeeAge;
  }

  public void setProfileImage(String profileImage) {
    this.profileImage = profileImage;
  }
}
