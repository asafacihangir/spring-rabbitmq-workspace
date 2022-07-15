package com.phoenix.consumer;

import com.phoenix.consumer.event.InvoiceCancelledMessage;
import com.phoenix.consumer.event.InvoiceCreatedMessage;
import com.phoenix.consumer.event.InvoicePaidMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class InvoiceConsumer {
  private static final Logger LOG = LoggerFactory.getLogger(InvoiceConsumer.class);

  @RabbitListener(queues = "invoice_data.queue", concurrency = "3-7")
  public void handleInvoiceCreated(InvoiceCreatedMessage message) {
    LOG.info("Invoice created : {}", message);
  }

  @RabbitListener(queues = "invoice_data.queue", concurrency = "3-7")
  public void handleInvoicePaid(InvoicePaidMessage message) {
    LOG.info("Invoice paid : {}", message);
  }

  @RabbitListener(queues = "invoice_data.queue", concurrency = "3-7")
  public void handleInvoiceCancelled(InvoiceCancelledMessage message) {
    LOG.info("Invoice cancelled : {}", message);
  }
}
