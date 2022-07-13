package com.phoenix.consumer;

import com.rabbitmq.client.Channel;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class FileResponseConsumer {
  private static final Logger LOG = LoggerFactory.getLogger(FileResponseConsumer.class);



  @RabbitListener(queues = "file_data.queue", concurrency = "3-7")
  public void listenQ1(FileResponseModel fileResponse)
      throws InterruptedException, WrongFileException {
    LOG.info("Q1-FileResponse is {}", fileResponse);
    if ( "csv".equals(fileResponse.getExtension())){
      throw new WrongFileException(fileResponse.toString());
    }
  }

  /*
  @RabbitListener(queues = "file_data.queue", concurrency = "3-7")
  public void listenQ1(FileResponseModel fileResponse, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag)
      throws InterruptedException, WrongFileException, IOException {
    LOG.info("Q1-FileResponse is {}", fileResponse);
    if ( "csv".equals(fileResponse.getExtension())){
      channel.basicReject(tag, false);
      return;
    }
    channel.basicAck(tag, false);
  }*/

}
