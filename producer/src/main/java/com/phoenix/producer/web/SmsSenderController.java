package com.phoenix.producer.web;

import com.phoenix.producer.service.SmsSenderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sms")
public class SmsSenderController {

  private final SmsSenderService smsSenderService;

  public SmsSenderController(SmsSenderService invoiceService) {
    this.smsSenderService = invoiceService;
  }

  @PostMapping("/send")
  public String sendSms() {
    smsSenderService.sendSmsToQueue();
    return "Message sent to the RabbitMQ Successfully";
  }
}
