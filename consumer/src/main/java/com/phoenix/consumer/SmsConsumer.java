package com.phoenix.consumer;


import com.phoenix.consumer.model.SmsContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class SmsConsumer {
  private static final Logger LOG = LoggerFactory.getLogger(SmsConsumer.class);

  @RabbitListener(id = "sms-client-plivio", queues = "sms_data.queue", concurrency = "3-7")
  public void receive1(SmsContent message) {
    LOG.info("Sms received From Channel 1 : {}", message);
  }

  @RabbitListener(id = "sms-client-twilio", queues = "sms_data.queue", concurrency = "3-7")
  public void receive2(SmsContent message) {
    LOG.info("Sms received From Channel 2  : {}", message);
  }




}
