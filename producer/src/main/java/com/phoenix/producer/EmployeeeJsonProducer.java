package com.phoenix.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EmployeeeJsonProducer {

  private final RabbitTemplate rabbitTemplate;

  private final ObjectMapper objectMapper;

  private static final Logger LOG = LoggerFactory.getLogger(EmployeeeJsonProducer.class);

  public EmployeeeJsonProducer(RabbitTemplate rabbitTemplate,
      @Qualifier("projectObjectMapper") ObjectMapper objectMapper) {
    this.rabbitTemplate = rabbitTemplate;
    this.objectMapper = objectMapper;
  }

  public void sendMessage(Employee employee) throws JsonProcessingException {
    LOG.info("Message is {}", employee);
    var json = objectMapper.writeValueAsString(employee);
    rabbitTemplate.convertAndSend("course.employee", json);
  }
}
