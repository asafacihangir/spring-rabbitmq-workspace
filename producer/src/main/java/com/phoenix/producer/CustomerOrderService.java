package com.phoenix.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CustomerOrderService {

  private final RabbitTemplate rabbitTemplate;

  private final Faker faker;

  private final ObjectMapper objectMapper;

  private static final Logger LOG = LoggerFactory.getLogger(CustomerOrderService.class);

  private static final String EXCHANGE = "orders_data_exchange";
  private static final String ROUTING_KEY = "customer.order";

  public CustomerOrderService(RabbitTemplate rabbitTemplate, Faker faker, ObjectMapper objectMapper) {
    this.rabbitTemplate = rabbitTemplate;
    this.faker = faker;
    this.objectMapper = objectMapper;
  }

  @Scheduled(fixedRate = 500)
  public void sendMessage() {
    final CustomerOrder customerOrder = createCustomerOrder();
    String json;
    try {
      json = objectMapper.writeValueAsString(customerOrder);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }

    LOG.info("Message is {}", json);
    rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, json);
  }

  private CustomerOrder createCustomerOrder() {
    final String userId = UUID.randomUUID().toString();
    final String orderId = UUID.randomUUID().toString();
    final String product = faker.commerce().productName();
    final String price = faker.commerce().price();
    final String date = LocalDate.now().toString();

    final CustomerOrder customerOrder = new CustomerOrder();
    customerOrder.setUserId(userId);
    customerOrder.setOrderId(orderId);
    customerOrder.setProductName(product);
    customerOrder.setPrice(price);
    customerOrder.setDate(date);

    return customerOrder;
  }
}
