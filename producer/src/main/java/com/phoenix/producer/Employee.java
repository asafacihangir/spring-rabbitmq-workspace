package com.phoenix.producer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public class Employee {

  private String employeeId;

  private String name;

  private String birthDate;

  public Employee(String employeeId, String name, String birthDate) {
    super();
    this.employeeId = employeeId;
    this.name = name;
    this.birthDate = birthDate;
  }

  public String getBirthDate() {
    return birthDate;
  }

  public String getEmployeeId() {
    return employeeId;
  }

  public String getName() {
    return name;
  }

  public void setBirthDate(String birthDate) {
    this.birthDate = birthDate;
  }

  public void setEmployeeId(String employeeId) {
    this.employeeId = employeeId;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Employee [employeeId=" + employeeId + ", name=" + name + ", birthDate=" + birthDate + "]";
  }

}
