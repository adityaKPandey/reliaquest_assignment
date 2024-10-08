package com.example.rqchallenge.employees.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class APIResponse {

  private String status;
  private List<Employee> data;

  public APIResponse(){

  }

  public APIResponse(String status, List<Employee> data) {
    this.status = status;
    this.data = data;
  }

  public String getStatus() {
    return status;
  }


  public void setStatus(String status) {
    this.status = status;
  }

  public List<Employee> getData() {
    return data;
  }

  public void setData(List<Employee> data) {
    this.data = data;
  }
}
