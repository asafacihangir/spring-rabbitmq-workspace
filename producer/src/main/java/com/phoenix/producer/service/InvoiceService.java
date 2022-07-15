package com.phoenix.producer.service;

import com.github.javafaker.Faker;
import com.phoenix.producer.event.InvoiceCancelledMessage;
import com.phoenix.producer.event.InvoiceCreatedMessage;
import com.phoenix.producer.event.InvoicePaidMessage;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

  private final AmqpTemplate amqpTemplate;

  private final Faker faker;

  private static final String EXCHANGE = "invoice_data_exhange";
  private static final String ROUTING_KEY = "invoice_data_routing_key";

  private static final Logger LOG = LoggerFactory.getLogger(InvoiceService.class);

  public InvoiceService(
      @Qualifier("projectRabbitTemplate") AmqpTemplate amqpTemplate, Faker faker) {
    this.amqpTemplate = amqpTemplate;
    this.faker = faker;
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

      LOG.info("Invoice Created {}", invoiceCreatedMessage);
    }
  }

  public void sendCancelledInvoices() {
    final long size = ThreadLocalRandom.current().nextInt(1, 100);
    for (int i = 0; i < size; i++) {
      final InvoiceCancelledMessage invoiceCancelledMessage = new InvoiceCancelledMessage();
      invoiceCancelledMessage.setInvoiceNumber(
          "INV_" + ThreadLocalRandom.current().nextInt(0, 100000));
      invoiceCancelledMessage.setCancelDate(faker.date().toString());
      invoiceCancelledMessage.setReason(faker.lorem().characters(1, 100));

      sendInvoiceCancelled(invoiceCancelledMessage);

      LOG.info("Invoice Cancelled {}", invoiceCancelledMessage);
    }
  }

  public void sendPaidInvoices() {

    final long size = ThreadLocalRandom.current().nextInt(1, 100);
    for (int i = 0; i < size; i++) {
      final InvoicePaidMessage paidMessage = new InvoicePaidMessage();
      paidMessage.setInvoiceNumber("INV_" + ThreadLocalRandom.current().nextInt(0, 100000));
      paidMessage.setPaidDate(faker.date().toString());
      paidMessage.setPaymentNumber(UUID.randomUUID().toString());

      sendInvoicePaid(paidMessage);

      LOG.info("Invoice Paid {}", paidMessage);
    }
  }

  private void sendInvoiceCreated(InvoiceCreatedMessage message) {
    amqpTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, message);
  }

  private void sendInvoicePaid(InvoicePaidMessage message) {
    amqpTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, message);
  }

  private void sendInvoiceCancelled(InvoiceCancelledMessage message) {
    amqpTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, message);
  }
}
