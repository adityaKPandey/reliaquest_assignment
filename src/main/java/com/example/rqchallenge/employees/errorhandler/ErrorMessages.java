package com.example.rqchallenge.employees.errorhandler;

public class ErrorMessages {

  public static String ERROR_MESSAGE_EMPLOYEE_FILE_READ = "Failure in reading employee data from file";
  public static String ERROR_MESSAGE_EMPLOYEE_REST_SERVICE = "Failure in fetching employee data from service";

  public static String ERROR_MESSAGE_EMPLOYEE_NAME_TEXT_NOT_PRESENT = "Could not find any employee whose name contains or matches input text {0}";
  public static String ERROR_MESSAGE_EMPLOYEE_ID_NOT_MATCHING = "Could not find any employee matching the given employee id {0}";
  public static String ERROR_MESSAGE_EMPLOYEE_HIGHEST_SALARY = "Error occurred while finding employee with highest salary";
  public static String ERROR_MESSAGE_EMPLOYEE_TEN_HIGHEST_SALARY = "Error occurred while finding ten highest salaried employees";


}
