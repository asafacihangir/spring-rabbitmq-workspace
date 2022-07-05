package com.phoenix.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

  private final EmployeeeJsonProducer employeeeJsonProducer;

  private final Faker faker;

  public EmployeeService(EmployeeeJsonProducer employeeeJsonProducer, Faker faker) {
    this.employeeeJsonProducer = employeeeJsonProducer;
    this.faker = faker;
  }


  public void generateAndSendEmployeeData() throws JsonProcessingException {
    for (int i = 0; i<1000; i++){
      final String id = UUID.randomUUID().toString();
      final String fullName = faker.name().fullName();
      final String birthDate = faker.date().birthday().toString();

      final Employee employee = new Employee(id, fullName, birthDate);
      employeeeJsonProducer.sendMessage(employee);
    }

  }

}
