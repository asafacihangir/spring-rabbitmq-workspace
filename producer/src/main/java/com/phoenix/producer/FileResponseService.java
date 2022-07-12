package com.phoenix.producer;

import com.github.javafaker.Faker;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class FileResponseService {

  private final AmqpTemplate amqpTemplate;

  private final Faker faker;

  private static final String EXCHANGE = "file_data_exhange";
  private static final String ROUTING_KEY = "file_data_routing_key";

  private static final Logger LOG = LoggerFactory.getLogger(FileResponseService.class);

  public FileResponseService(
      @Qualifier("projectRabbitTemplate") AmqpTemplate amqpTemplate, Faker faker) {
    this.amqpTemplate = amqpTemplate;
    this.faker = faker;
  }

  public void sendMessage() {
    for (int i = 0; i < 15; i++) {
      final FileResponseModel fileResponseModel = createFileResponse();
      amqpTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, fileResponseModel);
      LOG.info("Message is {}", fileResponseModel);
    }
  }

  private FileResponseModel createFileResponse() {
    final String fileId = UUID.randomUUID().toString();
    final String filename = faker.file().fileName();
    final String type = faker.file().mimeType();
    final String extension = faker.file().extension();
    final long size = ThreadLocalRandom.current().nextLong(1L, 1000000000L);

    final FileResponseModel fileResponse = new FileResponseModel();
    fileResponse.setFileId(fileId);
    fileResponse.setName(filename);
    fileResponse.setType(type);
    fileResponse.setExtension(extension);
    fileResponse.setSize(size);

    return fileResponse;
  }
}
