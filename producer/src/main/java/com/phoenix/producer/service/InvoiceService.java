package com.phoenix.producer.service;

import com.github.javafaker.Faker;
import com.phoenix.producer.event.InvoiceCreatedMessage;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

  private final RabbitTemplate rabbitTemplate;

  private final Faker faker;

  private static final String EXCHANGE = "invoice_data_exhange";
  private static final String ROUTING_KEY = "invoice_data_routing_key";

  private static final Logger LOG = LoggerFactory.getLogger(InvoiceService.class);

  public InvoiceService(
      @Qualifier("projectRabbitTemplate") RabbitTemplate amqpTemplate, Faker faker) {
    this.rabbitTemplate = amqpTemplate;
    this.faker = faker;
  }

  @PostConstruct
  private void registerCallback() {
    rabbitTemplate.setConfirmCallback(
        (correlation, ack, reason) -> {
          if (correlation == null) {
            return;
          }

          if (ack) {
            LOG.info("Message with correlation {} published", correlation.getId());
          } else {
            LOG.warn("Invalid exchange for message with correlation {}", correlation.getId());
          }
        });

    rabbitTemplate.setReturnsCallback(
        returned -> {
          LOG.info("return callback");

          if (returned.getReplyText() != null
              && returned.getReplyText().equalsIgnoreCase("NO_ROUTE")) {
            var id =
                returned
                    .getMessage()
                    .getMessageProperties()
                    .getHeader("spring_returned_message_correlation")
                    .toString();
            LOG.warn("Invalid routing key for message {}", id);
          }
        });
  }

  public void sendCreatedInvoices() {
    final long size = ThreadLocalRandom.current().nextInt(1, 100);
    for (int i = 0; i < size; i++) {
      final InvoiceCreatedMessage invoiceCreatedMessage = new InvoiceCreatedMessage();
      invoiceCreatedMessage.setInvoiceNumber(
          "INV_" + ThreadLocalRandom.current().nextInt(0, 100000));
      invoiceCreatedMessage.setAmount(ThreadLocalRandom.current().nextDouble(0, 99999999));
      invoiceCreatedMessage.setCreatedDate(faker.date().toString());
      invoiceCreatedMessage.setCurrency(faker.currency().code());

      sendInvoiceCreated(invoiceCreatedMessage);
      sendDummyToInvalidExchange(invoiceCreatedMessage);
      sendDummyWithInvalidRoutingKey(invoiceCreatedMessage);

      LOG.info("Invoice Created {}", invoiceCreatedMessage);
    }
  }

  private void sendInvoiceCreated(InvoiceCreatedMessage message) {
    var correlationData = new CorrelationData(message.getInvoiceNumber());
    rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, message, correlationData);
  }

  private void sendDummyWithInvalidRoutingKey(InvoiceCreatedMessage message) {
    var correlationData = new CorrelationData(message.getInvoiceNumber());
    rabbitTemplate.convertAndSend("x.dummy2", "invalid-routing-key", message, correlationData);
  }

  private void sendDummyToInvalidExchange(InvoiceCreatedMessage message) {
    var correlationData = new CorrelationData(message.getInvoiceNumber());
    rabbitTemplate.convertAndSend("x.non-exists-exchange", "", message, correlationData);
  }
}
