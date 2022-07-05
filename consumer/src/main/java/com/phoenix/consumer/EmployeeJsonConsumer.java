package com.phoenix.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EmployeeJsonConsumer {
  private static final Logger LOG = LoggerFactory.getLogger(EmployeeJsonConsumer.class);

  private final ObjectMapper objectMapper;

  public EmployeeJsonConsumer(@Qualifier("projectObjectMapper") ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @RabbitListener(queues = "course.employee", concurrency = "3-7")
  public void listen(String message) throws InterruptedException, JsonProcessingException {
    var employee = objectMapper.readValue(message, Employee.class);
    LOG.info("Employee is {}", employee);
  }

}
