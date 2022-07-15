package com.phoenix.producer.web;

import com.phoenix.producer.service.InvoiceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {

  private final InvoiceService invoiceService;

  public InvoiceController(InvoiceService invoiceService) {
    this.invoiceService = invoiceService;
  }

  @PostMapping("/send-cancelled-invoices")
  public String sendCancelledInvoices() {

    invoiceService.sendCancelledInvoices();
    return "Message sent to the RabbitMQ Successfully";
  }

  @PostMapping("/send-created-invoices")
  public String sendCreatedInvoices() {

    invoiceService.sendCreatedInvoices();
    return "Message sent to the RabbitMQ Successfully";
  }

  @PostMapping("/send-paid-invoices")
  public String sendPaidInvoices() {

    invoiceService.sendPaidInvoices();
    return "Message sent to the RabbitMQ Successfully";
  }
}
