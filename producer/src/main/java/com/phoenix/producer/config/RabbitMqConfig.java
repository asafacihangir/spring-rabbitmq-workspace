package com.phoenix.producer.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

  @Bean
  DirectExchange fileDataExchange() {
    return new DirectExchange("invoice_data_exhange");
  }

  @Bean
  Queue fileDataQueue() {
    return QueueBuilder.durable("invoice_data.queue")
        .withArgument("x-dead-letter-exchange", "dead_letter_exchange")
        .withArgument("x-dead-letter-routing-key", "dead_letter_routing_key")
        .build();
  }

  @Bean
  Binding fileDataBinding() {
    return BindingBuilder.bind(fileDataQueue()).to(fileDataExchange()).with("invoice_data_routing_key");
  }


  @Bean
  DirectExchange deadLetterExchange() {
    return new DirectExchange("dead_letter_exchange");
  }

  @Bean
  Queue dlq() {
    return QueueBuilder.durable("dead_letter.queue").build();
  }


  @Bean
  Binding DLQbinding() {
    return BindingBuilder.bind(dlq()).to(deadLetterExchange()).with("dead_letter_routing_key");
  }

  @Bean
  public MessageConverter jsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean("projectRabbitTemplate")
  public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
    final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(jsonMessageConverter());
    return rabbitTemplate;
  }
}
