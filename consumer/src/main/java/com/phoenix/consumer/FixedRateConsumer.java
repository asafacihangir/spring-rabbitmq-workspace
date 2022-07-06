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
public class FixedRateConsumer {
  private static final Logger LOG = LoggerFactory.getLogger(FixedRateConsumer.class);

  private final ObjectMapper objectMapper;

  public FixedRateConsumer(@Qualifier("projectObjectMapper") ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }


  @RabbitListener(queues = "customer.order.q1", concurrency = "3-7")
  public void listenQ1(String message) throws InterruptedException {
    CustomerOrder employee;
    try {
       employee = objectMapper.readValue(message, CustomerOrder.class);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    LOG.info("Q1-Employee is {}", employee);
  }

  @RabbitListener(queues = "customer.order.q2", concurrency = "3-7")
  public void listenQ2(String message) throws InterruptedException {
    CustomerOrder employee;
    try {
      employee = objectMapper.readValue(message, CustomerOrder.class);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    LOG.info("Q2-Employee is {}", employee);
  }

}
