package com.phoenix.producer;

import java.time.LocalDateTime;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class FixedRateProducer {

  private final RabbitTemplate rabbitTemplate;

  private static final Logger LOG = LoggerFactory.getLogger(FixedRateProducer.class);

  public FixedRateProducer(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  @Scheduled(fixedRate = 500)
  public void sendMessage() {
    final String message = String.format("%s_%s", LocalDateTime.now(), UUID.randomUUID());
    LOG.info("Message is {}", message);
    rabbitTemplate.convertAndSend("course.fixedrate", "Fixed rate " + message);
  }
}
