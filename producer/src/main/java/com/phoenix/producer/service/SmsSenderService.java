package com.phoenix.producer.service;

import com.github.javafaker.Faker;
import com.phoenix.producer.model.SmsContent;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SmsSenderService {

  private final AmqpTemplate amqpTemplate;

  private final Faker faker;

  private static final String EXCHANGE = "sms_data_exhange";
  private static final String ROUTING_KEY = "sms_data_routing_key";

  private static final Logger LOG = LoggerFactory.getLogger(SmsSenderService.class);

  public SmsSenderService(
      @Qualifier("projectRabbitTemplate") AmqpTemplate amqpTemplate, Faker faker) {
    this.amqpTemplate = amqpTemplate;
    this.faker = faker;
  }

  public void sendSmsToQueue() {
    final long size = ThreadLocalRandom.current().nextInt(1, 100);
    for (int i = 0; i < size; i++) {
      final SmsContent smsContent = new SmsContent();
      smsContent.setContent(faker.lorem().characters(100, 400));

      final int recipientCount = ThreadLocalRandom.current().nextInt(1, 6);
      for (int j = 0; j < recipientCount; j++){
        smsContent.getRecipients().add(faker.phoneNumber().cellPhone());
      }
      amqpTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, smsContent);

      LOG.info("SmsContent Created {}", smsContent);
    }
  }

}
