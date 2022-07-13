package com.phoenix.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phoenix.consumer.rabbitmq.DlxProcessingErrorHandler;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class FileResponseConsumer {
  private static final Logger LOG = LoggerFactory.getLogger(FileResponseConsumer.class);

  private static final String DEAD_EXCHANGE_NAME = "dead_letter_exchange";
  private final DlxProcessingErrorHandler dlxProcessingErrorHandler;

  private final ObjectMapper objectMapper;

  public FileResponseConsumer(@Qualifier("projectObjectMapper") ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
    this.dlxProcessingErrorHandler = new DlxProcessingErrorHandler(DEAD_EXCHANGE_NAME);
  }

  @RabbitListener(queues = "file_data.queue", concurrency = "3-7")
  public void listenQ1(
      Message message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
    try {
      var fileResponse = objectMapper.readValue(message.getBody(), FileResponseModel.class);
      if ("csv".equals(fileResponse.getExtension())) {
        throw new WrongFileException(fileResponse.toString());
      } else {
        LOG.info("On processing image : {}", fileResponse);
        channel.basicAck(tag, false);
      }
    } catch (Exception e) {
      LOG.warn("Error processing message : {} : {}", new String(message.getBody()), e.getMessage());
      dlxProcessingErrorHandler.handleErrorProcessingMessage(message, channel, tag);
    }
  }
}
